package br.com.teste.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.teste.model.Funcionario;
import br.com.teste.repository.Funcionarios;

@FacesConverter(forClass = Funcionario.class)
public class FuncionarioConverter implements Converter {
	
	@Inject
	private Funcionarios funcionarios;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("FuncionarioConverter.getAsObject()");
		
		Funcionario retorno = null;
		
		if (StringUtils.isNotEmpty(value)) {
			Integer idFuncionario = new Integer(value);
			retorno = funcionarios.porId(idFuncionario);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Funcionario funcionario = (Funcionario) value;
			return funcionario.getIdFuncionario() == null ? null : funcionario.getIdFuncionario().toString();
		}
		
		return "";
	}
}
