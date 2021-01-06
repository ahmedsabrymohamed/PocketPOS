package com.pocket.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pocket.pos.repository.BillRepo;
import com.pocket.pos.repository.BillSecondPartyRepo;
import com.pocket.pos.repository.BulkRepo;
import com.pocket.pos.repository.ProductRepo;
import com.pocket.pos.repository.UserRepo;

@CrossOrigin
@RestController
@RequestMapping("/Dashboard")
public class DashboardController {
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
