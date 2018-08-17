package com.abeldevelop.salary.salaryrepositoryservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter

@Entity
@Table(name="Contract")
@SequenceGenerator(name="CONTRACT_SEQ", sequenceName="contract_seq")
public class Contract {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CONTRACT_SEQ")
	private Long id;
	
	@Size(min=3, max=50)
	@Column(name="CATEGORY", nullable=false)
	private String category;
	
	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE", nullable=false)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;
	
	@Digits(integer=6, fraction=2)
	@Column(name="SALARY", nullable=false)
	private Double salary;
	
	@Size(min=3, max=3)
	@Column(name="CURRENCY", nullable=false)
	private String currency;
	
	@Size(min=24, max=24)
	@Column(name="BANK_ACCOUNT", nullable=false)
	private String bankAccount;
	
}
