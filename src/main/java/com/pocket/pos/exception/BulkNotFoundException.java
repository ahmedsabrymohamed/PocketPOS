package com.pocket.pos.exception;

import com.pocket.pos.requestModel.BulkRequestModel;

public class BulkNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	private String notFound="Not Found Bulk "; 
	public BulkNotFoundException(BulkRequestModel requestModel){
		notFound+=(requestModel.buyPrice!=null?" BuyPrice: "+requestModel.buyPrice:"");
		notFound+=(requestModel.product!=null?" ProductId: "+requestModel.product:"");
		
	}
	@Override
	public String getMessage() {
		
		return notFound;
	}

	
}
