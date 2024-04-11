-- Limpar todos os dados
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE cards;
TRUNCATE TABLE customers;
truncate table profile_comments;
TRUNCATE TABLE transfers;
TRUNCATE TABLE employees;
truncate table employees_customers;
TRUNCATE TABLE payment_services_purchases;
truncate table transactions;
truncate table transfers;
TRUNCATE TABLE deposits;

SET FOREIGN_KEY_CHECKS = 1;

-- Insert test data into the Cards table
INSERT INTO cards (card_number, expiration_date, status)
VALUES ('4001330931275836', '2026-10-31', 'Active');

INSERT INTO cards (card_number, expiration_date, status)
VALUES ('4001333354114391', '2024-06-30', 'Active');

INSERT INTO cards (card_number, expiration_date, status)
VALUES ('4001351884567109', '2025-12-31', 'Active');

INSERT INTO cards (card_number, expiration_date, status)
VALUES ('4001491015472463', '2024-08-31', 'Active');

INSERT INTO cards (card_number, expiration_date, status)
VALUES ('4001733310706027', '2024-04-30', 'Active');

INSERT INTO cards (card_number, expiration_date, status)
VALUES ('4001830285782013', '2024-11-30', 'Active');

INSERT INTO cards (card_number, expiration_date, status)
VALUES ('4001883484655658', '2024-09-30', 'Active');

INSERT INTO cards (card_number, expiration_date, status)
VALUES ('4001596602388724', '2024-10-31', 'Active');

INSERT INTO cards (card_number, expiration_date, status)
VALUES ('4001120953203520', '2024-07-31', 'Active');


