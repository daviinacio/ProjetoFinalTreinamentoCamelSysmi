/**
 * Author:  Davi-PC
 * Created: Nov 6, 2020
 */

CREATE TABLE `Patients` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(128) NOT NULL,
    `cpf` VARCHAR(14) NOT NULL,
    `rg` VARCHAR(12) NOT NULL,
    `data_nasc` DATE NOT NULL,
    `idade` INT UNSIGNED NOT NULL DEFAULT 0,
    `cor` VARCHAR(32) NOT NULL DEFAULT '',
    `altura` VARCHAR(16) NOT NULL DEFAULT '',
    `peso` INT UNSIGNED NOT NULL,
    `tipo_sanguineo` VARCHAR(3) NOT NULL DEFAULT '',
    `signo` VARCHAR(16) NOT NULL DEFAULT '',
    `celular` VARCHAR(20) NOT NULL DEFAULT '',
    `telefone_fixo` VARCHAR(20) NOT NULL DEFAULT '',
    `mae` VARCHAR(128) NOT NULL DEFAULT '',
    `pai` VARCHAR(128) NOT NULL DEFAULT '',
    `email` VARCHAR(96) NOT NULL DEFAULT '',
    `senha` VARCHAR(256) NOT NULL DEFAULT '',
    `numero` INT NOT NULL DEFAULT 0,
    `cep` VARCHAR(10) NOT NULL DEFAULT '',
    `cidade` VARCHAR(64) NOT NULL DEFAULT '',
    `uf` VARCHAR(2) NOT NULL DEFAULT '',
    `bairro` VARCHAR(64) NOT NULL DEFAULT '',
    `rua` VARCHAR(64) NOT NULL DEFAULT '',
    `complemento` VARCHAR(128) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
);