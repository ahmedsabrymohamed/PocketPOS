package com.pocket.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pocket.pos.exception.ResourceNotFoundException;
import com.pocket.pos.entity.BillSecondParty;
import com.pocket.pos.repository.BillSecondPartyRepo;

@Service
public class BillSecondPartyService {

	private  BillService billService;
	private  BulkService bulkService;
	private  BillSecondPartyRepo secondPartyRepo;
	private static final int PAGE_SIZE = 1;
	

	@Transactional(readOnly = true)
	public BillSecondParty getById(Long id) {
		return secondPartyRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, BillSecondParty.class));
	}
	
	@Transactional(readOnly = true)
	public Page<BillSecondParty> getNotDeletedParties(int page) {
		Pageable requestedPage = PageRequest.of(page, PAGE_SIZE);
		return secondPartyRepo.findByDeleted(false, requestedPage);
	}
	
	@Autowired
	public void setBillService(BillService billService){
		this.billService = billService;
	}
	
	@Autowired
	public void setBulkService(BulkService bulkService) {
		this.bulkService = bulkService;
	}

	@Autowired
	public void setSecondPartyRepo(BillSecondPartyRepo secondPartyRepo) {
		this.secondPartyRepo = secondPartyRepo;
	}
}
