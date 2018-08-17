package com.abeldevelop.salary.salaryrepositoryservice.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import lombok.Getter;
import lombok.Setter;


@StaticMetamodel(ContractEntity.class)
public class ContractEntity_ {

	public static volatile SingularAttribute<ContractEntity, Long> id;
	public static volatile SingularAttribute<ContractEntity, String> categoria;
	public static volatile SingularAttribute<ContractEntity, String> tipoContrato;
	public static volatile SingularAttribute<ContractEntity, LocalDate> fechaInicio;
	public static volatile SingularAttribute<ContractEntity, String> salario;
	public static volatile SingularAttribute<ContractEntity, String> moneda;
	public static volatile SingularAttribute<ContractEntity, LocalDate> fechaFin;
	public static volatile SingularAttribute<ContractEntity, String> cuentaBancaria;
}
