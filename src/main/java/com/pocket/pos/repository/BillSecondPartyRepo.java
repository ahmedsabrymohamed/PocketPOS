package com.pocket.pos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pocket.pos.model.BillSecondParty;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "billSecondParty", path = "billSecondParty")
public interface BillSecondPartyRepo extends PagingAndSortingRepository<BillSecondParty, Long>{
	@RestResource(path = "findifexists", rel = "findifexists")
	public Page<BillSecondParty> findByDeleted(@Param("deleted")boolean deleted, Pageable pageable);
}
