# Apagar o banco de dados
# DROP DATABASE IF EXISTS minha_garagem;

# Criar o baco de dados
CREATE DATABASE IF NOT EXISTS minha_garagem;

# SELECIONA O BANCO DE DADOS
USE minha_garagem;

# DELETA A TABELA SE ELA EXISTR
DROP TABLE IF EXISTS carros;

# CRIA A TABLEA SE ELA NAO EXISTIR
CREATE TABLE IF NOT EXISTS carros (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150),
    cor VARCHAR(100),
    fabricante VARCHAR(255),
    placa VARCHAR(8),
    chassi VARCHAR(50),
    quilometragem FLOAT,
    potencia FLOAT,
    data_compra DATE,
    
    esta_funcionando BOOLEAN,
    permitida_circulacao BOOLEAN,
    
    quantidade_portas TINYINT,
    quantidade_batidas TINYINT,
    
    ano_fabricacao SMALLINT,
    ano_lancamento SMALLINT,
    tipo_pneu SMALLINT,
    
    renavam VARCHAR(100),
   
     descricao TEXT
);
# DELETA A TABELA SE ELA EXISTR
DROP TABLE IF EXISTS categorias;
# CRIA A TABLEA SE ELA NAO EXISTIR
CREATE TABLE IF NOT EXISTS categorias(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR (150) UNIQUE NOT NULL, # NÃO PERMITE SER VAZIO E UNICO
    descricao TEXT,
    ativo BOOLEAN DEFAULT FALSE # VAI CONTER O VALOR false CASO NÃO FOR PASSADO NADA NO INSERT

    # UNIQUE (nome, descricao) 
);

CREATE TABLE IF NOT EXISTS  avioes(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_categoria INTEGER NOT NULL,
    FOREIGN KEY (id_categria) REFERENCES categorias(id),
    nome VARCHAR(100) NOT NULL UNIQUE
);

insert into categorias (nome) values
("Baixa"),
("Média"),
("Alta");

INSERT INTO avioes (id_categoria, nome) values
(1,"TecoTeco"),
(2,"Air bus a320"),
(2,"Air bus 777"),
(3,"Air bus 747");