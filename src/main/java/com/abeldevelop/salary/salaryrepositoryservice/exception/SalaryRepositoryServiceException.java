package com.abeldevelop.salary.salaryrepositoryservice.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SalaryRepositoryServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final List<String> messages;

	public SalaryRepositoryServiceException(String message) {
		messages = new ArrayList<>();
		messages.add(message);
	}
	
	public SalaryRepositoryServiceException(List<String> messages) {
		this.messages = new ArrayList<>(messages);
		this.messages.addAll(messages);
	}

	public List<String> getMessages() {
		return messages;
	}

}
