package com.abeldevelop.salary.salaryrepositoryservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.abeldevelop.salary.salaryrepositoryservice.entity.Employee;
import com.abeldevelop.salary.salaryrepositoryservice.exception.SalaryRepositoryServiceException;
import com.abeldevelop.salary.salaryrepositoryservice.repository.SalarySearchRepository;
import com.abeldevelop.salary.salaryrepositoryservice.repository.SalarySpringDataRepository;
import com.abeldevelop.salary.salaryrepositoryservice.service.impl.SalaryRepositoryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SalaryRepositoryServiceImplTest {

	private SalaryRepositoryServiceImpl salaryRepositoryServiceImpl;
	
	@Mock
	private SalarySearchRepository salarySearchRepository;
	
	@Mock
	private SalarySpringDataRepository salarySpringDataRepository;
	
	@Before
	public void setUp() {
		salaryRepositoryServiceImpl = new SalaryRepositoryServiceImpl(salarySearchRepository, salarySpringDataRepository);
	}
	
	@Test
	public void deleteEmployee_ok() {
		Employee employee = new Employee();
		Optional<Employee> optional = Optional.of(employee);
		
		Mockito.when(salarySpringDataRepository.findById(Mockito.anyLong())).thenReturn(optional);
		
		Mockito.doNothing().when(salarySpringDataRepository).deleteById(Mockito.anyLong());
		salaryRepositoryServiceImpl.deleteEmployee(1L);
	}
	
	@Test(expected=SalaryRepositoryServiceException.class)
	public void deleteEmployee_ko() {
		Optional<Employee> optional = Optional.empty();
		Mockito.when(salarySpringDataRepository.findById(Mockito.anyLong())).thenReturn(optional);
		
		salaryRepositoryServiceImpl.deleteEmployee(1L);
	}
	
	@Test
	public void updateEmployee_ok() {
		Employee employee = new Employee();
		employee.setId(1l);
		Optional<Employee> optional = Optional.of(employee);
		
		Mockito.when(salarySpringDataRepository.findById(Mockito.anyLong())).thenReturn(optional);
		
		salaryRepositoryServiceImpl.updateEmployee(employee);
	}
	
	@Test
	public void createEmployee_ok() {
		Employee employee = new Employee();
//		Mockito
//			.when(salarySearchRepository.checkIsEmployeeExist(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
//			.thenReturn(new ArrayList<>());
		
		Mockito.when(salarySpringDataRepository.save(Mockito.any())).thenReturn(new Employee());
		
		salaryRepositoryServiceImpl.createEmployee(employee);
	}
	
	@Test(expected=SalaryRepositoryServiceException.class)
	public void createEmployee_ko() {
		Employee employeeIn = new Employee();
		employeeIn.setPhone("22");
		employeeIn.setDni("22");
		employeeIn.setEmail("22");
		
		List<Employee> employees = new ArrayList<>();
		employees.add(employeeIn);
		Mockito
			.when(salarySearchRepository.checkIsEmployeeExist(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
			.thenReturn(employees);
		
		salaryRepositoryServiceImpl.createEmployee(employeeIn);
	}
}
