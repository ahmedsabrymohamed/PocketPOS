package com.pocket.pos.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pocket.pos.exception.ResourceNotFoundException;
import com.pocket.pos.exception.ResourceStateCahngedException;
import com.pocket.pos.model.Bill;
import com.pocket.pos.model.BillItem;
import com.pocket.pos.model.BillType;
import com.pocket.pos.model.Bulk;
import com.pocket.pos.projections.BillSecondPartyOnlyProjection;
import com.pocket.pos.projections.BillWithoutRelationsProjection;
import com.pocket.pos.repository.BillRepo;
import com.pocket.pos.requestModel.BillItemRequestModel;
import com.pocket.pos.requestModel.BillRequestModel;
import com.pocket.pos.requestModel.BulkRequestModel;

@Service
public class BillService {
	private BillRepo billRepo;
	private BulkService bulkService;
	private BillSecondPartyService secondPartyService;
	private static final int PAGE_SIZE = 1;

	@Transactional(readOnly = true)
	public Page<BillWithoutRelationsProjection> getNotDeletedBills(int page) {
		Pageable requestedPage = PageRequest.of(page, PAGE_SIZE);
		return billRepo.findByDeleted(false, requestedPage);
	}

	@Transactional(readOnly = true)
	public BillWithoutRelationsProjection getBillById(Long id) {
		return billRepo.findBillById(id).orElseThrow(() -> new ResourceNotFoundException(id, Bill.class));
	}

	@Transactional(readOnly = true)
	public BillSecondPartyOnlyProjection getSecondPartyById(Long id) {
		return billRepo.findBill_SecondPartyById(id).orElseThrow(() -> new ResourceNotFoundException(id, Bill.class));
	}

	@Transactional
	public Long addBill(BillRequestModel billRequest) {

		Bill bill = new Bill(billRequest.paid, billRequest.billType,
				secondPartyService.getById(billRequest.secondParty));
		bill.setItems(processBillItems(billRequest.items, billRequest.billType));
		bill.calculateBill();

		billRepo.save(bill);

		Long id = bill.getId();
		return id;
	}

	@Transactional
	private Collection<BillItem> processBillItems(Collection<BillItemRequestModel> items, BillType type) {
		return items.stream().map(type.equals(BillType.SELL) ? this::createSoldBillItem : this::createBoughtBillItem)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	private BillItem createSoldBillItem(BillItemRequestModel item) {
		Bulk bulk = bulkService.subtractQuantity(new BulkRequestModel(item.id,item.price,item.quantity,item.product));
		return new BillItem(item.price, item.quantity, bulk);

	}

	private BillItem createBoughtBillItem(BillItemRequestModel item) {
		Bulk bulk;
		if (item.newBulk && !bulkService.existsByPriceAndproduct_ProductId(item.price, item.product)) {
			bulk = bulkService.addBulk(new BulkRequestModel(item.price, item.quantity, item.product));
		} else {
			bulk = bulkService.addQuantity(new BulkRequestModel(item.id, item.price, item.quantity, item.product));
		}
		return new BillItem(bulk.getBuyPrice(), item.quantity, bulk);

	}

	@Autowired
	public void setBillRepo(BillRepo billRepo) {
		this.billRepo = billRepo;
	}

	@Autowired
	public void setBulkService(BulkService bulkService) {
		this.bulkService = bulkService;
	}

	@Autowired
	public void setSecondPartyService(BillSecondPartyService secondPartyService) {
		this.secondPartyService = secondPartyService;
	}

}
