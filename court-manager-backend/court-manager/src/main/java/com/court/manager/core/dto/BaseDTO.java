package com.court.manager.core.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"user","lastUpdated"})
public class BaseDTO {

	private Long id;
	private String user;
	private Date lastUpdated = new Date();
	
	
}