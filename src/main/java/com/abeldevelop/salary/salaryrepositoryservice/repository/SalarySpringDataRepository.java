package com.abeldevelop.salary.salaryrepositoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abeldevelop.salary.salaryrepositoryservice.entity.Employee;

public interface SalarySpringDataRepository extends JpaRepository<Employee, Long> {

}
