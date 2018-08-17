package com.abeldevelop.salary.salaryrepositoryservice.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.abeldevelop.salary.salaryrepositoryservice.entity.Contract;
import com.abeldevelop.salary.salaryrepositoryservice.entity.Contract_;
import com.abeldevelop.salary.salaryrepositoryservice.entity.Employee;
import com.abeldevelop.salary.salaryrepositoryservice.entity.Employee_;
import com.abeldevelop.salary.salaryrepositoryservice.repository.SalarySearchRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class SalarySearchRepositoryImpl implements SalarySearchRepository {

	@Autowired
	private EntityManager em;

	@Override
	public List<Employee> simpleSearch(String data) {
		log.info("simpleSearch dataIn => " + data);
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);
		root.fetch(Employee_.contract);
		
		
		Predicate orPredicate = criteriaBuilder.or(
				criteriaBuilder.like(criteriaBuilder.upper(root.get(Employee_.fullName)), "%"+data.toUpperCase()+"%"),
				criteriaBuilder.like(criteriaBuilder.upper(root.get(Employee_.dni)), "%"+data.toUpperCase()+"%")
		);
		criteriaQuery.where(orPredicate);
		
		List<Employee> result = em.createQuery(criteriaQuery).getResultList();
		
		log.info("simpleSearch dataOut => result.size(): {}" + result.size());
		return result;
	}

	@Override
	public List<Employee> searchBySalary(Double minSalary, Double maxSalary) {
		log.info("searchBySalary dataIn => minSalary: {}, maxSalary: {}", minSalary, maxSalary);
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);
		Join<Employee, Contract> join = (Join<Employee, Contract>)root.fetch(Employee_.contract);
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(minSalary) && !StringUtils.isEmpty(maxSalary)) {
			Predicate greaterThanOrEqualTo = criteriaBuilder.greaterThanOrEqualTo(join.get(Contract_.salary), minSalary);
			Predicate lessThanOrEqualTo = criteriaBuilder.lessThanOrEqualTo(join.get(Contract_.salary), maxSalary);
			predicates.add(criteriaBuilder.and(greaterThanOrEqualTo, lessThanOrEqualTo));
		}
		else if(!StringUtils.isEmpty(minSalary) && StringUtils.isEmpty(maxSalary)) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(join.get(Contract_.salary), minSalary));
		}
		else if(StringUtils.isEmpty(minSalary) && !StringUtils.isEmpty(maxSalary)) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(join.get(Contract_.salary), maxSalary));
		}
		else {
			return new ArrayList<>();
		}
		
		criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
		List<Employee> result = em.createQuery(criteriaQuery).getResultList();
		log.info("searchBySalary dataOut => result.size(): {}" + result.size());
		return result;
	}
	
	@Override
	public List<Employee> checkIsEmployeeExist(String dni, String email, String phone) {
		log.info("checkIsEmployeeExist dataIn => dni: {}, email: {}, phone: {}", dni, email, phone);
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);
		List<Predicate> predicates = new ArrayList<>();
		
		Predicate or = criteriaBuilder.or(
				criteriaBuilder.equal(root.get(Employee_.dni), dni),
				criteriaBuilder.equal(root.get(Employee_.email), email),
				criteriaBuilder.equal(root.get(Employee_.phone), phone)
		);
		
		predicates.add(or);
		criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
		
		List<Employee> result = em.createQuery(criteriaQuery).getResultList();
		log.info("searchBySalary dataOut => result.size(): {}" + result.size());
		return result;
	}
}
