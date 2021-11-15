package com.court.manager.core.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Component
@Data @EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class ClientDTO extends BaseDTO {

	private String name;
	private String owner;
	private String email;
	private List<BranchDTO> branches;
	private List<GuestDTO> guests;
	private List<BatchDTO> batches;


	public ClientDTO(){
		super();
		this.branches = new ArrayList<>();
		this.guests = new ArrayList<>();
		this.batches = new ArrayList<>();
		
	}
	
	public void addBranch(BranchDTO branch) {
		this.branches.add(branch);
		branch.setClient(this);
	}
	
}

