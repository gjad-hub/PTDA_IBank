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
    entity         INT,
    FOREIGN KEY (default_card) REFERENCES card (card_number)
);

CREATE TABLE cards
(
    card_number    VARCHAR(255) PRIMARY KEY,
    expiration_date DATE,
    status        VARCHAR(50),
    customer_iban       INT,
    FOREIGN KEY (client) REFERENCES client(customer_iban)
);

-- Payment_Services_Purchases Table
CREATE TABLE payment_services_purchases
(
    entity      INT,
    reference   INT,
    amount       DECIMAL(10, 2),
    paid        BOOLEAN,
    customer    INT,
    created_by  INT,
    canceled    BOOLEAN,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (entity, reference),
    FOREIGN KEY (customer) REFERENCES customer (customer_iban),
    FOREIGN KEY (created_by) REFERENCES customer (customer_iban)
);

-- Transfer Table
CREATE TABLE transfer
(
    transfer_id    INT PRIMARY KEY AUTO_INCREMENT,
    amount          DECIMAL(10, 2),
    performing_customer INT,
    receiving_customer  INT,
    reason         VARCHAR(200),
    FOREIGN KEY (performing_customer) REFERENCES customer (customer_iban),
    FOREIGN KEY (receiving_customer) REFERENCES customer (customer_iban)
);

-- employees Table
CREATE TABLE employees
(
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255),
    address     VARCHAR(255),
    email       VARCHAR(255),
    phone       VARCHAR(15),
    nif      VARCHAR(9),
    password    VARCHAR(255),
    manager     INT,
    dismissed   BOOLEAN DEFAULT 0,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (manager) REFERENCES employees (employee_id)
);

-- employees_Customer Table
CREATE TABLE employees_customer
(
    id      INTEGER PRIMARY KEY AUTO_INCREMENT,
    employee_id INT,
    customer_iban INT,
    FOREIGN KEY (employee_id) REFERENCES employees (employee_id),
    FOREIGN KEY (customer_iban) REFERENCES customer (customer_iban)
);

-- Deposit Table
CREATE TABLE deposits
(
    deposit_id INT PRIMARY KEY AUTO_INCREMENT,
    amount      DECIMAL(10, 2),
    approved   BOOLEAN,
    pending_approval BOOLEAN NOT NULL DEFAULT 1,
    employee   INT,
    customer   INT,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employees) REFERENCES employees (employee_id),
    FOREIGN KEY (customer) REFERENCES customer (customer_iban)
);
ALTER TABLE deposit ADD CONSTRAINT check_approved CHECK 
    ((approved = 1 AND employees IS NOT NULL) OR approved = 0);

-- Transactions Table
CREATE TABLE transactions
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    customer_iban   INT NOT NULL,
    description VARCHAR(50),
    amount     DECIMAL(10, 2),
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_iban) REFERENCES customer (customer_iban)
);

-- Profile Comments Table
CREATE TABLE profile_comments
(
    id           INTEGER AUTO_INCREMENT PRIMARY KEY,
    employee_id INTEGER references employees (employee_id),
    customer_iban   INTEGER references customer (customer_iban),
    description    VARCHAR(255),
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Trigger deposit
CREATE TRIGGER deposit AFTER INSERT ON deposit FOR EACH ROW
BEGIN
    DECLARE exists_customer INT;

    -- Adds extra security to the trigger
    SELECT COUNT(*) INTO exists_customer FROM customer WHERE customer_iban = NEW.customer;

    IF exists_customer > 0 THEN
        UPDATE customer SET active_balance = active_balance + NEW.amount WHERE customer_iban = NEW.customer;
    END IF;
END;

-- Trigger pay service
CREATE TRIGGER pay_service AFTER UPDATE ON payment_services_purchases FOR EACH ROW
BEGIN
    DECLARE exists_customer INT;
    DECLARE balance DECIMAL(10, 2);

    -- Adds extra security to the trigger
    SELECT COUNT(*) INTO exists_customer FROM customer WHERE customer_iban = NEW.customer;
    SELECT customer.balance INTO balance FROM customer WHERE customer_iban = NEW.customer;

    IF exists_customer > 0 THEN
        IF balance >= NEW.amount THEN
            INSERT INTO transactions (customer_iban, description, amount)
            amountS (NEW.customer, "Payment for services", 0 - NEW.amount);

            INSERT INTO transactions (customer_iban, description, amount)
            amountS (NEW.created_by, "Payment for services", NEW.amount);
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
            INSERT INTO transfer (amount, performing_customer, receiving_customer, reason) amountS (transfer_amount, performing_customer, receiving_customer, description);

            INSERT INTO transactions (customer_iban, description, amount) amountS (performing_customer, "Transfer", 0 - transfer_amount);
            INSERT INTO transactions (customer_iban, description, amount) amountS (receiving_customer, "Transfer", transfer_amount);
        COMMIT;
END;

-- Trigger update customer balance
CREATE TRIGGER update_balance AFTER INSERT ON transactions FOR EACH ROW
BEGIN
    DECLARE exists_customer INT;

    -- Adds extra security to the trigger
    SELECT COUNT(*) INTO exists_customer FROM customer WHERE customer_iban = NEW.customer_iban;

    IF exists_customer > 0 THEN
        UPDATE customer SET balance = balance + NEW.amount WHERE customer_iban = NEW.customer_iban;
    END IF;
END;

-- Procedure approve deposit
CREATE PROCEDURE approve_deposit(IN deposit_id INTEGER, IN employee_id INTEGER)
BEGIN
        DECLARE EXIT HANDLER FOR SQLEXCEPTION
            BEGIN
                ROLLBACK;
                RESIGNAL;
            END;
        START TRANSACTION;
            UPDATE customer SET active_balance = active_balance - (SELECT amount FROM deposit WHERE deposit_id = deposit_id)
                           WHERE customer_iban = (SELECT customer FROM deposit WHERE deposit_id = deposit_id);

            UPDATE deposit SET approved = true, employees = employee_id, pending_approval = false WHERE deposit_id = deposit_id;

            INSERT INTO employees_customer (employees, customer)
            amountS (employee_id, (SELECT customer FROM deposit WHERE deposit_id = deposit_id));

            INSERT INTO transactions (customer_iban, description, amount)
            amountS ((SELECT customer FROM deposit WHERE deposit_id = deposit_id), "Deposit", (SELECT amount FROM deposit WHERE deposit_id = deposit_id));
        COMMIT;
END;

CREATE PROCEDURE reject_deposit(IN deposit_id INTEGER, IN employee_id INTEGER)
BEGIN
    UPDATE customer SET active_balance = active_balance - (SELECT amount FROM deposit WHERE deposit_id = deposit_id) WHERE customer_iban = (SELECT customer FROM deposit WHERE deposit_id = deposit_id);

    UPDATE deposit SET approved = false, employees = employee_id, pending_approval = false WHERE deposit_id = deposit_id;

    INSERT INTO employees_customer (employees, customer) amountS (employee_id, (SELECT customer FROM deposit WHERE deposit_id = deposit_id));
END;

\\
DELIMITER ;
