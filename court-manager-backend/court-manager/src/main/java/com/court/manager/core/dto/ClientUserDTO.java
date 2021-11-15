package com.court.manager.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data @ToString(exclude = {"branch"},callSuper = true) @EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"branch"})
public class ClientUserDTO extends BaseDTO {

	private String name;
	private String email;
	private Boolean admin;
	private BranchDTO branch;
}
