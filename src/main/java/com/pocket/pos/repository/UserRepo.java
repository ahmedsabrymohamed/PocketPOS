package com.pocket.pos.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pocket.pos.model.User;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepo extends PagingAndSortingRepository<User, Long>{

}
