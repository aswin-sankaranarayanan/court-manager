package com.court.manager.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "COURTS")
@Data @EqualsAndHashCode(callSuper = true)
public class CourtEntity extends BaseEntity {

	@Column(name = "NAME")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "BRANCH_FK")
	private BranchEntity branch;
	
}
