package com.pocket.pos.requestModel;

import java.util.Collection;

import com.pocket.pos.model.BillType;

public class BillRequestModel {
	
	public Long id ;
	public Double paid;
	public BillType billType;
	public Long secondParty;
	public Collection<BillItemRequestModel> items ;

	
	public BillRequestModel() {
		
	}


	public BillRequestModel(Double paid, BillType billType, Long secondParty, Collection<BillItemRequestModel> items) {
		this.paid = paid;
		this.billType = billType;
		this.secondParty = secondParty;
		this.items = items;
	}


	public BillRequestModel(Long id, Double paid, BillType billType, Long secondParty,
			Collection<BillItemRequestModel> items) {
		this.id = id;
		this.paid = paid;
		this.billType = billType;
		this.secondParty = secondParty;
		this.items = items;
	}
	
	
	
	
}
