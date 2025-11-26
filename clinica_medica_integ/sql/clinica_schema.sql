SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES';

DROP SCHEMA IF EXISTS clinica_medica;
CREATE SCHEMA IF NOT EXISTS clinica_medica
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_0900_ai_ci;

USE clinica_medica;

CREATE TABLE IF NOT EXISTS paciente (
    id_paciente INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NULL DEFAULT NULL,
    telefone VARCHAR(15) NULL DEFAULT NULL,
    endereco VARCHAR(150) NULL DEFAULT NULL,
    PRIMARY KEY (id_paciente)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS medico (
    id_medico INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    crm VARCHAR(20) NOT NULL,
    telefone VARCHAR(15) NULL DEFAULT NULL,
    email VARCHAR(100) NULL DEFAULT NULL,
    PRIMARY KEY (id_medico),
    UNIQUE INDEX crm (crm ASC)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS especialidade (
    id_especialidade INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(200) NULL DEFAULT NULL,
    PRIMARY KEY (id_especialidade)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS medico_especialidade (
    id_medico INT NOT NULL,
    id_especialidade INT NOT NULL,
    PRIMARY KEY (id_medico, id_especialidade),
    CONSTRAINT fk_medico_especialidade_medico
        FOREIGN KEY (id_medico)
        REFERENCES medico (id_medico)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_medico_especialidade_especialidade
        FOREIGN KEY (id_especialidade)
        REFERENCES especialidade (id_especialidade)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS consulta (
    id_consulta INT NOT NULL AUTO_INCREMENT,
    data DATE NOT NULL,
    hora TIME NOT NULL,
    observacoes VARCHAR(255) NULL DEFAULT NULL,
    id_paciente INT NOT NULL,
    id_medico INT NOT NULL,
    PRIMARY KEY (id_consulta),
    INDEX idx_paciente (id_paciente ASC),
    INDEX idx_medico (id_medico ASC),
    CONSTRAINT fk_consulta_paciente
        FOREIGN KEY (id_paciente)
        REFERENCES paciente (id_paciente)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_consulta_medico
        FOREIGN KEY (id_medico)
        REFERENCES medico (id_medico)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS receita (
    id_receita INT NOT NULL AUTO_INCREMENT,
    descricao VARCHAR(255) NULL DEFAULT NULL,
    data_emissao DATE NULL DEFAULT NULL,
    validade DATE NULL DEFAULT NULL,
    id_consulta INT NOT NULL,
    PRIMARY KEY (id_receita),
    UNIQUE INDEX idx_consulta (id_consulta ASC),
    CONSTRAINT fk_receita_consulta
        FOREIGN KEY (id_consulta)
        REFERENCES consulta (id_consulta)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
