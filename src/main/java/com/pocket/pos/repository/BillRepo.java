package com.pocket.pos.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pocket.pos.model.Bill;
import com.pocket.pos.model.BillType;
import com.pocket.pos.projections.BillSecondPartyOnlyProjection;
import com.pocket.pos.projections.BillWithoutRelationsProjection;


public interface BillRepo extends PagingAndSortingRepository<Bill, Long> {
	
	public Page<BillWithoutRelationsProjection> findByDeleted(boolean deleted, Pageable pageable);
	
	public Optional<BillWithoutRelationsProjection> findBillById(Long id);
	
	public Optional<BillSecondPartyOnlyProjection> findBill_SecondPartyById(Long id);
	
	public Page<Bill> findByDeletedAndSecondParty_Id(boolean deleted,Long id, Pageable pageable);
	
	
	public Page<Bill> findByDeletedAndBillType(boolean deleted,BillType billType, Pageable pageable);
	
	
	public Page<Bill> findByDeletedAndSecondParty_IdAndBillType(boolean deleted,Long id,BillType billType, Pageable pageable);
}