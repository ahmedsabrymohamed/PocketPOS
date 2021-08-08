package com.pocket.pos.service;

import com.pocket.pos.entity.Bulk;
import com.pocket.pos.model.BulkModel;
import com.pocket.pos.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pocket.pos.exception.ResourceNotFoundException;
import com.pocket.pos.entity.Product;
import com.pocket.pos.repository.ProductRepo;

@Component
public class ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private  BulkService bulkService;

	private static final int PAGE_SIZE = 1;
	
	@Transactional(readOnly = true)
	public Product getById(Long id) {
		return productRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, Product.class));
	}

	@Transactional(readOnly = true)
	public Page<Product> getNotDeletedProducts(int page) {
		Pageable requestedPage = PageRequest.of(page, PAGE_SIZE);
		return productRepo.findByDeleted(false, requestedPage);
	}

	@Transactional(readOnly = true)
	public Page<Product> getDeletedProducts(int page) {
		Pageable requestedPage = PageRequest.of(page, PAGE_SIZE);
		return productRepo.findByDeleted(true, requestedPage);
	}

	@Transactional(readOnly = true)
	public Page<BulkModel>  getBulks(Long id, int page) {
		return bulkService.findByProductId(id ,page);
	}

	@Transactional
	public Product addProduct(ProductModel model) {
		Product product = new Product(model.getName(),model.getExpirationDate());
		productRepo.save(product);
		return product;
	}

}
