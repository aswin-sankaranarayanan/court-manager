package com.court.manager.core.dto;

import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = true)
public class GuestDTO extends BaseDTO{

	private String name;
	private String contactDetails;
	private Date startTime;
	private  Date endTime;
	private Double fee;
	private  Date feePaidOn;
	private Boolean due;
	private BranchDTO branch;
}
