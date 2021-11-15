package com.court.manager.core.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(of = "id")
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@LastModifiedBy
	@Column(name="LAST_UPDATED_BY")
	private String lastUpdatedBy = "Admin";
	
	@LastModifiedDate
	@Column(name = "LAST_UPDATED_ON")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date lastUpdated;
	
	public BaseEntity() {
		this.lastUpdated = new Date();
		this.lastUpdatedBy = "Admin";
	}
	
	
}
