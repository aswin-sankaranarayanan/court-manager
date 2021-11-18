package com.court.manager.api;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.court.manager.common.RecordNotFoundException;
import com.court.manager.core.dto.MemberDTO;
import com.court.manager.core.dto.PagedResponseDTO;
import com.court.manager.core.services.MemberService;

@RestController
@RequestMapping("/api/members")
public class MembersAPI {

	@Autowired
	private MemberService memberService;
	
	@PostMapping
	public ResponseEntity<MemberDTO> saveMember(@RequestBody MemberDTO member){
		LocalDate lastPayment = member.getLastPayment();
		member.setNextPayment(lastPayment.plusDays(30));
		return ResponseEntity.ok(memberService.saveMember(member));
	}
	
	@GetMapping("/batch/{batchId}")
	public ResponseEntity<PagedResponseDTO<MemberDTO>> getAllMembersInBatch(@PathVariable("batchId") Long batchId,
			@RequestParam(name = "page",defaultValue = "0") int page,
			@RequestParam(name ="size", defaultValue = "5") int size){
		return ResponseEntity.ok(memberService.findAllMembersInBatch(batchId, page, size));
	}
	
	@PutMapping
	public ResponseEntity<MemberDTO> updateMember(@RequestBody MemberDTO member) throws RecordNotFoundException{
		return ResponseEntity.ok(memberService.updateMember(member));
	}
	
	@DeleteMapping("/{memberId}")
	public ResponseEntity<Void> deleteMember(@PathVariable("memberId") Long memberId) throws RecordNotFoundException{
		memberService.deleteMember(memberId);
		return ResponseEntity.ok().build();
	}
}
