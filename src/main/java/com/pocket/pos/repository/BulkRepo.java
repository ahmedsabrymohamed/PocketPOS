package com.pocket.pos.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pocket.pos.model.Bulk;


public interface BulkRepo extends PagingAndSortingRepository<Bulk, Long>{
	
	public Page<Bulk> findByDeletedAndProduct_Id(boolean deleted,Long id, Pageable pageable);
	
	public Page<Bulk> findByDeleted(boolean deleted, Pageable pageable);
	
	public boolean existsByBuyPriceAndProduct_Id(Double price,Long productId);
	
	public Optional<Bulk> findByBuyPriceAndProduct_Id(Double price,Long productId);
	
	
}
