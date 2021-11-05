INSERT INTO FORNECEDORES (
	for_razao_social, 
 	for_nome_fantasia, 
	for_codigo, 
	for_tipo_endereco, 
	for_logradouro, 
	for_tipo_logradouro,
	for_numero,
	for_bairro,
	for_cep,
	for_complemento,
	for_cid_id,
	for_tipo_cadastro
) VALUES
('FOOrnecedor Ltda', 'FOOrnecedor', 'FOO-MOCK-29102021', 'E', 'Avenida Foo', 'Avenida',500, 'Vila Mock', '12345678','3º andar',4463,'R');

SELECT * FROM CIDADES
WHERE cid_descricao LIKE '%Mogi%';

SELECT * FROM FORNECEDORES;