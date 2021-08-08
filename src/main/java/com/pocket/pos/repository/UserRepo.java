package com.pocket.pos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pocket.pos.entity.User;


public interface UserRepo extends PagingAndSortingRepository<User, Long>{
	
	public Page<User> findByDeleted(boolean deleted, Pageable pageable);
	
	public User findByDeletedAndUserNameAndPassword(boolean deleted, String userName,String password);
	
}
