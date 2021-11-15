package com.court.manager.core.entity;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "BATCH")
@Data @EqualsAndHashCode(callSuper = true)
public class BatchEntity extends BaseEntity {

	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "START_TIME")
	private LocalTime startTime;
	
	@Column(name = "END_TIME")
	private LocalTime endTime;
	
	@ManyToOne
	@JoinColumn(name = "BRANCH_FK")
	private BranchEntity branch;
	
}
