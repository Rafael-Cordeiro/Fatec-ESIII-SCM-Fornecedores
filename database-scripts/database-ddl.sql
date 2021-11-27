-- POSTGRESQL


-- 1
CREATE TABLE PAISES (
	pai_id            SERIAL       NOT NULL,
	pai_codigo        CHAR(3)      NOT NULL,
	pai_descricao     VARCHAR(255) NOT NULL,
	pai_tipo_cadastro CHAR(1)      NOT NULL,
	CONSTRAINT PK_PAI PRIMARY KEY (pai_id),
	CONSTRAINT UK_PAI_01 UNIQUE (pai_codigo),
	CONSTRAINT CK_PAI_01 CHECK (pai_tipo_cadastro IN ('R','A','I'))
);


-- 2
CREATE TABLE ESTADOS (
	est_id            SERIAL       NOT NULL,
	est_codigo        CHAR(2)      NOT NULL,
	est_descricao     VARCHAR(255) NOT NULL,
	est_pai_id        INTEGER      NOT NULL,
	est_tipo_cadastro CHAR(1)      NOT NULL,
	CONSTRAINT PK_EST PRIMARY KEY (est_id),
	CONSTRAINT FK_EST_PAI FOREIGN KEY (est_pai_id)
		REFERENCES PAISES (pai_id),
	CONSTRAINT UK_EST_01 UNIQUE (est_codigo),
	CONSTRAINT CK_EST_01 CHECK (est_tipo_cadastro IN ('R','A','I'))
);


-- 3
CREATE TABLE CIDADES (
	cid_id            SERIAL       NOT NULL,
	cid_codigo        CHAR(8)      NOT NULL,
	cid_descricao     VARCHAR(255) NOT NULL,
	cid_est_id        INTEGER      NOT NULL,
	cid_tipo_cadastro CHAR(1)      NOT NULL,
	CONSTRAINT PK_CID PRIMARY KEY (cid_id),
	CONSTRAINT FK_CID_EST FOREIGN KEY (cid_est_id)
		REFERENCES ESTADOS (est_id),
	CONSTRAINT UK_CID_01 UNIQUE (cid_codigo),
	CONSTRAINT CK_CID_01 CHECK (cid_tipo_cadastro IN ('R','A','I'))
);

-- 4
CREATE TABLE FORNECEDORES (
	for_id              SERIAL       NOT NULL,
	for_razao_social    VARCHAR(255) NOT NULL,
	for_nome_fantasia   VARCHAR(255) NOT NULL,
	for_codigo          VARCHAR(20)  NOT NULL,
	for_tipo_endereco   CHAR(1)      NOT NULL,
	for_logradouro      VARCHAR(20)  NOT NULL,
	for_tipo_logradouro VARCHAR(20)  NOT NULL,
	for_numero          VARCHAR(6)   NOT NULL,
	for_bairro          VARCHAR(20)  NOT NULL,
	for_cep             VARCHAR(10)  NOT NULL,
	for_complemento     VARCHAR(20)  NOT NULL,
	for_cid_id          INTEGER      NOT NULL,
	for_tipo_cadastro   CHAR(1)      NOT NULL,
	CONSTRAINT PK_FOR PRIMARY KEY (for_id),
	CONSTRAINT FK_FOR_CID FOREIGN KEY (for_cid_id)
		REFERENCES CIDADES (cid_id),
	CONSTRAINT UK_FOR_01 UNIQUE (for_codigo),
	CONSTRAINT CK_FOR_01 CHECK (for_tipo_cadastro IN ('R','A','I')),
	CONSTRAINT CK_FOR_02 CHECK (for_tipo_endereco IN ('G','S','E'))
);

-- 5
CREATE TABLE CONTATOS (
	con_id            SERIAL       NOT NULL,
	con_ddi           VARCHAR(4)   NOT NULL,
	con_ddd           VARCHAR(3)   NOT NULL,
	con_telefone      VARCHAR(12)  NOT NULL,
	con_ramal         VARCHAR(12)  NOT NULL,
	con_nome          VARCHAR(255) NOT NULL,
	con_email         VARCHAR(255) NOT NULL,
	con_departamento  VARCHAR(255) NOT NULL,
	con_tipo_telefone VARCHAR(255) NOT NULL,
	con_for_id        INTEGER      NOT NULL,
	con_tipo_cadastro CHAR(1)      NOT NULL,
	CONSTRAINT PK_CON PRIMARY KEY (con_id),
	CONSTRAINT FK_CON_FOR FOREIGN KEY (con_for_id)
		REFERENCES FORNECEDORES (for_id),
	CONSTRAINT CK_CON_01 CHECK (con_tipo_cadastro IN ('R','A','I'))
);

-- 5
CREATE TABLE DOCUMENTOS (
	doc_id     SERIAL         NOT NULL,
	doc_codigo VARCHAR(255)   NOT NULL,
	doc_tipo   VARCHAR(20)    NOT NULL,
	doc_for_id INTEGER        NOT NULL,
	doc_tipo_cadastro CHAR(1) NOT NULL,
	CONSTRAINT PK_DOC PRIMARY KEY (doc_id),
	CONSTRAINT FK_DOC_FOR FOREIGN KEY (doc_for_id)
		REFERENCES FORNECEDORES (for_id),
	CONSTRAINT UK_DOC_01 UNIQUE (doc_codigo),
	CONSTRAINT CK_DOC_01 CHECK (doc_tipo_cadastro IN ('R','A','I'))
);

-- 6
CREATE TABLE FORNECEDORES_FORNECIVEIS (
	for_fnv_id      SERIAL  NOT NULL,
	for_fnv_for_id  INTEGER NOT NULL,
	for_fnv_fnv_id  INTEGER NOT NULL,
	for_fnv_data_inicio DATE,
	CONSTRAINT PK_FOR_FNV PRIMARY KEY (for_fnv_id),
	CONSTRAINT FK_FOR_FNV_FOR FOREIGN KEY (for_fnv_for_id)
		REFERENCES FORNECEDORES (for_id),
	CONSTRAINT FK_FOR_FNV_FNV FOREIGN KEY (for_fnv_fnv_id)
		REFERENCES FORNECIVEIS (fnv_id)
);

-- 5
CREATE TABLE FORNECIVEIS (
	fnv_id              SERIAL       NOT NULL,
	fnv_codigo          VARCHAR(20)  NOT NULL,
	fnv_descricao       VARCHAR(255) NOT NULL,
	fnv_tipo            CHAR(1)      NOT NULL,
	fnv_tipo_cadastro   CHAR(1)      NOT NULL,
	CONSTRAINT PK_FNV PRIMARY KEY (fnv_id),
	CONSTRAINT UK_FNV_01 UNIQUE (fnv_codigo),
	CONSTRAINT CK_FNV_01 CHECK (fnv_tipo_cadastro IN ('R','A','I')),
	CONSTRAINT CK_FNV_02 CHECK (fnv_tipo IN ('P','S'))
);
