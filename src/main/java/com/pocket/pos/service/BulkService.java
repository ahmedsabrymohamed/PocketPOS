package com.pocket.pos.service;

import com.pocket.pos.util.mapper.entitymapper.BulkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pocket.pos.exception.BulkNotFoundException;
import com.pocket.pos.exception.MissingParametersException;
import com.pocket.pos.exception.ResourceNotFoundException;
import com.pocket.pos.exception.ResourceStateCahngedException;
import com.pocket.pos.entity.Bulk;
import com.pocket.pos.repository.BulkRepo;
import com.pocket.pos.model.BulkModel;

@Service
public class BulkService {

	private BulkRepo bulkRepo;
	private ProductService productService;
	private BulkMapper bulkMapper;
	private static final int PAGE_SIZE = 1;

	@Transactional(readOnly = true)
	public BulkModel getById(Long id) {
		return bulkMapper.mapBulkEntityToBulkModel(bulkRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, Bulk.class)));
	}

	@Transactional(readOnly = true)
	public Page<BulkModel> findByProductId(Long productId, int page) {
		Pageable requestedPage = PageRequest.of(page, PAGE_SIZE);

		Page<Bulk> bulks = bulkRepo.findByDeletedAndProduct_Id(false,productId,requestedPage);
		Page<BulkModel> bulkModels= new PageImpl<BulkModel>(bulkMapper.mapBulkEntitiesToBulkModels(bulks.getContent()),bulks.getPageable(),bulks.getTotalElements());
		return bulkModels;
	}

	@Transactional(readOnly = true)
	public boolean existsById(Long id) {
		return bulkRepo.existsById(id);
	}

	@Transactional
	public Bulk addQuantity(BulkModel requestModel) {
		Bulk bulk;
		if (requestModel.getBulkId() != null) {
			bulk = bulkRepo.findById(requestModel.getBulkId())
					.orElseThrow(() -> new ResourceNotFoundException(requestModel.getBulkId(), Bulk.class));
		} else if (requestModel.getBuyPrice() != null && requestModel.getProductId() != null) {
			bulk = bulkRepo.findByBuyPriceAndProduct_Id(requestModel.getBuyPrice(), requestModel.getProductId())
					.orElseThrow(() -> new BulkNotFoundException(requestModel));
		} else {
			throw new MissingParametersException(BulkModel.class);
		}

		bulk.addQuantity(requestModel.getQuantity());
		return bulk;
	}

	@Transactional
	public Bulk subtractQuantity(BulkModel requestModel) {
		Bulk bulk = bulkRepo.findById(requestModel.getBulkId())
				.orElseThrow(() -> new ResourceNotFoundException(requestModel.getBulkId(), Bulk.class));
		Double quantity = bulk.getQuantity();
		if (quantity - requestModel.getQuantity() >= 0) {
			bulk.subtractQuantity(requestModel.getQuantity());
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
	public Bulk addBulk(BulkModel model) {
		Bulk bulk = new Bulk(model.getBuyPrice(), model.getQuantity(), productService.getById(model.getProductId()));
		bulkRepo.save(bulk);
		return bulk;
	}



	@Autowired
	public void setBulkRepo(BulkRepo bulkRepo) {
		this.bulkRepo = bulkRepo;
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setBulkMapper(BulkMapper bulkMapper) {
		this.bulkMapper = bulkMapper;
	}
}
