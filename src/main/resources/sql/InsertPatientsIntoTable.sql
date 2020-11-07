/**
 * Author:  Davi-PC
 * Created: Nov 6, 2020
 */

INSERT INTO patients (
    nome, cpf, rg, data_nasc, idade, cor, altura, peso, tipo_sanguineo, signo, celular, telefone_fixo, mae, pai, email, senha, numero, cep, cidade, uf, bairro, rua, complemento
)
VALUES (
    '${exchangeProperty.nome}',
    '${exchangeProperty.cpf}',
    '${exchangeProperty.rg}',
    DATE_FORMAT(STR_TO_DATE('${exchangeProperty.data_nasc}', '%d/%m/%Y'), '%Y-%m-%d'),
    '${exchangeProperty.idade}',
    '${exchangeProperty.cor}',
    '${exchangeProperty.altura}',
    '${exchangeProperty.peso}',
    '${exchangeProperty.tipo_sanguineo}',
    '${exchangeProperty.signo}',
    '${exchangeProperty.celular}',
    '${exchangeProperty.telefone_fixo}',
    '${exchangeProperty.mae}',
    '${exchangeProperty.pai}',
    '${exchangeProperty.email}',
    '${exchangeProperty.senha}',
    '${exchangeProperty.numero}',
    '${exchangeProperty.cep}',
    '${exchangeProperty.cidade}',
    '${exchangeProperty.uf}',
    '${exchangeProperty.bairro}',
    '${exchangeProperty.rua}',
    '${exchangeProperty.complemento}'
)
