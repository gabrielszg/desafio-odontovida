package br.com.teste.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.teste.model.Funcionario;
import br.com.teste.repository.Funcionarios;
import br.com.teste.util.jpa.Transactional;

public class FuncionarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Funcionarios funcionarios;
	
	@Transactional
	public Funcionario salvar(Funcionario funcionario) throws CadastroException {
//		Funcionario funcionarioExistente = funcionarios.porCpf(funcionario.getCpf());
//		
//		if (funcionarioExistente != null && !funcionarioExistente.equals(funcionario)) {
//			throw new CadastroException("Já existe funcionário cadastrado com esse CPF.");
//		}
		
		return funcionarios.guardar(funcionario);
	}
}
