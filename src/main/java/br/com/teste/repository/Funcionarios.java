package br.com.teste.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.teste.model.Funcionario;
import br.com.teste.repository.filter.FuncionarioFilter;
import br.com.teste.service.CadastroException;
import br.com.teste.util.jpa.Transactional;

public class Funcionarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Funcionario guardar(Funcionario funcionario) {
		return manager.merge(funcionario);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Funcionario> buscarTodos(FuncionarioFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Funcionario.class);

		if (StringUtils.isNotBlank(filtro.getCpf())) {
			criteria.add(Restrictions.eq("cpf", filtro.getCpf()));
		}

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		return criteria.list();
	}
	
	@Transactional
	public void remover(Funcionario funcionario) throws CadastroException {
		try {
			funcionario = findById(funcionario.getIdFuncionario());
			manager.remove(funcionario);
			manager.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new CadastroException("Funcionario não pode ser excluído.");
		}

	}

	public Funcionario findById(Integer idFuncionario) {
		return manager.find(Funcionario.class, idFuncionario);
	}
	
	public Funcionario findByCpf(String cpf) {
		try {
			return manager.createQuery("from Funcionario where cpf = :cpf", Funcionario.class)
					.setParameter("cpf", cpf)
					.getSingleResult();
		}catch (NoResultException ne) {
			return null;
		}
	}
}
