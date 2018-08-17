package com.abeldevelop.salary.salaryrepositoryservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
@Table(name="Employee")
@SequenceGenerator(name="EMPLOYEE_SEQ", sequenceName="employee_seq")
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EMPLOYEE_SEQ")
	private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String dni;
    private String birthday;
    private String phone;
    private String sex;
    private String role;
    
    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    private ContractEntity contract;
    
    @Formula( value = "CONCAT(firstName, ' ', lastName)")
    private String fullName;


}
