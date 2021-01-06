package com.pocket.pos.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.pocket.pos.repository.BillRepo;
import com.pocket.pos.repository.BillSecondPartyRepo;
import com.pocket.pos.repository.BulkRepo;
import com.pocket.pos.repository.ProductRepo;
import com.pocket.pos.repository.UserRepo;


public class DashboardService {
	@Autowired
	BillRepo billRepo;
	@Autowired
	BillSecondPartyRepo secondPartyRepo;
	@Autowired
	ProductRepo productRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	BulkRepo bulkRepo ;
}
