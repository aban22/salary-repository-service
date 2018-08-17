package com.abeldevelop.salary.salaryrepositoryservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.abeldevelop.salary.salaryrepositoryservice.entity.Employee;
import com.abeldevelop.salary.salaryrepositoryservice.exception.SalaryRepositoryServiceException;
import com.abeldevelop.salary.salaryrepositoryservice.repository.SalarySearchRepository;
import com.abeldevelop.salary.salaryrepositoryservice.repository.SalarySpringDataRepository;
import com.abeldevelop.salary.salaryrepositoryservice.service.SalaryRepositoryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class SalaryRepositoryServiceImpl implements SalaryRepositoryService {

	private final SalarySearchRepository salarySearchRepository;
	private final SalarySpringDataRepository salarySpringDataRepository;
	
	@Override
	public Employee createEmployee(final Employee employee) {
		checkIsEmployeeExist(employee);
		//TODO => Catch exception de validaciones o bbdd
		return salarySpringDataRepository.save(employee);
	}
	
	@Override
	public void updateEmployee(Employee employee) {
		log.info("deleteEmployee findById => id: {}", employee);
		findById(employee.getId());
		salarySpringDataRepository.save(employee);
	}
	@Override
	public void deleteEmployee(Long id) {
		log.info("deleteEmployee findById => id: {}", id);
		findById(id);
		salarySpringDataRepository.deleteById(id);
	}
	@Override
	public List<Employee> findAll() {
		return salarySpringDataRepository.findAll();
	}
	@Override
	public Employee findById(Long id) {
		log.info("simpleSearch findById => id: {}", id);
		Optional<Employee> employeeOptional = salarySpringDataRepository.findById(id);
		if(!employeeOptional.isPresent()) {
			throw new SalaryRepositoryServiceException("The user not exist");
		}
		return employeeOptional.get();
	}
	@Override
	public List<Employee> simpleSearch(String data) {
		log.info("simpleSearch dataIn => data: {}", data);
		if(data == null || data.trim().length() == 0) {
			log.error("The value can not be null");
			throw new SalaryRepositoryServiceException("The value can not be null");
		}
		if(data.trim().length() == 1) {
			log.error("The value should have at least 2 characters");
			throw new SalaryRepositoryServiceException("The value should have at least 2 characters");
		}
		List<Employee> employees = salarySearchRepository.simpleSearch(data);
		log.info("simpleSearch dataOut => employees.size(): {}", employees.size());
		return employees;
	}
	@Override
	public List<Employee> searchBySalary(Double minSalary, Double maxSalary) {
		log.info("searchBySalary dataIn => minSalary: {}, maxSalary: {}", minSalary, maxSalary);
		if(minSalary == null && maxSalary == null) {
			throw new SalaryRepositoryServiceException("The value can not be null");
		}
		
		if(minSalary != null && maxSalary != null) {
			throw new SalaryRepositoryServiceException("El primer valor debe ser menor que el segundo");
		}
		
		List<Employee> employees = salarySearchRepository.searchBySalary(minSalary, maxSalary);
		log.info("searchBySalary dataOut => employees.size(): {}", employees.size());
		return employees;
	}
	
	private void checkIsEmployeeExist(Employee employee) {
		List<Employee> employees = salarySearchRepository.checkIsEmployeeExist(employee.getDni(), employee.getEmail(), employee.getPhone());
		List<String> errors = new ArrayList<>();
		
		if(employees != null && !employees.isEmpty()) {
			log.error("Exsist {} employees with unique data", employees.size());
			if(employees.get(0).getDni().equals(employee.getDni())) {
				log.error("The dni already exist in the system");
				errors.add("The dni already exist in the system");
			}
			if(employees.get(0).getEmail().equals(employee.getEmail())) {
				log.error("The email already exist in the system");
				errors.add("The email already exist in the system");
			}
			if(employees.get(0).getPhone().equals(employee.getPhone())) {
				log.error("The phone already exist in the system");
				errors.add("The phone already exist in the system");
			}
		}
		
		if(!errors.isEmpty()) {
			throw new SalaryRepositoryServiceException(errors);
		}
	}
}
