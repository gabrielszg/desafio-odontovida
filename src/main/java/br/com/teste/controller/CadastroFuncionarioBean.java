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

	private Funcionario funcionario;

	@Inject
	private FuncionarioService funcionarioService;

	@PostConstruct
	public void init() {
		System.out.println("FuncionarioBean.init()");

		String idFuncionarioStr = FacesUtil.getParameter("funcionario");

		if (StringUtils.isNotBlank(idFuncionarioStr)) {
			Integer idFuncionario = Integer.parseInt(idFuncionarioStr);
			this.funcionario = funcionarioService.findById(idFuncionario);

			System.out.println("Editando o funcionário " + funcionario.getNome());
		} else {
			this.funcionario = new Funcionario();
			System.out.println("Cadastrando novo funcionario.");
		}
	}

	public boolean isNewEmployer() {
		return !(funcionario != null && funcionario.getIdFuncionario() != null);
	}

	public void salvar() {
		try {
			this.funcionario = funcionarioService.salvar(this.funcionario);
			
			FacesUtil.addInfoMessage("Funcionário cadastrado com sucesso!");
		} catch (CadastroException ce) {
			FacesUtil.addErrorMessage("Erro ao cadastrar o funcionario. Idade menor que 18 anos!");
			ce.printStackTrace();
		}
	}

	public void limpar() {
		this.funcionario = new Funcionario();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
