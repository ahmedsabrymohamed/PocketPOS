package com.pocket.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pocket.pos.exception.BulkNotFoundException;
import com.pocket.pos.exception.MissingParametersException;
import com.pocket.pos.exception.ResourceNotFoundException;
import com.pocket.pos.exception.ResourceStateCahngedException;
import com.pocket.pos.model.Bulk;
import com.pocket.pos.repository.BulkRepo;
import com.pocket.pos.requestModel.BulkRequestModel;

@Service
public class BulkService {

	private BillService billService;
	private BulkRepo bulkRepo;
	private ProductService productService;
	private static final int PAGE_SIZE = 1;

	@Transactional(readOnly = true)
	public Bulk getById(Long id) {
		return bulkRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, Bulk.class));
	}

	@Transactional(readOnly = true)
	public boolean existsById(Long id) {
		return bulkRepo.existsById(id);
	}

	@Transactional
	public Bulk addQuantity(BulkRequestModel requestModel) {
		Bulk bulk;
		if (requestModel.id != null) {
			bulk = bulkRepo.findById(requestModel.id)
					.orElseThrow(() -> new ResourceNotFoundException(requestModel.id, Bulk.class));
		} else if (requestModel.buyPrice != null && requestModel.product != null) {
			bulk = bulkRepo.findByBuyPriceAndProduct_Id(requestModel.buyPrice, requestModel.product)
					.orElseThrow(() -> new BulkNotFoundException(requestModel));
		} else {
			throw new MissingParametersException(BulkRequestModel.class);
		}

		bulk.addQuantity(requestModel.quantity);
		return bulk;
	}

	@Transactional
	public Bulk subtractQuantity(BulkRequestModel requestModel) {
		Bulk bulk = bulkRepo.findById(requestModel.id)
				.orElseThrow(() -> new ResourceNotFoundException(requestModel.id, Bulk.class));
		Double quantity = bulk.getQuantity();
		if (quantity - requestModel.quantity >= 0) {
			bulk.subtractQuantity(requestModel.quantity);
		} else {
			throw new ResourceStateCahngedException(bulk.getId(), bulk.getClass());
		}

		return bulk;
	}

	@Transactional(readOnly = true)
	public boolean existsByPriceAndproduct_ProductId(Double price, Long productId) {
		return bulkRepo.existsByBuyPriceAndProduct_Id(price, productId);

	}

	@Transactional
	public Bulk addBulk(BulkRequestModel model) {
		Bulk bulk = new Bulk(model.buyPrice, model.quantity, productService.getById(model.product));
		bulkRepo.save(bulk);
		return bulk;
	}

	@Autowired
	public void setBillService(BillService billService) {
		this.billService = billService;
	}

	@Autowired
	public void setBulkRepo(BulkRepo bulkRepo) {
		this.bulkRepo = bulkRepo;
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}
