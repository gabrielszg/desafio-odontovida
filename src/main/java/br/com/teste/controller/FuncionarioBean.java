package br.com.teste.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;

import br.com.teste.model.Funcionario;
import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
@Getter
@Setter
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Session manager;
	
	@Inject
	private Funcionario funcionario;
	
	public void adicionaFuncionario() {
		manager.persist(funcionario);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "info", "Funcionário cadastrado com sucesso!"));
	}

}