-- Insert test data into the Customers table
INSERT INTO customers (name, address, email, phone, nif, password, account_number, balance, pending_balance, default_card, entity)
VALUES ('Marta Rodrigues', 'Rua G, nº 111', 'marta.rodrigues@email.com', '876543210', '111223344', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', 'PT50003966064989044052174', 0.00, 0.0, '4001330931275836', 22389);

INSERT INTO customers (name, address, email, phone, nif, password, account_number, balance, pending_balance, default_card, entity)
VALUES ('Pedro Alves', 'Avenida H, nº 222', 'pedro.alves@email.com', '987654321', '222334455', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', 'PT50000265405973711583183', 0.00, 0.00, '4001333354114391', 22456);

INSERT INTO customers (name, address, email, phone, nif, password, account_number, balance, pending_balance, default_card, entity)
VALUES ('Sara Silva', 'Rua I, nº 333', 'sara.silva@email.com', '876543210', '333445566', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', 'PT50005620984363163946165', 0.00, 0.00, '4001351884567109', 22123);

INSERT INTO customers (name, address, email, phone, nif, password, account_number, balance, pending_balance, default_card, entity)
VALUES ('Hugo Oliveira', 'Avenida J, nº 444', 'hugo.oliveira@email.com', '987654321', '444556677', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', 'PT50007884763541403081684', 0.00, 0.00, '4001491015472463', 22789);

INSERT INTO customers (name, address, email, phone, nif, password, account_number, balance, pending_balance, default_card, entity)
VALUES ('Inês Pereira', 'Rua K, nº 555', 'ines.pereira@email.com', '876543210', '555667788', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', 'PT50005637932098875504569', 0.00, 0.00, '4001733310706027', 22456);

-- Update card assignments
UPDATE cards SET customer_number = 1 WHERE card_number = '4001330931275836';
UPDATE cards SET customer_number = 2 WHERE card_number = '4001333354114391';
UPDATE cards SET customer_number = 3 WHERE card_number = '4001351884567109';
UPDATE cards SET customer_number = 4 WHERE card_number = '4001491015472463';
UPDATE cards SET customer_number = 5 WHERE card_number = '4001733310706027';
UPDATE cards SET customer_number = 1 WHERE card_number = '4001120953203520';
UPDATE cards SET customer_number = 1 WHERE card_number = '4001596602388724';
UPDATE cards SET customer_number = 2 WHERE card_number = '4001883484655658';
UPDATE cards SET customer_number = 4 WHERE card_number = '4001830285782013';

-- Insert test data into the Employees table
INSERT INTO employees (name, address, email, phone, nif, password, manager)
VALUES ('admin', 'admin', 'admin@ibank.pt', '123456789', '123456789', '1000:9084140a6008be75562e71e82a93b5e6:59b3a43838bc479623e19ef78e9d428e7486aea20deb788297cb10e829a27e1def6c456bfd17e457eb611bd0781621d18a4f37a4f26a3e3e594488f8d76474ec', null);

INSERT INTO employees (name, address, email, phone, nif, password, manager)
VALUES ('Ricardo Pereira', 'Avenida L, nº 111', 'ricardo.pereira@email.com', '765432109', '111223344', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', NULL);

INSERT INTO employees (name, address, email, phone, nif, password, manager)
VALUES ('Isabel Martins', 'Rua M, nº 222', 'isabel.martins@email.com', '987654321', '222334455', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', NULL);

INSERT INTO employees (name, address, email, phone, nif, password, manager)
VALUES ('Miguel Oliveira', 'Avenida N, nº 333', 'miguel.oliveira@email.com', '876543210', '333445566', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', 1);

INSERT INTO employees (name, address, email, phone, nif, password, manager)
VALUES ('Catarina Santos', 'Rua O, nº 444', 'catarina.santos@email.com', '987654321', '444556677', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', NULL);

INSERT INTO employees (name, address, email, phone, nif, password, manager)
VALUES ('Nuno Almeida', 'Avenida P, nº 555', 'nuno.almeida@email.com', '876543210', '555667788', '1000:244d4e1bc313fff0d490e95636af4ed2:56f93e4e89d4e884e85fded976a7853c076e861588137f05c38e18aaf211a97f0eee7113fe9e9f73927ea84006a67eb2312fb7361790721f3ccbfb1960b43d3f', 3);

-- Insert test data into the Transfer table
INSERT INTO transfers (amount, performing_customer, receiving_customer, reason)
VALUES (120.00, 1, 2, 'Rent payment');

INSERT INTO transfers (amount, performing_customer, receiving_customer, reason)
VALUES (80.00, 2, 3, 'Thursday party');

INSERT INTO transfers (amount, performing_customer, receiving_customer, reason)
VALUES (200.00, 3, 4, 'Gift for Helena');

INSERT INTO transfers (amount, performing_customer, receiving_customer, reason)
VALUES (30.00, 4, 5, 'Company lunch');

INSERT INTO transfers (amount, performing_customer, receiving_customer, reason)
VALUES (150.00, 5, 1, 'Loan for purchases');

-- Insert test data into the Payment_Services_Purchases table
INSERT INTO payment_services_purchases (entity, reference, amount, paid, customer, created_by, canceled)
VALUES (22389, 789789789, 50.00, false, NULL, 1, false);

INSERT INTO payment_services_purchases (entity, reference, amount, paid, customer, created_by, canceled)
VALUES (22456, 234234234, 75.00, false, NULL, 2, false);

INSERT INTO payment_services_purchases (entity, reference, amount, paid, customer, created_by, canceled)
VALUES (22123, 567567567, 30.00, false, NULL, 3, false);

INSERT INTO payment_services_purchases (entity, reference, amount, paid, customer, created_by, canceled)
VALUES (22789, 901901901, 100.00, false, NULL, 4, false);

INSERT INTO payment_services_purchases (entity, reference, amount, paid, customer, created_by, canceled)
VALUES (22456, 123123123, 25.00, false, NULL, 5, false);

-- Insert test data into the Deposits table

INSERT INTO deposits (amount, approved, employee, customer, pending_approval)
VALUES (500.00, false, NULL, 1, true);

INSERT INTO deposits (amount, approved, employee, customer, pending_approval)
VALUES (300.00, false, NULL, 2, true);

INSERT INTO deposits (amount, approved, employee, customer, pending_approval)
VALUES (700.00, false, NULL, 3, true);

INSERT INTO deposits (amount, approved, employee, customer, pending_approval)
VALUES (250.00, false, NULL, 4, true);

INSERT INTO deposits (amount, approved, employee, customer, pending_approval)
VALUES (145.00, false, NULL, 4, true);

INSERT INTO deposits (amount, approved, employee, customer, pending_approval)
VALUES (134.00, false, NULL, 4, true);

INSERT INTO deposits (amount, approved, employee, customer, pending_approval)
VALUES (154.00, false, NULL, 3, true);

INSERT INTO deposits (amount, approved, employee, customer, pending_approval)
VALUES (567.00, false, NULL, 4, true);

CALL approve_deposit(1, 1);
CALL approve_deposit(2, 1);
CALL approve_deposit(3, 3);
CALL approve_deposit(4, 4);
CALL approve_deposit(5, 5);