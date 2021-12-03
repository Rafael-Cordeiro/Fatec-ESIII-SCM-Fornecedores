CREATE TABLE AUDIT_TABLE (
    aud_id            SERIAL NOT NULL,
    aud_dt_hora       TIMESTAMP NOT NULL,
    aud_evento        CHAR(1) NOT NULL,
    aud_tabela        VARCHAR(30) NOT NULL,
    aud_owner         VARCHAR(30) NOT NULL,
    aud_coluna        VARCHAR(30),
    aud_old           VARCHAR(255),
    aud_new           VARCHAR(255),
    aud_identificador VARCHAR(50) NOT NULL,
    aud_bduser        VARCHAR(30) NOT NULL,
	CONSTRAINT PK_AUD PRIMARY KEY (aud_id),
	CONSTRAINT CK_AUD_01 CHECK (aud_evento IN  ('I','U','D'))
);

CREATE OR REPLACE PROCEDURE INSERT_AUDIT (
    p_aud_dt_hora       TIMESTAMP,
    p_aud_evento        CHAR,
    p_aud_tabela        VARCHAR,
    p_aud_owner         VARCHAR,
    p_aud_coluna        VARCHAR,
    p_aud_old           VARCHAR,
    p_aud_new           VARCHAR,
    p_aud_identificador VARCHAR,
    p_aud_bduser        VARCHAR
)
LANGUAGE SQL
AS $$
	INSERT INTO AUDIT_TABLE (
		aud_dt_hora, 
		aud_evento, 
		aud_tabela, 
		aud_owner, 
		aud_coluna, 
		aud_old, 
		aud_new, 
		aud_identificador, 
		aud_bduser
	) VALUES (
        p_aud_dt_hora,
        p_aud_evento,
        p_aud_tabela,
        p_aud_owner,
        p_aud_coluna,
        p_aud_old,
        p_aud_new,
        p_aud_identificador,
        p_aud_bduser
    );
$$

-- CALL PROC EXAMPLE
CALL INSERT_AUDIT(CURRENT_TIMESTAMP::TIMESTAMP, 'I', 'FUNCIONARIOS', 'SCM', NULL, NULL, NULL,'5',CURRENT_USER::VARCHAR);