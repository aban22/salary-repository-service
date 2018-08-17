package com.abeldevelop.salary.salaryrepositoryservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.abeldevelop.salary.salaryrepositoryservice.service.SalaryRepositoryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
public class SalaryRepositoryController {

	private final SalaryRepositoryService salaryRepositoryService;
	
	
}
