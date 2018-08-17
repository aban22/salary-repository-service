package com.abeldevelop.salary.salaryrepositoryservice.service.impl;

import org.springframework.stereotype.Service;

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
	
}
