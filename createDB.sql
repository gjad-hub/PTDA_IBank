create database if not exists IbankDB;
use IbankDB;

-- Tabela Cliente
CREATE TABLE cliente (
    num_cliente INT PRIMARY KEY,
    nome VARCHAR(255),
    morada VARCHAR(255),
    email VARCHAR(255),
    telemovel VARCHAR(15),
    nif VARCHAR(9),
    password VARCHAR(255),
    num_conta VARCHAR(255) UNIQUE NOT NULL,
    saldo DECIMAL(10,2)
);

-- Tabela Cartao
CREATE TABLE cartao (
    num_cartao INT PRIMARY KEY,
    data_validade DATE,
    estado VARCHAR(50),
    conta VARCHAR(255),
    FOREIGN KEY (conta) REFERENCES cliente(num_conta)
);

-- Tabela Credito
CREATE TABLE credito (
    num_cartao INT,
    data_vencimento DATE,
    saldo_limite DECIMAL(10,2),
    PRIMARY KEY (num_cartao),
    FOREIGN KEY (num_cartao) REFERENCES cartao(num_cartao)
);

-- Tabela Pagamento_Servicos_Compras
CREATE TABLE pagamento_servicos_compras (
    referencia INT PRIMARY KEY,
    entidade INT,
    valor DECIMAL(10,2),
    estado VARCHAR(50),
    cliente INT,
    FOREIGN KEY (cliente) REFERENCES cliente(num_cliente)
);

-- Tabela Tranferencia
CREATE TABLE transferencia (
    id_transferencia INT PRIMARY KEY,
    valor DECIMAL(10,2),
    cliente_realiza INT,
    cliente_recebe INT,
    FOREIGN KEY (cliente_realiza) REFERENCES cliente(num_cliente),
    FOREIGN KEY (cliente_recebe) REFERENCES cliente(num_cliente)
);

-- Tabela Funcionario
CREATE TABLE funcionario (
    num_fun INT PRIMARY KEY,
    nome VARCHAR(255),
    morada VARCHAR(255),
    email VARCHAR(255),
    telemovel VARCHAR(15),
    nif VARCHAR(9),
    password VARCHAR(255),
    gerente INT,
    FOREIGN KEY (gerente) REFERENCES funcionario(num_fun)
);

-- Tabela Funcionario_Cliente
CREATE TABLE funcionario_cliente (
    num_fun INT,
    num_cli INT,
    PRIMARY KEY (num_fun, num_cli),
    FOREIGN KEY (num_fun) REFERENCES funcionario(num_fun),
    FOREIGN KEY (num_cli) REFERENCES cliente(num_cliente)
);

-- Tabela Deposito
CREATE TABLE deposito (
    id_deposito INT PRIMARY KEY,
    valor DECIMAL(10,2),
    estado VARCHAR(50),
    num_fun INT,
    num_cli INT,
    FOREIGN KEY (num_fun) REFERENCES funcionario(num_fun),
    FOREIGN KEY (num_cli) REFERENCES cliente(num_cliente)
);

