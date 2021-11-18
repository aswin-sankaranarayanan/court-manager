package com.court.manager.core.dto;

import java.time.LocalDate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Component
@Data @EqualsAndHashCode(callSuper = true)
public class MemberDTO extends BaseDTO {

	private String name;
	private String contactDetails;
	private Integer currentPaymentCycle;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate nextPayment;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate lastPayment;
	
	private Boolean due;
	private Double totalDue;
	
	@JsonBackReference
	private BatchDTO batch;
	
	private CourtDTO court;
}
