package com.pocket.pos.service;

import com.pocket.pos.entity.Bill;
import com.pocket.pos.entity.BillItem;
import com.pocket.pos.entity.BillType;
import com.pocket.pos.entity.Bulk;
import com.pocket.pos.exception.ResourceNotFoundException;
import com.pocket.pos.model.BillItemModel;
import com.pocket.pos.model.BillModel;
import com.pocket.pos.model.BulkModel;
import com.pocket.pos.projection.BillSecondPartyOnlyProjection;
import com.pocket.pos.projection.BillWithoutRelationsProjection;
import com.pocket.pos.repository.BillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

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
	public Bill addBill(BillModel billRequest) {

		Bill bill = new Bill(billRequest.getPaid(), billRequest.getBillType(),
				secondPartyService.getById(billRequest.getSecondParty()));
		bill.setItems(createBillItems(billRequest.getItems(), billRequest.getBillType()));
		bill.calculateBill();

		billRepo.save(bill);

		return bill;
	}

	@Transactional
	public Bill updateBill(BillModel request) {
		Bill bill = billRepo.findById(request.getBillId())
				.orElseThrow(() -> new ResourceNotFoundException(request.getBillId(), Bill.class));
		bill.setSecondParty(secondPartyService.getById(request.getSecondParty()));
		bill.setItems(updateBillItems(request.getItems(), bill));
		bill.calculateBill();
		return bill;
	}

	@Transactional
	private Collection<BillItem> createBillItems(Collection<BillItemModel> items, BillType type) {
		return items.stream().map(type.equals(BillType.SELL) ? this::createSellBillItem : this::createPayBillItem)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	private BillItem createSellBillItem(BillItemModel item) {
		Bulk bulk = bulkService
				.subtractQuantity(new BulkModel(item.getBulkId(), item.getPrice(), item.getQuantity(), item.getProductId()));
		return new BillItem(item.getPrice(), item.getQuantity(), bulk);

	}

	private BillItem createPayBillItem(BillItemModel item) {
		Bulk bulk;
		if (item.isNewBulk() && !bulkService.existsByPriceAndproduct_ProductId(item.getPrice(), item.getProductId())) {
			bulk = bulkService.addBulk(new BulkModel(item.getPrice(), item.getQuantity(), item.getProductId()));
		} else {
			bulk = bulkService.addQuantity(new BulkModel(item.getBulkId(), item.getPrice(), item.getQuantity(), item.getProductId()));
		}
		return new BillItem(bulk.getBuyPrice(), item.getQuantity(), bulk);

	}

	@Transactional
	private Collection<BillItem> updateBillItems(Collection<BillItemModel> newItems, Bill bill) {
		bill.getItems().stream()
				.forEach(bill.getBillType().equals(BillType.SELL) ? this::rollBackSoldBillItem : this::rollBackBoughtBillItem);
		billRepo.deleteBill_itemsById(bill.getId());
		return newItems.stream().map(bill.getBillType().equals(BillType.SELL) ? this::createSellBillItem : this::createPayBillItem)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	private void rollBackSoldBillItem(BillItem item) {
		Bulk itemBulk = item.getBulk();
		bulkService.addQuantity(
				new BulkModel(itemBulk.getId(), null, itemBulk.getQuantity(), null));
	}

	private void rollBackBoughtBillItem(BillItem item) {
		Bulk itemBulk = item.getBulk();
		bulkService.subtractQuantity(
				new BulkModel(itemBulk.getId(), null, itemBulk.getQuantity(), null));
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
