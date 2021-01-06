package com.pocket.pos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pocket.pos.model.BillSecondParty;


public interface BillSecondPartyRepo extends PagingAndSortingRepository<BillSecondParty, Long>{
	
	public Page<BillSecondParty> findByDeleted(boolean deleted, Pageable pageable);
}
