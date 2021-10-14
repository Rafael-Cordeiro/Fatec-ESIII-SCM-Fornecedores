package com.temperosoft.scmfornecedores.core.dao;

import java.util.stream.Stream;

import com.dvsmedeiros.bce.core.dao.IDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.temperosoft.scmfornecedores.domain.ScmFornecedores;

public interface IScmFornecedoresDAO extends IDAO<ScmFornecedores> {

	public Stream<ScmFornecedores> filter(Filter<ScmFornecedores> filter);
}
