package com.abeldevelop.salary.salaryrepositoryservice.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
@Table(name="EMPLOYEE")
@SequenceGenerator(name="EMPLOYEE_SEQ", sequenceName="employee_seq")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EMPLOYEE_SEQ")
	private Long id;
	
	@Size(min=2, max=240)
	@Column(name="FIRST_NAME", nullable=false)
    private String firstName;
	
	@Size(min=2, max=240)
	@Column(name="LAST_NAME", nullable=false)
    private String lastName;
	
	@Size(min=5, max=50)
	@Column(name="EMAIL", nullable=false, unique=true)
    private String email;
	
	@Size(min=9, max=9)
	@Column(name="DNI", nullable=false, unique=true)
    private String dni;
	
	@Temporal(TemporalType.DATE)
	@Past
	@Column(name="BIRTHDAY", nullable=false)
    private Date birthday;
	
	@Size(min=9, max=14)
	@Column(name="PHONE", nullable=false)
    private String phone;
	
	@Size(min=1, max=1)
	@Column(name="SEX")
    private String sex;
	
	@Size(min=1, max=240)
	@Column(name="ROLE")
    private String role;
    
    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CONTRACT_ID", nullable=false)
    private Contract contract;
    
    @Formula( value = "CONCAT(firstName, ' ', lastName)")
    private String fullName;


}
