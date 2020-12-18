package com.pocket.pos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pocket.pos.model.Bulk;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "bulk", path = "bulk")
public interface BulkRepo extends PagingAndSortingRepository<Bulk, Long>{
	@RestResource(path = "findByProductId", rel = "findByProductId")
	public Page<Bulk> findByDeletedAndProduct_Id(@Param("deleted")boolean deleted,@Param("productId")Long id, Pageable pageable);
	@RestResource(path = "findifexists", rel = "findifexists")
	public Page<Bulk> findByDeleted(@Param("deleted")boolean deleted, Pageable pageable);
}
