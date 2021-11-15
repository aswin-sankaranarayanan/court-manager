package com.court.manager.core.services;

import org.springframework.stereotype.Service;

import com.court.manager.common.RecordNotFoundException;
import com.court.manager.core.dto.BranchDTO;
import com.court.manager.core.entity.BranchEntity;
import com.court.manager.core.repository.BranchRepository;

@Service
public class BranchService extends BaseService<BranchEntity, BranchDTO, BranchRepository> {
	
private BranchRepository branchRepository;
	
	public BranchService(BranchRepository branchRepository) {
		this.branchRepository = branchRepository;
		init(BranchEntity.class,BranchDTO.class,this.branchRepository);
	}
	
	
	public BranchDTO findBranch(BranchDTO branchDTO) throws RecordNotFoundException {
		return findOne(branchDTO);
	}


	@Override
	public void init(Class<BranchEntity> entityClass, Class<BranchDTO> dtoClass, BranchRepository repository) {
		this.entityClass = entityClass;
		this.dtoClass = dtoClass;
		this.repository = repository;			
	}
	
}
