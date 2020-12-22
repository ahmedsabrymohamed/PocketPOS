package com.pocket.pos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pocket.pos.model.User;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepo extends PagingAndSortingRepository<User, Long>{
	@RestResource(path = "findIfExists", rel = "findIfExists")
	public Page<User> findByDeleted(@Param("deleted")boolean deleted, Pageable pageable);
	@RestResource(path = "findByUserNameAndPassword", rel = "findByUserNameAndPassword")
	public Page<User> findByDeletedAndUserNameAndPassword(@Param("deleted")boolean deleted, @Param("userName") String userName,@Param("password") String password,Pageable pageable);
	
}
