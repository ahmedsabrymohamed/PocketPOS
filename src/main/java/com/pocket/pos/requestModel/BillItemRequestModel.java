package com.pocket.pos.requestModel;

public class BillItemRequestModel {

	public Long id;
	public Double price;
	public Double quantity;
	public Long bulk;
	public boolean newBulk;
	public Long product;

	public BillItemRequestModel() {

	}

	public BillItemRequestModel(Double price, Double quantity, Long bulk, boolean newBulk, Long product) {
		this.price = price;
		this.quantity = quantity;
		this.bulk = bulk;
		this.newBulk = newBulk;
		this.product = product;
	}

	public BillItemRequestModel(Long id, Double price, Double quantity, Long bulk, boolean newBulk, Long product) {
		
		this.id = id;
		this.price = price;
		this.quantity = quantity;
		this.bulk = bulk;
		this.newBulk = newBulk;
		this.product = product;
	}

	
	
}
