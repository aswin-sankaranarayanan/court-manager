package com.court.manager.core.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "CLIENT_BRANCH")
@Data @EqualsAndHashCode(callSuper = true)
public class BranchEntity extends BaseEntity {
	
	@Column(name = "LOCATION")
	private String location;
	
	@Column(name = "MONTHLY_FEE")
	private Double monthlyFee;
	
	@Column(name = "GUEST_FEE")
	private Double guestFee;
	
	@Column(name = "COURT_FEE")
	private Double courtFee;
	
	@ManyToOne
	@JoinColumn(name = "CLIENT_FK")
	private ClientEntity client;

	@OneToMany(mappedBy = "branch",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<ClientUserEntity> clientUsers;
	
	@OneToMany(mappedBy = "branch",cascade = CascadeType.ALL)
	private List<GuestEntity> guests;
	
	@OneToMany(mappedBy = "branch",cascade = CascadeType.ALL)
	private List<BatchEntity> batches;
	
	public BranchEntity(){
		this.clientUsers = new ArrayList<>();
		this.guests = new ArrayList<>();
		this.batches = new ArrayList<>();
	}
}
