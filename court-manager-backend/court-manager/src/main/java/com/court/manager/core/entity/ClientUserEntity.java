package com.court.manager.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "CLIENT_USERS")
@Data @EqualsAndHashCode(callSuper = true)
public class ClientUserEntity extends BaseEntity {

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "IS_ADMIN")
	private Boolean admin;
	
	@ManyToOne
	@JoinColumn(name = "BRANCH_FK")
	private BranchEntity branch;
	
}
