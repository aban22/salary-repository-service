package com.abeldevelop.salary.salaryrepositoryservice.repository;

import java.util.List;

import com.abeldevelop.salary.salaryrepositoryservice.entity.Employee;

public interface SalarySearchRepository {

	public List<Employee> simpleSearch(String data);
	
	public List<Employee> searchBySalary(Double minSalary, Double maxSalary);
	
	public List<Employee> checkIsEmployeeExist(String dni, String email, String phone);
}
