package br.com.teste.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(precision = 11)
	private Integer idFuncionario;
	@Column(nullable = false, length = 100)
	private String nome;
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataNascimento;
	@Column(nullable = false, unique = true, length = 14)
	private String cpf;
	@Column(nullable = false, precision = 10, scale = 2)
	private Double salario;
	
}