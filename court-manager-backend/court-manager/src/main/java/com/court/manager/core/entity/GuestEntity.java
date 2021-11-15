package com.court.manager.core.entity;

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
	private Date startTime;
	
	@Column(name = "END_TIME")
	private  Date endTime;
	
	@Column(name = "FEE")
	private Double fee;
	
	@Column(name = "FEE_PAID_ON")
	private  Date feePaidOn;
	
	@Column(name = "IS_DUE")
	private Boolean due;
	
	@ManyToOne
	@JoinColumn(name = "BRANCH_FK")
	private BranchEntity branch;
}
