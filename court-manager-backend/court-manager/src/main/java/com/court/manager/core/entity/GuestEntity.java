package com.court.manager.core.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "GUEST")
@Data @EqualsAndHashCode(callSuper = true)
public class GuestEntity extends BaseEntity{

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "CONTACT_DETAILS")
	private String contactDetails;
	
	@Column(name = "START_TIME")
	private LocalTime startTime;
	
	@Column(name = "END_TIME")
	private LocalTime endTime;
	
	@Column(name = "FEE")
	private Double fee;
	
	@Column(name = "FEE_PAID_ON")
	private  LocalDate feePaidOn;
	
	@Column(name = "IS_DUE")
	private Boolean due;
	
	@Column(name = "IS_FULL_COURT")
	private Boolean fullCourt;
	
	@ManyToOne
	@JoinColumn(name = "BRANCH_FK")
	private BranchEntity branch;
}
