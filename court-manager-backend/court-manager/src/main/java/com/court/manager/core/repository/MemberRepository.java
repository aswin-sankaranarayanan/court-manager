package com.court.manager.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.court.manager.core.entity.MemberEntity;

@Repository
public interface MemberRepository extends PagingAndSortingRepository<MemberEntity, Long> {

	Page<MemberEntity> findAllByBatch_Id(Long batchId,Pageable pageable);
	
	@Query("Select M from MemberEntity M join BatchEntity B on M.batch.id=B.id"
			+ " where B.id=:batchId AND (M.name like %:query% OR M.contactDetails like %:query%)")
	Page<MemberEntity> searchMembersInBatch(Long batchId,String query,Pageable pageable);
}
