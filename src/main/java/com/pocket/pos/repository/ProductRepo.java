package com.pocket.pos.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pocket.pos.model.Product;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface ProductRepo extends PagingAndSortingRepository<Product, Long> {

}
