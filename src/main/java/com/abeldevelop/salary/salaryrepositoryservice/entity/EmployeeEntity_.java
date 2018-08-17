package com.abeldevelop.salary.salaryrepositoryservice.entity;

import java.time.LocalDate;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EmployeeEntity.class)
public class EmployeeEntity_ {

	public static volatile SingularAttribute<EmployeeEntity, Long> id;
	public static volatile SingularAttribute<EmployeeEntity, String> nombre;
	public static volatile SingularAttribute<EmployeeEntity, String> apellido;
	public static volatile SingularAttribute<EmployeeEntity, String> email;
	public static volatile SingularAttribute<EmployeeEntity, String> dni;
	public static volatile SingularAttribute<EmployeeEntity, LocalDate> cumpleanos;
	public static volatile SingularAttribute<EmployeeEntity, String> telefono;
	public static volatile SingularAttribute<EmployeeEntity, String> sexo;
	public static volatile SingularAttribute<EmployeeEntity, String> rol;
	public static volatile SingularAttribute<EmployeeEntity, ContractEntity> contrato;
	public static volatile SingularAttribute<EmployeeEntity, String> nombreCompleto;
}
