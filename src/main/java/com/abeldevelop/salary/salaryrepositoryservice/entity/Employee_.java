package com.abeldevelop.salary.salaryrepositoryservice.entity;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Employee.class)
public class Employee_ {

	public static volatile SingularAttribute<Employee, Long> id;
	public static volatile SingularAttribute<Employee, String> firstName;
	public static volatile SingularAttribute<Employee, String> lastName;
	public static volatile SingularAttribute<Employee, String> email;
	public static volatile SingularAttribute<Employee, String> dni;
	public static volatile SingularAttribute<Employee, Date> birthday;
	public static volatile SingularAttribute<Employee, String> phone;
	public static volatile SingularAttribute<Employee, String> sex;
	public static volatile SingularAttribute<Employee, String> role;
	public static volatile SingularAttribute<Employee, Contract> contract;
	public static volatile SingularAttribute<Employee, String> fullName;
}
