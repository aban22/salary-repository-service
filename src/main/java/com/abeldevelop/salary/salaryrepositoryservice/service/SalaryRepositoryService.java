package com.abeldevelop.salary.salaryrepositoryservice.service;

import java.util.List;

import com.abeldevelop.salary.salaryrepositoryservice.entity.Employee;

public interface SalaryRepositoryService {

	public Employee createEmployee(final Employee employee);
	
	public void updateEmployee(Employee employee);
	
	public void deleteEmployee(Long id);
	
	public List<Employee> findAll();
	
	public Employee findById(Long id);

	public List<Employee> simpleSearch(String data);
	
	public List<Employee> searchBySalary(Double minSalary, Double maxSalary);
}
