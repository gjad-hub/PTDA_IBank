-- Customer Table
CREATE TABLE customers
(
    customer_number INT PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(255),
    address        VARCHAR(255),
    email          VARCHAR(255) UNIQUE NOT NULL,
    phone          VARCHAR(15),
    nif            VARCHAR(9),
    password       VARCHAR(255) NOT NULL,
    account_number VARCHAR(255) UNIQUE NOT NULL,
    balance        DECIMAL(10, 2) DEFAULT 0,
    pending_balance DECIMAL(10, 2) DEFAULT 0,
    default_card   VARCHAR(255),
    deleted        BOOLEAN DEFAULT 0,
    creation_date  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    entity         INT
);

-- Cards Table
CREATE TABLE cards
(
    card_number     VARCHAR(255) PRIMARY KEY,
    expiration_date DATE,
    status          VARCHAR(50),
    customer_number INT,
    FOREIGN KEY (customer_number) REFERENCES customers (customer_number)
);


-- Payment_Services_Purchases Table
CREATE TABLE payment_services_purchases
(
    entity      INT,
    reference   INT,
    amount      DECIMAL(10, 2),
    paid        BOOLEAN,
    customer    INT,
    created_by  INT,
    canceled    BOOLEAN,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (entity, reference),
    FOREIGN KEY (customer) REFERENCES customers (customer_number),
    FOREIGN KEY (created_by) REFERENCES customers (customer_number)
);

-- Transfer Table
CREATE TABLE transfer
(
    transfer_id    		INT PRIMARY KEY AUTO_INCREMENT,
    amount          	DECIMAL(10, 2),
    performing_customer INT,
    receiving_customer  INT,
    reason         		VARCHAR(200),
    FOREIGN KEY (performing_customer) REFERENCES customers (customer_number),
    FOREIGN KEY (receiving_customer) REFERENCES customers (customer_number)
);

-- employees Table
CREATE TABLE employees
(
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255),
    address     VARCHAR(255),
    email       VARCHAR(255),
    phone       VARCHAR(15),
    nif      	VARCHAR(9),
    password    VARCHAR(255),
    manager     INT,
    dismissed   BOOLEAN DEFAULT 0,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (manager) REFERENCES employees (employee_id)
);

-- employees_customers Table
CREATE TABLE employees_customers
(
    id      	    INTEGER PRIMARY KEY AUTO_INCREMENT,
    employee_id     INT,
    customer_number INT,
    FOREIGN KEY (employee_id) REFERENCES employees (employee_id),
    FOREIGN KEY (customer_number) REFERENCES customers (customer_number)
);

-- Deposit Table
CREATE TABLE deposits
(
    deposit_id INT PRIMARY KEY AUTO_INCREMENT,
    amount     DECIMAL(10, 2),
    approved   BOOLEAN,
    pending_approval BOOLEAN NOT NULL DEFAULT 1,
    employee   INT,
    customer   INT,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee) REFERENCES employees (employee_id),
    FOREIGN KEY (customer) REFERENCES customers (customer_number)
);
ALTER TABLE deposit ADD CONSTRAINT check_approved CHECK 
    ((approved = 1 AND employees IS NOT NULL) OR approved = 0);

-- Transactions Table
CREATE TABLE transactions
(
    id        	     INT PRIMARY KEY AUTO_INCREMENT,
    customer_number  INT NOT NULL,
    description      VARCHAR(50),
    amount           DECIMAL(10, 2),
    creation_date    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_number) REFERENCES customers (customer_number)
);


