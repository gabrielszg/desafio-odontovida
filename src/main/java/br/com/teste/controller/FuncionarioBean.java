package br.com.teste.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.teste.model.Funcionario;
import br.com.teste.service.CadastroException;
import br.com.teste.service.FuncionarioService;
import br.com.teste.util.jsf.FacesUtil;

@Named
@ViewScoped
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;
	
	@Inject
	private FuncionarioService funcionarioService;
	
	public FuncionarioBean() {
		limpar();
	}
	
	public void limpar() {
		funcionario = new Funcionario();
	}
	
	public void salvar() {
		try {
			this.funcionario = funcionarioService.salvar(this.funcionario);
			limpar();
			
			FacesUtil.addInfoMessage("Funcionário cadastrado com sucesso!");
		}catch (CadastroException ce) {
			FacesUtil.addErrorMessage(ce.getMessage());
		}
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}

	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}
}
