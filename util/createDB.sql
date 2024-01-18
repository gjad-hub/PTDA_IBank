-- Tabela Cliente
CREATE TABLE cliente
(
    num_cliente    INT PRIMARY KEY AUTO_INCREMENT,
    nome           VARCHAR(255),
    morada         VARCHAR(255),
    email          VARCHAR(255) UNIQUE NOT NULL,
    telemovel      VARCHAR(15),
    nif            VARCHAR(9),
    password       VARCHAR(255)        NOT NULL,
    num_conta      VARCHAR(255) UNIQUE NOT NULL,
    saldo          DECIMAL(10, 2) DEFAULT 0,
    saldo_cativo   DECIMAL(10, 2) DEFAULT 0,
    cartao_default VARCHAR(255),
    eliminado      BOOLEAN DEFAULT 0,
    data_criacao   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    entidade       INT
);

-- Tabela Cartao
CREATE TABLE cartao
(
    num_cartao    VARCHAR(255) PRIMARY KEY,
    data_validade DATE,
    estado        VARCHAR(50),
    cliente       INT,
    FOREIGN KEY (cliente) REFERENCES cliente (num_cliente)
);

ALTER TABLE cliente
    ADD FOREIGN KEY (cartao_default) REFERENCES cartao (num_cartao);

-- Tabela Pagamento_Servicos_Compras
CREATE TABLE pagamento_servicos_compras
(
    entidade     INT,
    referencia   INT,
    valor        DECIMAL(10, 2),
    pago         BOOLEAN,
    cliente      INT,
    cliente_cria INT,
    cancelada    BOOLEAN,
    data_cri     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (entidade, referencia),
    FOREIGN KEY (cliente) REFERENCES cliente (num_cliente),
    FOREIGN KEY (cliente_cria) REFERENCES cliente(num_cliente)
);

-- Tabela Tranferencia
CREATE TABLE transferencia
(
    id_transferencia INT PRIMARY KEY AUTO_INCREMENT,
    valor            DECIMAL(10, 2),
    cliente_realiza  INT,
    cliente_recebe   INT,
    motivo           VARCHAR(200),
    FOREIGN KEY (cliente_realiza) REFERENCES cliente (num_cliente),
    FOREIGN KEY (cliente_recebe) REFERENCES cliente (num_cliente)
);

-- Tabela Funcionario
CREATE TABLE funcionario
(
    num_fun   INT PRIMARY KEY AUTO_INCREMENT,
    nome      VARCHAR(255),
    morada    VARCHAR(255),
    email     VARCHAR(255),
    telemovel VARCHAR(15),
    nif       VARCHAR(9),
    password  VARCHAR(255),
    gerente   INT,
    demitido        BOOLEAN DEFAULT 0,
    data_criacao    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (gerente) REFERENCES funcionario (num_fun)
);

-- Tabela Funcionario_Cliente
CREATE TABLE funcionario_cliente
(
    id      Integer PRIMARY KEY AUTO_INCREMENT,
    num_fun INT,
    num_cli INT,
    FOREIGN KEY (num_fun) REFERENCES funcionario (num_fun),
    FOREIGN KEY (num_cli) REFERENCES cliente (num_cliente)
);

-- Tabela Deposito
CREATE TABLE deposito
(
    id_deposito INT PRIMARY KEY AUTO_INCREMENT,
    valor       DECIMAL(10, 2),
    aprovado    BOOLEAN,
    pendente_aprovacao BOOLEAN NOT NULL DEFAULT 1 ,
    num_fun     INT,
    num_cli     INT,
    data        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (num_fun) REFERENCES funcionario (num_fun),
    FOREIGN KEY (num_cli) REFERENCES cliente (num_cliente)
);
alter table deposito add CONSTRAINT check_approved CHECK 
    ((aprovado = 1 AND num_fun IS NOT NULL) OR aprovado = 0);