-- Profile Comments Table
CREATE TABLE profile_comments
(
    id                INTEGER AUTO_INCREMENT PRIMARY KEY,
    employee_id       INTEGER references employees (employee_id),
    customer_number   INTEGER references customers (customer_number),
    description       VARCHAR(255),
    creation_date     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DELIMITER //

-- Trigger deposit
CREATE TRIGGER deposit AFTER INSERT ON deposit FOR EACH ROW
BEGIN
    DECLARE costumer_exists INT;

    -- Adds extra security to the trigger
    SELECT COUNT(*) INTO costumer_exists FROM customers WHERE customer_number = NEW.customers;

    IF costumer_exists > 0 THEN
        UPDATE customers SET pending_balance = pending_balance + NEW.amount WHERE customer_number = NEW.customers;
    END IF;
END;

-- Trigger pay service
CREATE TRIGGER pay_service AFTER UPDATE ON payment_services_purchases FOR EACH ROW
BEGIN
    DECLARE costumer_exists INT;
    DECLARE balance DECIMAL(10, 2);

    -- Adds extra security to the trigger
    SELECT COUNT(*) INTO costumer_exists FROM customers WHERE customer_number = NEW.customers;
    SELECT customer.balance INTO balance FROM customers WHERE customer_number = NEW.customers;

    IF costumer_exists > 0 THEN
        IF balance >= NEW.amount THEN
            INSERT INTO transactions (customer_number, description, amount)
            values (NEW.customer, "Payment for services", 0 - NEW.amount);

            INSERT INTO transactions (customer_number, description, amount)
            values (NEW.created_by, "Payment for services", NEW.amount);
        END IF;
    END IF;
END;

-- Procedure make transfer
CREATE PROCEDURE make_transfer(IN performing_customer INTEGER, IN receiving_customer INTEGER, IN transfer_amount DECIMAL(10,2), IN description VARCHAR(255))
BEGIN
        DECLARE EXIT HANDLER FOR SQLEXCEPTION
            BEGIN
                ROLLBACK;
                RESIGNAL;
            END;
        START TRANSACTION;
            INSERT INTO transfers (amount, performing_customer, receiving_customer, reason) values (transfer_amount, performing_customer, receiving_customer, description);

            INSERT INTO transactions (customer_iban, description, amount) values (performing_customer, "Transfer", 0 - transfer_amount);
            INSERT INTO transactions (customer_iban, description, amount) values (receiving_customer, "Transfer", transfer_amount);
        COMMIT;
END;

-- Trigger update customer balance
CREATE TRIGGER update_balance AFTER INSERT ON transactions FOR EACH ROW
BEGIN
    DECLARE consumer_exists INT;

    -- Adds extra security to the trigger
    SELECT COUNT(*) INTO consumer_exists FROM customers WHERE costumer_number = NEW.costumer_number;

    IF exists_customer > 0 THEN
        UPDATE customers SET balance = balance + NEW.amount WHERE costumer_number = NEW.costumer_number;
    END IF;
END;

-- Procedure approve deposit
CREATE PROCEDURE approve_deposit(IN deposit INTEGER, IN employee_id INTEGER)
BEGIN
        DECLARE EXIT HANDLER FOR SQLEXCEPTION
            BEGIN
                ROLLBACK;
                RESIGNAL;
            END;
        START TRANSACTION;
            UPDATE customers SET active_balance = active_balance - (SELECT amount FROM deposit WHERE deposit_id = deposit)
                           WHERE customer_iban = (SELECT customer FROM deposit WHERE deposit_id = deposit);

            UPDATE deposits SET approved = true, employee = employee_id, pending_approval = false WHERE deposit_id = deposit;

            INSERT INTO employees_customers (employees, customer)
            values (employee_id, (SELECT customer FROM deposits WHERE deposit_id = deposit));

            INSERT INTO transactions (costumer_number, description, amount)
            values ((SELECT customer FROM deposits WHERE deposit_id = deposit), "Deposit", (SELECT amount FROM deposits WHERE deposit_id = deposit));
        COMMIT;
END;

CREATE PROCEDURE reject_deposit(IN deposit INTEGER, IN employee_id INTEGER)
BEGIN
    UPDATE customers SET active_balance = active_balance - (SELECT amount FROM deposit WHERE deposit_id = deposit) WHERE customer_iban = (SELECT customer FROM deposit WHERE deposit_id = deposit);

    UPDATE deposits SET approved = false, employee = employee_id, pending_approval = false WHERE deposit_id = deposit;

    INSERT INTO employees_customers (employee_id , customer_number) values (employee_id, (SELECT customer FROM deposits WHERE deposit_id = deposit));
END;

\\
DELIMITER ;