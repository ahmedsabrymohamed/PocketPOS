package com.pocket.pos.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pocket.pos.model.Bill;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "bill", path = "bill")
public interface BillRepo extends PagingAndSortingRepository<Bill, Long> {

}