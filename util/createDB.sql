create database if not exists IbankDB;
use IbankDB;

-- Tabela Cliente
CREATE TABLE cliente (
    num_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255),
    morada VARCHAR(255),
    email VARCHAR(255),
    telemovel VARCHAR(15),
    nif VARCHAR(9),
    password VARCHAR(255),
    num_conta VARCHAR(255) UNIQUE NOT NULL,
    saldo DECIMAL(10,2) DEFAULT 0,
	saldo_cativo DECIMAL(10,2) DEFAULT 0
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
    id_transferencia INT PRIMARY KEY AUTO_INCREMENT,
    valor DECIMAL(10,2),
    cliente_realiza INT,
    cliente_recebe INT,
    FOREIGN KEY (cliente_realiza) REFERENCES cliente(num_cliente),
    FOREIGN KEY (cliente_recebe) REFERENCES cliente(num_cliente)
);

-- Tabela Funcionario
CREATE TABLE funcionario (
    num_fun INT PRIMARY KEY AUTO_INCREMENT,
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
    id_deposito INT PRIMARY KEY AUTO_INCREMENT,
    valor DECIMAL(10,2),
    estado VARCHAR(50),
    num_fun INT,
    num_cli INT,
    FOREIGN KEY (num_fun) REFERENCES funcionario(num_fun),
    FOREIGN KEY (num_cli) REFERENCES cliente(num_cliente)
);

-- Tabela transacoes
CREATE TABLE transacoes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    num_cli INT NOT NULL,
    descricao VARCHAR(50),
    valor DECIMAL(10,2),
    FOREIGN KEY (num_cli) REFERENCES cliente(num_cliente)
);

-- Trigger deposito
CREATE TRIGGER deposito AFTER INSERT ON deposito FOR EACH ROW 
BEGIN
    DECLARE existe INT;

	-- Cria mais alguma segurança no trigger
    SELECT COUNT(*) INTO existe FROM cliente WHERE num_cliente = NEW.num_cli;
    
    IF existe > 0 THEN
		UPDATE cliente SET saldo_cativo = NEW.valor WHERE num_cliente = NEW.num_cli;
    END IF;
END;

-- Trigger pagar serviço
CREATE TRIGGER pagar_servico AFTER INSERT ON pagamento_servicos_compras FOR EACH ROW 
BEGIN
    DECLARE existe INT;
	DECLARE saldo INT;

	-- Cria mais alguma segurança no trigger
    SELECT COUNT(*) INTO existe FROM cliente WHERE num_cliente = NEW.cliente;
	SELECT saldo INTO saldo FROM cliente WHERE num_cliente = NEW.cliente;
    
    IF existe > 0 THEN
		IF saldo >= NEW.valor THEN 
			INSERT INTO transacoes (num_cli, descricao, valor) VALUES (NEW.cliente, "Pagamento serviços", 0 - NEW.valor);
		END IF;
    END IF;
END;

-- Trigger transferencia
CREATE TRIGGER do_transferencia AFTER INSERT ON transferencia FOR EACH ROW 
BEGIN
    DECLARE existe INT;
	DECLARE saldo INT;

	-- Cria mais alguma segurança no trigger
    SELECT COUNT(*) INTO existe FROM cliente WHERE num_cliente = NEW.cliente_realiza;
	SELECT saldo INTO saldo FROM cliente WHERE num_cliente = NEW.cliente;
    
    IF existe > 0 THEN
		IF saldo >= NEW.valor THEN 
			INSERT INTO transacoes (num_cli, descricao, valor) VALUES (NEW.cliente_realiza, "Transferencia", 0 - NEW.valor);
			INSERT INTO transacoes (num_cli, descricao, valor) VALUES (NEW.cliente_recebe, "Transferencia", NEW.valor);
		END IF;
    END IF;
END;

-- Trigger para actualizar o saldo do cliente
CREATE TRIGGER actualizar_saldo AFTER INSERT ON transacoes FOR EACH ROW 
BEGIN
    DECLARE existe INT;

	-- Cria mais alguma segurança no trigger
    SELECT COUNT(*) INTO existe FROM cliente WHERE num_cliente = NEW.num_cli;
    
    IF existe > 0 THEN
        UPDATE cliente SET saldo = saldo + NEW.valor WHERE num_cliente = NEW.num_cli;
    END IF;
END;


