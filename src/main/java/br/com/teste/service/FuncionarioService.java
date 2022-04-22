package br.com.teste.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.teste.model.Funcionario;
import br.com.teste.repository.Funcionarios;
import br.com.teste.util.date.DateValidator;
import br.com.teste.util.jpa.Transactional;

public class FuncionarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Funcionarios funcionarios;
	
	@Transactional
	public Funcionario salvar(Funcionario funcionario) throws CadastroException {
		
		Funcionario funcionarioExistente = funcionarios.findByCpf(funcionario.getCpf());
		
		if (funcionarioExistente != null && !funcionarioExistente.equals(funcionario)) {
			throw new CadastroException("Já existe funcionário com o CPF informado");
		}
		
		if (DateValidator.getAge(funcionario.getDataNascimento()) >= 18) {
			return funcionarios.guardar(funcionario);
		}else {
			throw new CadastroException("Funcionario não é maior que 18 anos");
		}
	}
	
	public Funcionario findById(Integer idFuncionario) {
		return funcionarios.findById(idFuncionario);
	}
}
