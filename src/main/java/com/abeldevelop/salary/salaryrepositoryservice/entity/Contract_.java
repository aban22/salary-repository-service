package com.abeldevelop.salary.salaryrepositoryservice.entity;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel(Contract.class)
public class Contract_ {

	public static volatile SingularAttribute<Contract, Long> id;
	public static volatile SingularAttribute<Contract, String> category;
	public static volatile SingularAttribute<Contract, Date> startDate;
	public static volatile SingularAttribute<Contract, Date> endDate;
	public static volatile SingularAttribute<Contract, Double> salary;
	public static volatile SingularAttribute<Contract, String> currency;
	public static volatile SingularAttribute<Contract, String> bankAccount;
}
