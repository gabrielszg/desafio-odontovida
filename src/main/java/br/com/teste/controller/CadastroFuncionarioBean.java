package br.com.teste.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.teste.model.Funcionario;
import br.com.teste.service.CadastroException;
import br.com.teste.service.FuncionarioService;
import br.com.teste.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFuncionarioBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private FuncionarioService funcionarioService;
	
	private Funcionario funcionario;
	
	
	@PostConstruct
	public void init() {
		System.out.println("CadastroFuncionarioBean.init()");
		String idFuncionarioStr = FacesUtil.getParameter("idFuncionario");
		
		if(StringUtils.isNotBlank(idFuncionarioStr)) {
			Integer idFuncionario = Integer.parseInt(idFuncionarioStr);
			this.funcionario = funcionarioService.findById(idFuncionario);
			
			System.out.println("Editando o funcionario " + funcionario.getNome());			
		} else {
			this.funcionario = new Funcionario();
			System.out.println("Criando um novo funcionario.");
		}
		
	}
	
	public boolean isNewEmployer( ) {
		return !(funcionario != null && funcionario.getIdFuncionario() != null);
	}
	
	public void salvar() {
		System.out.println("CadastroFuncionarioBean.salvar()");
		
		try {
			this.funcionario = funcionarioService.salvar(funcionario);
		} catch (CadastroException e) {
			FacesUtil.addErrorMessage("Erro ao cadastrar o funcionario.");
			e.printStackTrace();
		}
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
}
