package com.pocket.pos.projection;

import java.time.LocalDateTime;

import com.pocket.pos.entity.BillType;

public interface BillWithoutRelationsProjection extends BillProjection {
	
	 
	 Long getVersion();
	 
	 Double getTotal();

	 Double getPaid();
	 
	 boolean isDeleted();

	 Double getRemainder();
	
	 BillType getBillType();
	
	 LocalDateTime getCreateDateTime();
	
	 LocalDateTime getUpdateDateTime();


}
