package com.court.manager.core.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.court.manager.common.RecordNotFoundException;
import com.court.manager.core.dto.BranchDTO;
import com.court.manager.core.dto.PagedResponseDTO;
import com.court.manager.core.entity.BranchEntity;
import com.court.manager.core.repository.BranchRepository;

@Service
public class BranchService extends BaseService<BranchEntity, BranchDTO, BranchRepository> {
	
private BranchRepository branchRepository;
	
	public BranchService(BranchRepository branchRepository) {
		this.branchRepository = branchRepository;
		init(BranchEntity.class,BranchDTO.class,this.branchRepository);
	}
	
	
	public BranchDTO findBranch(Long id) throws RecordNotFoundException {
		return findById(id);
	}


	public BranchDTO saveBranch(BranchDTO branchDTO) {
		branchDTO.getCourts().forEach(court -> court.setBranch(branchDTO));
		return save(branchDTO);
	}
	

	public void deleteBranch(Long branchId) throws RecordNotFoundException {
		delete(branchId);
	}	
}
