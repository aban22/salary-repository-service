package com.abeldevelop.salary.salaryrepositoryservice.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abeldevelop.salary.salaryrepositoryservice.entity.Employee;
import com.abeldevelop.salary.salaryrepositoryservice.service.SalaryRepositoryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
public class SalaryRepositoryController {

	private final SalaryRepositoryService salaryRepositoryService;
	
	@PostMapping("/employee")
	public ResponseEntity<Void> createEmployee(@Valid @RequestBody Employee employee) {
		log.info("createEmployee dataIn => {}", employee);
		Employee employeeInserted = salaryRepositoryService.createEmployee(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employeeInserted.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/employee")
	public ResponseEntity<Void> updateEmployee(@Valid @RequestBody Employee employee) {
		log.info("updateEmployee dataIn => {}", employee);
		salaryRepositoryService.updateEmployee(employee);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		log.info("deleteEmployee dataIn => id: {}", id);
		salaryRepositoryService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> findAll() {
		List<Employee> employees = salaryRepositoryService.findAll();
		log.info("findById dataOut => employees: {}", employees);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> findById(@PathVariable Long id) {
		log.info("findById dataIn => id: {}", id);
		Employee employee = salaryRepositoryService.findById(id);
		log.info("findById dataOut => employee: {}", employee);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@GetMapping("/employee/search/{data}")
	public ResponseEntity<List<Employee>> simpleSearch(@PathVariable String data) {
		log.info("simpleSearch dataIn => data: {}", data);
		List<Employee> employees = salaryRepositoryService.simpleSearch(data);
		log.info("simpleSearch dataOut => employees: {}", employees);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	@GetMapping("/employee/search")
	public ResponseEntity<List<Employee>> searchBySalary(@PathParam("minSalary") Double minSalary, @PathParam("maxSalary") Double maxSalary) {
		log.info("searchBySalary dataIn => minSalary: {}, maxSalary: {}", minSalary, maxSalary);
		List<Employee> employees = salaryRepositoryService.searchBySalary(minSalary, maxSalary);
		log.info("searchBySalary dataOut => employees: {}", employees);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
}
