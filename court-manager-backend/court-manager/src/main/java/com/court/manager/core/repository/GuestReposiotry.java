package com.court.manager.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.court.manager.core.entity.GuestEntity;

public interface GuestReposiotry extends PagingAndSortingRepository<GuestEntity, Long> {

	Page<GuestEntity> findAllByBranch_Id(Long branchId, Pageable pageable);

	@Query("Select G from GuestEntity G join BranchEntity B on G.branch.id = B.id where B.id = :branchId"
			+ " AND (G.name like %:query% OR G.contactDetails like %:query%)")
	Page<GuestEntity> findGuestInBranch(Long branchId, String query,Pageable pageable);

}
