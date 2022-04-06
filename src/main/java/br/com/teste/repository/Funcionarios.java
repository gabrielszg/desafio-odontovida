package br.com.teste.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.teste.model.Funcionario;

public class Funcionarios implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Funcionario guardar(Funcionario funcionario) {
		return manager.merge(funcionario);
	}
	
	public Funcionario porCpf(String cpf) {
		return manager.find(Funcionario.class, cpf);
		
	}
}
