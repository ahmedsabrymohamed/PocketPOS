package com.pocket.pos.exception;

import com.pocket.pos.model.BulkModel;

public class BulkNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	private String notFound="Not Found Bulk "; 
	public BulkNotFoundException(BulkModel requestModel){
		notFound+=(requestModel.getBuyPrice()!=null?" BuyPrice: "+requestModel.getBuyPrice():"");
		notFound+=(requestModel.getProductId() !=null?" ProductId: "+requestModel.getProductId() :"");
		
	}
	@Override
	public String getMessage() {
		
		return notFound;
	}

	
}
