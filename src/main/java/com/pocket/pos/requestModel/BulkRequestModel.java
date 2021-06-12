package com.pocket.pos.requestModel;

public class BulkRequestModel {

	public Long id;
	public Double buyPrice;
	public Double quantity;
	public Long product;
	
	
	
	public BulkRequestModel() {
		
	}



	public BulkRequestModel(Double buyPrice, Double quantity, Long product) {
		
		this.buyPrice = buyPrice;
		this.quantity = quantity;
		this.product = product;
	}



	public BulkRequestModel(Long id, Double buyPrice, Double quantity, Long product) {
		
		this.id = id;
		this.buyPrice = buyPrice;
		this.quantity = quantity;
		this.product = product;
	}
	
	
	
	
}
