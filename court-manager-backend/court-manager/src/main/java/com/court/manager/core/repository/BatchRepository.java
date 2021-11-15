package com.court.manager.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.court.manager.core.entity.BatchEntity;

@Repository
public interface BatchRepository extends PagingAndSortingRepository<BatchEntity, Long> {

	@Query("Select o from BatchEntity o inner join BranchEntity b on o.branch.id = b.id where o.branch.id=:id order by o.startTime")
	Page<BatchEntity> findByBranchId(@Param("id")Long branchId,Pageable page);

}
