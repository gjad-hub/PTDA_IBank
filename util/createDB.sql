create database if not exists IbankDB;
use IbankDB;

-- Tabela Cliente
CREATE TABLE cliente (
    num_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255),
    morada VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    telemovel VARCHAR(15),
    nif VARCHAR(9),
    password VARCHAR(255) NOT NULL,
    num_conta VARCHAR(255) UNIQUE NOT NULL,
    saldo DECIMAL(10,2) DEFAULT 0,
	saldo_cativo DECIMAL(10,2) DEFAULT 0,
    cartao_default VARCHAR(255)
);

-- Tabela Cartao
CREATE TABLE cartao (
    num_cartao VARCHAR(255) PRIMARY KEY,
    data_validade DATE,
    estado VARCHAR(50),
    cliente INT,
    credito BOOLEAN,
    saldo_credito DECIMAL(10,2),
    data_vencimento DATE,
    FOREIGN KEY (cliente) REFERENCES cliente(num_cliente)
);

ALTER TABLE cliente ADD FOREIGN KEY (cartao_default) REFERENCES cartao(num_cartao);

-- Tabela Pagamento_Servicos_Compras
CREATE TABLE pagamento_servicos_compras (
    referencia INT,
    entidade INT,
    valor DECIMAL(10,2),
    pago BOOLEAN,
    cliente INT,
    PRIMARY KEY (referencia, entidade),
    FOREIGN KEY (cliente) REFERENCES cliente(num_cliente)
);

-- Tabela Tranferencia
CREATE TABLE transferencia (
    id_transferencia INT PRIMARY KEY AUTO_INCREMENT,
    valor DECIMAL(10,2),
    cliente_realiza INT,
    cliente_recebe INT,
	motivo VARCHAR(200),
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
    aprovado BOOLEAN,
    num_fun INT,
    num_cli INT,
    data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (num_fun) REFERENCES funcionario(num_fun),
    FOREIGN KEY (num_cli) REFERENCES cliente(num_cliente)
);

-- Tabela transacoes
CREATE TABLE transacoes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    num_cli INT NOT NULL,
    descricao VARCHAR(50),
    valor DECIMAL(10,2),
	data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (num_cli) REFERENCES cliente(num_cliente)
);

-- Tabela Comentarios
CREATE TABLE comentario_perfil (
id INTEGER AUTO_INCREMENT PRIMARY KEY,
id_empregado INTEGER references funcionario(num_fun),
id_cliente INTEGER references cliente(num_cliente),
descricao VARCHAR(255),
data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DELIMITER \\

-- Trigger deposito
CREATE TRIGGER deposito AFTER INSERT ON deposito FOR EACH ROW 
BEGIN
    DECLARE existe INT;

	-- Cria mais alguma segurança no trigger
    SELECT COUNT(*) INTO existe FROM cliente WHERE num_cliente = NEW.num_cli;
    
    IF existe > 0 THEN
		UPDATE cliente SET saldo_cativo = saldo_cativo + NEW.valor WHERE num_cliente = NEW.num_cli;
    END IF;
END;

-- Trigger pagar serviço
CREATE TRIGGER pagar_servico AFTER UPDATE ON pagamento_servicos_compras FOR EACH ROW 
BEGIN
    DECLARE existe INT;
	DECLARE saldo DECIMAL(10,2);

	-- Cria mais alguma segurança no trigger
    SELECT COUNT(*) INTO existe FROM cliente WHERE num_cliente = NEW.cliente;
	SELECT cliente.saldo INTO saldo FROM cliente WHERE num_cliente = NEW.cliente;

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
	DECLARE saldo DECIMAL(10,2);

	-- Cria mais alguma segurança no trigger
    SELECT COUNT(*) INTO existe FROM cliente WHERE num_cliente = NEW.cliente_realiza;
	SELECT cliente.saldo INTO saldo FROM cliente WHERE num_cliente = NEW.cliente_realiza;

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

-- Procedure aprovar deposito
CREATE PROCEDURE aprovar_deposito(IN depostio INTEGER, IN funcionario INTEGER)
BEGIN
	UPDATE cliente SET saldo_cativo = saldo_cativo - (select valor from deposito where id_deposito = depostio)
	               WHERE num_cliente = (select num_cli from deposito where id_deposito = depostio);

	UPDATE deposito SET aprovado = true, num_fun = funcionario
	                WHERE id_deposito = depostio;

	INSERT INTO transacoes (num_cli, descricao, valor)
	    VALUES ((SELECT num_cli FROM deposito WHERE id_deposito = depostio),"Depostio",(select valor from deposito where id_deposito = depostio));
END;
\\
DELIMITER ;


-- Dados de teste

-- INSERT INTO cliente (nome, morada, email, telemovel, nif, password, num_conta, saldo) VALUES ('Gonçalo', 'morada', 'goncalojdias@ua.pt', '123456789', '987654321', '1000:2cb67de5732ebf184ea454adc3ff78fb:326e6ab663e6950dca8bd0f7697877d56a2eaacf83a8a271f55701a287dc5f2172456212ba0d292a11f248f310c723b2940aa306e536c5f84e1c111c572d03d2', 'PT50000830064410685224784', 500);
-- INSERT INTO cliente (nome, morada, email, telemovel, nif, password, num_conta, saldo) VALUES ('Ricardo', 'morada', 'ricardo@ua.pt', '321456789', '147587458', '1000:72d16789b31e32b9204e83e46a3bf14e:3e9c60d09bbcad8f3f33086852409a570c8ab3435106599e64444fa44406d260d377aac6fba56f27f61d336d52029018ad07914237ee40dd7041eefe0602409e', 'PT50002329487360799005570', 100);

-- INSERT INTO cliente (nome, morada, email, telemovel, nif, password, num_conta, saldo) VALUES ('Richard', 'morada', 'richard@ua.pt', '321456789', '147587458', '1000:72d16789b31e32b9204e83e46a3bf14e:3e9c60d09bbcad8f3f33086852409a570c8ab3435106599e64444fa44406d260d377aac6fba56f27f61d336d52029018ad07914237ee40dd7041eefe0602409e', 'PT50002329487360799005510', 150);

-- INSERT INTO pagamento_servicos_compras (referencia, entidade, valor, pago, cliente) VALUES (457866148, 124548, 124.70, true, 1);
-- INSERT INTO pagamento_servicos_compras (referencia, entidade, valor, pago, cliente) VALUES (124587963, 124578, 120.00, true, 1);
-- INSERT INTO pagamento_servicos_compras (referencia, entidade, valor, pago, cliente) VALUES (135789032, 245784, 12.00, true, 1);

-- INSERT INTO transferencia (valor, cliente_realiza, cliente_recebe) VALUES (120.00, 1, 3);
