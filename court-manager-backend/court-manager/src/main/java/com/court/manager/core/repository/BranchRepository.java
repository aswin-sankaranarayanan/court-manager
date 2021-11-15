package com.court.manager.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.court.manager.core.entity.BranchEntity;

@Repository
public interface BranchRepository extends PagingAndSortingRepository<BranchEntity, Long> {

}
