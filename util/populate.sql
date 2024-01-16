-- Inserir dados de teste na tabela Cartao
INSERT INTO cartao (num_cartao, data_validade, estado)
VALUES ('4001330931275836', '2026-10-31', 'Ativo');

INSERT INTO cartao (num_cartao, data_validade, estado)
VALUES ('4001333354114391', '2024-06-30', 'Ativo');

INSERT INTO cartao (num_cartao, data_validade, estado)
VALUES ('4001351884567109', '2025-12-31', 'Ativo');

INSERT INTO cartao (num_cartao, data_validade, estado)
VALUES ('4001491015472463', '2024-08-31', 'Ativo');

INSERT INTO cartao (num_cartao, data_validade, estado)
VALUES ('4001733310706027', '2024-04-30', 'Ativo');

INSERT INTO cartao (num_cartao, data_validade, estado)
VALUES ('4001830285782013', '2024-11-30', 'Ativo');

INSERT INTO cartao (num_cartao, data_validade, estado)
VALUES ('4001883484655658', '2024-09-30', 'Ativo');

INSERT INTO cartao (num_cartao, data_validade, estado)
VALUES ('4001596602388724', '2024-10-31', 'Ativo');

INSERT INTO cartao (num_cartao, data_validade, estado)
VALUES ('4001120953203520', '2024-07-31', 'Ativo');


