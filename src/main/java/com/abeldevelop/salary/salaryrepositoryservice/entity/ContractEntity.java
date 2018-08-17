package com.abeldevelop.salary.salaryrepositoryservice.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter

@Entity
@Table(name="Contract")
@SequenceGenerator(name="CONTRACT_SEQ", sequenceName="contract_seq")
public class ContractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CONTRACT_SEQ")
	private Long id;
	private String category;
	private Date startDate;
	private Date endDate;
	private Double salary;
	private String currency;
	private String bankAccount;
	
}
