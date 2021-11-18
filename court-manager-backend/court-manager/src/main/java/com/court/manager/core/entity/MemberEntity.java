package com.court.manager.core.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "MEMBERS")
@Data @EqualsAndHashCode(callSuper = true)
public class MemberEntity extends BaseEntity {
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "CONTACT_NUMBER")
	private String contactDetails;
	
	@Column(name = "CURRENT_PAYMENT_CYCLE")
	private Integer currentPaymentCycle;
	
	@Column(name = "NEXT_PAYMENT")
	private LocalDate nextPayment;
	
	@Column(name = "LAST_PAYMENT")
	private LocalDate lastPayment;
	
	@Column(name = "IS_PAYMENT_DUE")
	private Boolean due;
	
	@Column(name = "TOTAL_DUE")
	private Double totalDue;
	
	@ManyToOne
	@JoinColumn(name = "BATCH_FK")
	private BatchEntity batch;
	
	@OneToOne
	@JoinColumn(name= "COURTS_FK")
	private CourtEntity court;
	
}
