-- Script de comandos DDL para a criação das tabelas das entidades do sistema no banco de dados
-- SGBD: POSTGRESQL
-- Obs: os comentários acima das tabelas com o número indicam a ordem na qual as tabelas devem ser criadas. Atente-se a criar todas as tabelas de mesmo número para, assim, criar as tabelas do número seguinte.

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
	for_codigo          VARCHAR(50)  NOT NULL,
	for_tipo_endereco   CHAR(1)      NOT NULL,
	for_logradouro      VARCHAR(50)  NOT NULL,
	for_tipo_logradouro VARCHAR(50)  NOT NULL,
	for_numero          VARCHAR(6)   NOT NULL,
	for_bairro          VARCHAR(50)  NOT NULL,
	for_cep             VARCHAR(10)  NOT NULL,
	for_complemento     VARCHAR(50),
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
	con_codigo        VARCHAR(50)  NOT NULL,
	con_ddi           VARCHAR(4)   NOT NULL,
	con_ddd           VARCHAR(3)   NOT NULL,
	con_telefone      VARCHAR(20)  NOT NULL,
	con_ramal         VARCHAR(20),
	con_tipo_telefone VARCHAR(255) NOT NULL,
	con_nome          VARCHAR(255) NOT NULL,
	con_email         VARCHAR(255) NOT NULL,
	con_departamento  VARCHAR(255) NOT NULL,
	con_for_id        INTEGER      NOT NULL,
	con_tipo_cadastro CHAR(1)      NOT NULL,
	CONSTRAINT PK_CON PRIMARY KEY (con_id),
	CONSTRAINT FK_CON_FOR FOREIGN KEY (con_for_id)
		REFERENCES FORNECEDORES (for_id),
	CONSTRAINT UK_CON_01 UNIQUE (con_codigo),
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
CREATE TABLE FORNECEDORES_PRODUTOS (
	for_pro_id      SERIAL  NOT NULL,
	for_pro_for_id  INTEGER NOT NULL,
	for_pro_pro_id  INTEGER NOT NULL,
	CONSTRAINT PK_FOR_PRO PRIMARY KEY (for_pro_id),
	CONSTRAINT FK_FOR_PRO_FOR FOREIGN KEY (for_pro_for_id)
		REFERENCES FORNECEDORES (for_id),
	CONSTRAINT FK_FOR_PRO_PRO FOREIGN KEY (for_pro_pro_id)
		REFERENCES PRODUTOS (pro_id)
);

-- 5
CREATE TABLE PRODUTOS (
	pro_id            SERIAL       NOT NULL,
	pro_codigo        VARCHAR(20)  NOT NULL,
	pro_descricao     VARCHAR(255) NOT NULL,
	pro_tipo_cadastro CHAR(1)      NOT NULL,
	CONSTRAINT PK_PRO PRIMARY KEY (pro_id),
	CONSTRAINT UK_PRO_01 UNIQUE (pro_codigo),
	CONSTRAINT CK_PRO_01 CHECK (pro_tipo_cadastro IN ('R','A','I'))
);

-- 5
CREATE TABLE SERVICOS (
	ser_id          SERIAL NOT NULL,
	ser_codigo      VARCHAR(20) NOT NULL,
	ser_descricao   VARCHAR(255) NOT NULL,
	ser_data_inicio DATE NOT NULL,
	ser_for_id      INTEGER NOT NULL,
	CONSTRAINT PK_SER PRIMARY KEY (ser_id),
	CONSTRAINT FK_SER_FOR FOREIGN KEY (ser_for_id)
		REFERENCES FORNECEDORES (for_id)
)