-- Inserir dados de teste na tabela Cliente
INSERT INTO cliente (nome, morada, email, telemovel, nif, password, num_conta, saldo, saldo_cativo, cartao_default, entidade)
VALUES ('Marta Rodrigues', 'Rua G, nº 111', 'marta.rodrigues@email.com', '876543210', '111223344', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', 'PT50003966064989044052174', 0.00, 0.0, '4001330931275836', 789);

INSERT INTO cliente (nome, morada, email, telemovel, nif, password, num_conta, saldo, saldo_cativo, cartao_default, entidade)
VALUES ('Pedro Alves', 'Avenida H, nº 222', 'pedro.alves@email.com', '987654321', '222334455', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', 'PT50000265405973711583183', 0.00, 0.00, '4001333354114391', 456);

INSERT INTO cliente (nome, morada, email, telemovel, nif, password, num_conta, saldo, saldo_cativo, cartao_default, entidade)
VALUES ('Sara Silva', 'Rua I, nº 333', 'sara.silva@email.com', '876543210', '333445566', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', 'PT50005620984363163946165', 0.00, 0.00, '4001351884567109', 123);

INSERT INTO cliente (nome, morada, email, telemovel, nif, password, num_conta, saldo, saldo_cativo, cartao_default, entidade)
VALUES ('Hugo Oliveira', 'Avenida J, nº 444', 'hugo.oliveira@email.com', '987654321', '444556677', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', 'PT50007884763541403081684', 0.00, 0.00, '4001491015472463', 789);

INSERT INTO cliente (nome, morada, email, telemovel, nif, password, num_conta, saldo, saldo_cativo, cartao_default, entidade)
VALUES ('Inês Pereira', 'Rua K, nº 555', 'ines.pereira@email.com', '876543210', '555667788', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', 'PT50005637932098875504569', 0.00, 0.00, '4001733310706027', 456);

UPDATE cartao SET cliente = 1 WHERE num_cartao = '4001330931275836';
UPDATE cartao SET cliente = 2 WHERE num_cartao = '4001333354114391';
UPDATE cartao SET cliente = 3 WHERE num_cartao = '4001351884567109';
UPDATE cartao SET cliente = 4 WHERE num_cartao = '4001491015472463';
UPDATE cartao SET cliente = 5 WHERE num_cartao = '4001733310706027';
UPDATE cartao SET cliente = 1 WHERE num_cartao = '4001120953203520';
UPDATE cartao SET cliente = 1 WHERE num_cartao = '4001596602388724';
UPDATE cartao SET cliente = 2 WHERE num_cartao = '4001883484655658';
UPDATE cartao SET cliente = 4 WHERE num_cartao = '4001830285782013';


-- Inserir dados de teste na tabela Funcionario
INSERT INTO funcionario (nome, morada, email, telemovel, nif, password, gerente)
VALUES ('admin', 'admin', 'admin@ibank.pt', '123456789', '123456789', '1000:9084140a6008be75562e71e82a93b5e6:59b3a43838bc479623e19ef78e9d428e7486aea20deb788297cb10e829a27e1def6c456bfd17e457eb611bd0781621d18a4f37a4f26a3e3e594488f8d76474ec', null);

INSERT INTO funcionario (nome, morada, email, telemovel, nif, password, gerente)
VALUES ('Ricardo Pereira', 'Avenida L, nº 111', 'ricardo.pereira@email.com', '765432109', '111223344', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', NULL);

INSERT INTO funcionario (nome, morada, email, telemovel, nif, password, gerente)
VALUES ('Isabel Martins', 'Rua M, nº 222', 'isabel.martins@email.com', '987654321', '222334455', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', NULL);

INSERT INTO funcionario (nome, morada, email, telemovel, nif, password, gerente)
VALUES ('Miguel Oliveira', 'Avenida N, nº 333', 'miguel.oliveira@email.com', '876543210', '333445566', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', 1);

INSERT INTO funcionario (nome, morada, email, telemovel, nif, password, gerente)
VALUES ('Catarina Santos', 'Rua O, nº 444', 'catarina.santos@email.com', '987654321', '444556677', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', NULL);

INSERT INTO funcionario (nome, morada, email, telemovel, nif, password, gerente)
VALUES ('Nuno Almeida', 'Avenida P, nº 555', 'nuno.almeida@email.com', '876543210', '555667788', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', 3);

-- Inserir dados de teste na tabela Transferencia
INSERT INTO transferencia (valor, cliente_realiza, cliente_recebe, motivo)
VALUES (120.00, 1, 2, 'Pagamento da renda');

INSERT INTO transferencia (valor, cliente_realiza, cliente_recebe, motivo)
VALUES (80.00, 2, 3, 'Festa de quinta-feita');

INSERT INTO transferencia (valor, cliente_realiza, cliente_recebe, motivo)
VALUES (200.00, 3, 4, 'Valor para a prenda a helena');

INSERT INTO transferencia (valor, cliente_realiza, cliente_recebe, motivo)
VALUES (30.00, 4, 5, 'Almoço da firma');

INSERT INTO transferencia (valor, cliente_realiza, cliente_recebe, motivo)
VALUES (150.00, 5, 1, 'Empréstimo para compras');

-- Inserir dados de teste na tabela Pagamento_Servicos_Compras
INSERT INTO pagamento_servicos_compras (entidade, referencia, valor, pago, cliente, cliente_cria, cancelada)
VALUES (123, 789, 50.00, false, null, 2, false);

INSERT INTO pagamento_servicos_compras (entidade, referencia, valor, pago, cliente, cliente_cria, cancelada)
VALUES (456, 234, 75.00, false, null, 3, false);

INSERT INTO pagamento_servicos_compras (entidade, referencia, valor, pago, cliente, cliente_cria, cancelada)
VALUES (789, 567, 30.00, false, null, 4, false);

INSERT INTO pagamento_servicos_compras (entidade, referencia, valor, pago, cliente, cliente_cria, cancelada)
VALUES (234, 901, 100.00, false, null, 5, false);

INSERT INTO pagamento_servicos_compras (entidade, referencia, valor, pago, cliente, cliente_cria, cancelada)
VALUES (567, 123, 25.00, false, null, 1, false);

-- Inserir dados de teste na tabela Deposito

INSERT INTO deposito (valor, aprovado, num_fun, num_cli, pendente_aprovacao)
VALUES (500.00, false, null, 1, true);

INSERT INTO deposito (valor, aprovado, num_fun, num_cli, pendente_aprovacao)
VALUES (300.00, false, null, 2, true);

INSERT INTO deposito (valor, aprovado, num_fun, num_cli, pendente_aprovacao)
VALUES (700.00, false, null, 3, true);

INSERT INTO deposito (valor, aprovado, num_fun, num_cli, pendente_aprovacao)
VALUES (250.00, false, null, 4, true);

INSERT INTO deposito (valor, aprovado, num_fun, num_cli, pendente_aprovacao)
VALUES (145.00, false, null, 4, true);

call aprovar_deposito(1,1);
call aprovar_deposito(2,1);
call aprovar_deposito(3,3);
call aprovar_deposito(4,4);
call aprovar_deposito(5,5);