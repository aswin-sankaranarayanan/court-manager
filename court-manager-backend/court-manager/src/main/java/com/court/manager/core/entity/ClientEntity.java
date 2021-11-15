package com.court.manager.core.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="CLIENT")
@Data @EqualsAndHashCode(callSuper = true)
public class ClientEntity extends BaseEntity {

	private String name;
	private String owner;
	private String email;
	
	@OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
	private List<BranchEntity> branches;
	
	
	public ClientEntity(){
		super();
		this.branches = new ArrayList<>();
	}
	
	public void addBranch(BranchEntity branch) {
		this.branches.add(branch);
		branch.setClient(this);
	}
	
}
