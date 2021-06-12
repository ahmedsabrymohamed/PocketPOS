package com.pocket.pos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pocket.pos.model.Product;


public interface ProductRepo extends PagingAndSortingRepository<Product, Long> {
	
	public Page<Product> findByDeleted(boolean deleted, Pageable pageable);
}
