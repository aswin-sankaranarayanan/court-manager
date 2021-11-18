package com.court.manager.core.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Component
@Data @EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class ClientDTO extends BaseDTO {

	private String name;
	private String owner;
	private String email;
	
	@JsonManagedReference
	private List<BranchDTO> branches;


	public ClientDTO(){
		super();
		this.branches = new ArrayList<>();
	}
	
	public void addBranch(BranchDTO branch) {
		this.branches.add(branch);
		branch.setClient(this);
	}
	
}

