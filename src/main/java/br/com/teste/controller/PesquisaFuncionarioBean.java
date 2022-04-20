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
import br.com.teste.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Funcionarios funcionarios;
	
	private FuncionarioFilter filtro;
	private List<Funcionario> funcionarioList;
	
	private Funcionario funcionarioSelecionado;
	
	public PesquisaFuncionarioBean() {
		this.filtro = new FuncionarioFilter();
	}
	
	public void pesquisar() {
		funcionarioList = funcionarios.buscarTodos(filtro);
	}
	
	public void excluir() {
		System.out.println("FuncionarioBean.excluir()");
		
		try {
			funcionarios.remover(funcionarioSelecionado);
			funcionarioList.remove(funcionarioSelecionado);
			
			FacesUtil.addInfoMessage("Funcionário " + funcionarioSelecionado.getNome() 
			+ " excluído com sucesso.");
		} catch (CadastroException e) {
			e.printStackTrace();
		}
		
	}

	public Funcionarios getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(Funcionarios funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Funcionario> getFuncionarioList() {
		return funcionarioList;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}
	
	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public FuncionarioFilter getFiltro() {
		return filtro;
	}
	
}
