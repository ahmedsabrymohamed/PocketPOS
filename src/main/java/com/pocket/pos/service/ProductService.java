package com.pocket.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pocket.pos.exception.ResourceNotFoundException;
import com.pocket.pos.model.Product;
import com.pocket.pos.repository.ProductRepo;

@Component
public class ProductService {
	private ProductRepo productRepo;
	
	@Transactional(readOnly = true)
	public Product getById(Long id) {
		return productRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, Product.class));
	}

	@Autowired
	public void setProductRepo(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}
	
	
}
