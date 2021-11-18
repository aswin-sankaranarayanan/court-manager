package com.court.manager.core.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.court.manager.common.RecordNotFoundException;
import com.court.manager.core.dto.MemberDTO;
import com.court.manager.core.dto.PagedResponseDTO;
import com.court.manager.core.entity.MemberEntity;
import com.court.manager.core.repository.MemberRepository;

@Service
public class MemberService extends BaseService<MemberEntity, MemberDTO, MemberRepository> {

	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		super();
		this.memberRepository = memberRepository;
		init(MemberEntity.class, MemberDTO.class, memberRepository);
	}
	
	public MemberDTO saveMember(MemberDTO memberDTO) {
		return save(memberDTO);
	}
	
	public PagedResponseDTO<MemberDTO> findAllMembersInBatch(Long batchId, int page, int size){
		Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, "name"));
		Page<MemberEntity> members = this.memberRepository.findAllByBatch_Id(batchId, pageable);
		return constructPagedResponseDTO(members);
	}
	
	public MemberDTO updateMember(MemberDTO memberDTO) throws RecordNotFoundException {
		return update(memberDTO);
	}
	
	public void deleteMember(Long memberId) throws RecordNotFoundException {
		delete(memberId);
	}
	
	public PagedResponseDTO<MemberDTO> searchMembersInBatch(Long batchId, String query, int page, int size){
		Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, "name"));
		Page<MemberEntity> members = this.memberRepository.searchMembersInBatch(batchId,query,pageable);
		return constructPagedResponseDTO(members);
	}
}
