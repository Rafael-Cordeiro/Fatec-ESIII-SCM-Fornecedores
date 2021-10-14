package com.temperosoft.scmfornecedores.core.dao.impl;

import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.google.common.base.Strings;
import com.temperosoft.scmfornecedores.core.dao.IScmFornecedoresDAO;
import com.temperosoft.scmfornecedores.domain.ScmFornecedores;

@Component
public class ScmFornecedoresDAO extends GenericDAO<ScmFornecedores> implements IScmFornecedoresDAO {

	@Override
	public Stream<ScmFornecedores> filter(Filter<ScmFornecedores> filter) {

		boolean hasFilter = filter != null && filter.getEntity() != null;

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT c FROM ");
		jpql.append(ScmFornecedores.class.getName());
		jpql.append(" c ");
		jpql.append(" WHERE 1=1 ");

		if (hasFilter && !Strings.isNullOrEmpty(filter.getEntity().getCode())) {
			jpql.append("AND c.code = :code ");
		}

		TypedQuery<ScmFornecedores> query = em.createQuery(jpql.toString(), ScmFornecedores.class);

		if (hasFilter && !Strings.isNullOrEmpty(filter.getEntity().getCode())) {
			jpql.append("AND c.code = :code ");
			query.setParameter("code", filter.getEntity().getCode());
		}

		return Optional.ofNullable(query.getResultList().stream()).orElse(Stream.of());
	}
}
