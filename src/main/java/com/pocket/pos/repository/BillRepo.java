package com.pocket.pos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pocket.pos.model.Bill;
import com.pocket.pos.model.BillType;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "bill", path = "bill")
public interface BillRepo extends PagingAndSortingRepository<Bill, Long> {
	@RestResource(path = "findifexists", rel = "findifexists")
	public Page<Bill> findByDeleted(@Param("deleted")boolean deleted, Pageable pageable);
	@RestResource(path = "findBySecondPartyId", rel = "findBySecondPartyId")
	public Page<Bill> findByDeletedAndSecondParty_Id(@Param("deleted")boolean deleted,@Param("SecondPartyId")Long id, Pageable pageable);
	@RestResource(path = "findByBillType", rel = "findByBillType")
	public Page<Bill> findByDeletedAndBillType(@Param("deleted")boolean deleted,@Param("billType")BillType billType, Pageable pageable);
	@RestResource(path = "findBySecondPartyIdandBillType", rel = "findBySecondPartyIdandBillType")
	public Page<Bill> findByDeletedAndSecondParty_Id(@Param("deleted")boolean deleted,@Param("SecondPartyId")Long id,@Param("billType")BillType billType, Pageable pageable);
}