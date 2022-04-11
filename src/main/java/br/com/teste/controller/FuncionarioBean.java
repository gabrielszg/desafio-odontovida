package br.com.teste.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.teste.model.Funcionario;
import br.com.teste.repository.Funcionarios;
import br.com.teste.repository.filter.FuncionarioFilter;
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
	
	@Inject
	private Funcionarios funcionarios;
	
	private FuncionarioFilter filtro;
	
	private List<Funcionario> funcionarioList;
	

	public FuncionarioBean() {
		filtro = new FuncionarioFilter();
		funcionario = new Funcionario();
	}

	public void limpar() {
		funcionario = new Funcionario();
	}

	public void salvar() {
		try {
			this.funcionario = funcionarioService.salvar(this.funcionario);
			limpar();

			FacesUtil.addInfoMessage("Funcionário cadastrado com sucesso!");
		} catch (CadastroException ce) {
			FacesUtil.addErrorMessage(ce.getMessage());
		}
	}
	
	public void pesquisar() {
		funcionarioList = funcionarios.buscarTodos(filtro);
	}
	
	public void excluir() {
		System.out.println("FuncionarioBean.excluir()");
		
		try {
			funcionarios.remover(funcionario);
			funcionarioList.remove(funcionario);
			
			FacesUtil.addInfoMessage("Funcionário " + funcionario.getCpf() 
			+ " excluído com sucesso.");
		} catch (CadastroException e) {
			e.printStackTrace();
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
	
	public List<Funcionario> getFuncionarioList() {
		return funcionarioList;
	}

	public void setFuncionarioList(List<Funcionario> funcionarioList) {
		this.funcionarioList = funcionarioList;
	}

	public FuncionarioFilter getFiltro() {
		return filtro;
	}
}
