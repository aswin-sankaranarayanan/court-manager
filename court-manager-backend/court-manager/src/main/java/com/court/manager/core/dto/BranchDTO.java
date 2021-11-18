package com.court.manager.core.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Component
@Data @ToString(exclude = {"client"},callSuper = true) @EqualsAndHashCode(callSuper = true)
public class BranchDTO extends BaseDTO {
	
	private String location;
	private Double monthlyFee;
	private Double guestFee;
	private Double courtFee;
	
	@JsonIgnore
	private List<ClientUserDTO> clientUsers;
	
	@JsonManagedReference
	private List<BatchDTO> batches;
	
	@JsonIgnore
	private List<GuestDTO> guests;
	
	@JsonBackReference
	private ClientDTO client;
	
	private List<CourtDTO> courts;
	
	public BranchDTO() {
		this.clientUsers = new ArrayList<>();
		this.batches =  new ArrayList<>();
		this.guests = new ArrayList<>();
		this.courts = new ArrayList<>();
	}
}
