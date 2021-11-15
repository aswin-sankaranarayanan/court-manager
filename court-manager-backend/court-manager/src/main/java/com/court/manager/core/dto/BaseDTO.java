package com.court.manager.core.dto;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class BaseDTO {

	private Long id;
	private String user;
	private Date lastUpdated = new Date();
	
	
}