-- Tabela transacoes
CREATE TABLE transacoes
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    num_cli   INT       NOT NULL,
    descricao VARCHAR(50),
    valor     DECIMAL(10, 2),
    data      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (num_cli) REFERENCES cliente (num_cliente)
);

-- Tabela Comentarios
CREATE TABLE comentario_perfil
(
    id           INTEGER AUTO_INCREMENT PRIMARY KEY,
    id_empregado INTEGER references funcionario (num_fun),
    id_cliente   INTEGER references cliente (num_cliente),
    descricao    VARCHAR(255),
    data         TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
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
    DECLARE saldo DECIMAL(10, 2);

    -- Cria mais alguma segurança no trigger
    SELECT COUNT(*) INTO existe FROM cliente WHERE num_cliente = NEW.cliente;
    SELECT cliente.saldo INTO saldo FROM cliente WHERE num_cliente = NEW.cliente;

    IF existe > 0 THEN
        IF saldo >= NEW.valor THEN
            INSERT INTO transacoes (num_cli, descricao, valor)
            VALUES (NEW.cliente, "Pagamento serviços", 0 - NEW.valor);

            INSERT INTO transacoes (num_cli, descricao, valor)
            VALUES (NEW.cliente_cria, "Pagamento serviços", NEW.valor);
        END IF;
    END IF;
END;

CREATE PROCEDURE fazer_transferencia(IN cliente_envia INTEGER, IN cliente_recetor INTEGER, IN valor_trans DECIMAL(10,2), IN descricao VARCHAR(255))
BEGIN
        DECLARE EXIT HANDLER FOR SQLEXCEPTION
            BEGIN
                ROLLBACK;
                RESIGNAL;
            END;
        START TRANSACTION;
            INSERT INTO transferencia (valor, cliente_realiza, cliente_recebe, motivo) VALUES (valor_trans, cliente_envia, cliente_recetor,descricao);

            INSERT INTO transacoes (num_cli, descricao, valor) VALUES (cliente_envia, "Transferencia", 0 - valor_trans);
            INSERT INTO transacoes (num_cli, descricao, valor) VALUES (cliente_recetor, "Transferencia", valor_trans);
        COMMIT;
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

DELIMITER \\

-- Procedure aprovar deposito
CREATE PROCEDURE aprovar_deposito(IN depostio INTEGER, IN funcionario INTEGER)
BEGIN
        DECLARE EXIT HANDLER FOR SQLEXCEPTION
            BEGIN
                ROLLBACK;
                RESIGNAL;
            END;
        START TRANSACTION;
            UPDATE cliente SET saldo_cativo = saldo_cativo - (select valor from deposito where id_deposito = depostio)
                           WHERE num_cliente = (SELECT num_cli from deposito where id_deposito = depostio);

            UPDATE deposito SET aprovado = true, num_fun = funcionario, pendente_aprovacao = false WHERE id_deposito = depostio;

            INSERT INTO funcionario_cliente (num_fun, num_cli)
            VALUES (funcionario, (SELECT num_cli from deposito where id_deposito = depostio));

            INSERT INTO transacoes (num_cli, descricao, valor)
            VALUES ((SELECT num_cli from deposito where id_deposito = depostio), "Depostio", (select valor from deposito where id_deposito = depostio));
        COMMIT;
END;

CREATE PROCEDURE reprovar_deposito(IN depostio INTEGER, IN funcionario INTEGER)
BEGIN
    UPDATE cliente SET saldo_cativo = saldo_cativo - (select valor from deposito where id_deposito = depostio) WHERE num_cliente = (SELECT num_cli from deposito where id_deposito = depostio);

    UPDATE deposito SET aprovado = false, num_fun = funcionario, pendente_aprovacao = false WHERE id_deposito = depostio;

    INSERT INTO funcionario_cliente (num_fun, num_cli) VALUES (funcionario, (SELECT num_cli from deposito where id_deposito = depostio));
END;

\\
DELIMITER ;
