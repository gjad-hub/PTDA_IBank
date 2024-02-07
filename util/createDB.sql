-- Customer Table
CREATE TABLE customer
(
    customer_id    INT PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(255),
    address        VARCHAR(255),
    email          VARCHAR(255) UNIQUE NOT NULL,
    phone          VARCHAR(15),
    tax_id         VARCHAR(9),
    password       VARCHAR(255) NOT NULL,
    account_number VARCHAR(255) UNIQUE NOT NULL,
    balance        DECIMAL(10, 2) DEFAULT 0,
    active_balance DECIMAL(10, 2) DEFAULT 0,
    default_card   VARCHAR(255),
    deleted        BOOLEAN DEFAULT 0,
    creation_date  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    entity         INT,
    FOREIGN KEY (default_card) REFERENCES card (card_number)
);

-- Payment_Services_Purchases Table
CREATE TABLE payment_services_purchases
(
    entity      INT,
    reference   INT,
    value       DECIMAL(10, 2),
    paid        BOOLEAN,
    customer    INT,
    created_by  INT,
    canceled    BOOLEAN,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (entity, reference),
    FOREIGN KEY (customer) REFERENCES customer (customer_id),
    FOREIGN KEY (created_by) REFERENCES customer (customer_id)
);

-- Transfer Table
CREATE TABLE transfer
(
    transfer_id    INT PRIMARY KEY AUTO_INCREMENT,
    value          DECIMAL(10, 2),
    performing_customer INT,
    receiving_customer  INT,
    reason         VARCHAR(200),
    FOREIGN KEY (performing_customer) REFERENCES customer (customer_id),
    FOREIGN KEY (receiving_customer) REFERENCES customer (customer_id)
);

-- Employee Table
CREATE TABLE employee
(
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255),
    address     VARCHAR(255),
    email       VARCHAR(255),
    phone       VARCHAR(15),
    tax_id      VARCHAR(9),
    password    VARCHAR(255),
    manager     INT,
    dismissed   BOOLEAN DEFAULT 0,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (manager) REFERENCES employee (employee_id)
);

-- Employee_Customer Table
CREATE TABLE employee_customer
(
    id      INTEGER PRIMARY KEY AUTO_INCREMENT,
    employee_id INT,
    customer_id INT,
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id),
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
);

-- Deposit Table
CREATE TABLE deposit
(
    deposit_id INT PRIMARY KEY AUTO_INCREMENT,
    value      DECIMAL(10, 2),
    approved   BOOLEAN,
    pending_approval BOOLEAN NOT NULL DEFAULT 1,
    employee   INT,
    customer   INT,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee) REFERENCES employee (employee_id),
    FOREIGN KEY (customer) REFERENCES customer (customer_id)
);
ALTER TABLE deposit ADD CONSTRAINT check_approved CHECK 
    ((approved = 1 AND employee IS NOT NULL) OR approved = 0);

-- Transactions Table
CREATE TABLE transactions
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    customer_id   INT NOT NULL,
    description VARCHAR(50),
    value     DECIMAL(10, 2),
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
);

-- Profile Comments Table
CREATE TABLE profile_comment
(
    id           INTEGER AUTO_INCREMENT PRIMARY KEY,
    employee_id INTEGER references employee (employee_id),
    customer_id   INTEGER references customer (customer_id),
    description    VARCHAR(255),
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Trigger deposit
CREATE TRIGGER deposit AFTER INSERT ON deposit FOR EACH ROW
BEGIN
    DECLARE exists_customer INT;

    -- Adds extra security to the trigger
    SELECT COUNT(*) INTO exists_customer FROM customer WHERE customer_id = NEW.customer;

    IF exists_customer > 0 THEN
        UPDATE customer SET active_balance = active_balance + NEW.value WHERE customer_id = NEW.customer;
    END IF;
END;

-- Trigger pay service
CREATE TRIGGER pay_service AFTER UPDATE ON payment_services_purchases FOR EACH ROW
BEGIN
    DECLARE exists_customer INT;
    DECLARE balance DECIMAL(10, 2);

    -- Adds extra security to the trigger
    SELECT COUNT(*) INTO exists_customer FROM customer WHERE customer_id = NEW.customer;
    SELECT customer.balance INTO balance FROM customer WHERE customer_id = NEW.customer;

    IF exists_customer > 0 THEN
        IF balance >= NEW.value THEN
            INSERT INTO transactions (customer_id, description, value)
            VALUES (NEW.customer, "Payment for services", 0 - NEW.value);

            INSERT INTO transactions (customer_id, description, value)
            VALUES (NEW.created_by, "Payment for services", NEW.value);
        END IF;
    END IF;
END;

-- Procedure make transfer
CREATE PROCEDURE make_transfer(IN performing_customer INTEGER, IN receiving_customer INTEGER, IN transfer_value DECIMAL(10,2), IN description VARCHAR(255))
BEGIN
        DECLARE EXIT HANDLER FOR SQLEXCEPTION
            BEGIN
                ROLLBACK;
                RESIGNAL;
            END;
        START TRANSACTION;
            INSERT INTO transfer (value, performing_customer, receiving_customer, reason) VALUES (transfer_value, performing_customer, receiving_customer, description);

            INSERT INTO transactions (customer_id, description, value) VALUES (performing_customer, "Transfer", 0 - transfer_value);
            INSERT INTO transactions (customer_id, description, value) VALUES (receiving_customer, "Transfer", transfer_value);
        COMMIT;
END;

-- Trigger update customer balance
CREATE TRIGGER update_balance AFTER INSERT ON transactions FOR EACH ROW
BEGIN
    DECLARE exists_customer INT;

    -- Adds extra security to the trigger
    SELECT COUNT(*) INTO exists_customer FROM customer WHERE customer_id = NEW.customer_id;

    IF exists_customer > 0 THEN
        UPDATE customer SET balance = balance + NEW.value WHERE customer_id = NEW.customer_id;
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
            UPDATE customer SET active_balance = active_balance - (SELECT value FROM deposit WHERE deposit_id = deposit_id)
                           WHERE customer_id = (SELECT customer FROM deposit WHERE deposit_id = deposit_id);

            UPDATE deposit SET approved = true, employee = employee_id, pending_approval = false WHERE deposit_id = deposit_id;

            INSERT INTO employee_customer (employee, customer)
            VALUES (employee_id, (SELECT customer FROM deposit WHERE deposit_id = deposit_id));

            INSERT INTO transactions (customer_id, description, value)
            VALUES ((SELECT customer FROM deposit WHERE deposit_id = deposit_id), "Deposit", (SELECT value FROM deposit WHERE deposit_id = deposit_id));
        COMMIT;
END;

CREATE PROCEDURE reject_deposit(IN deposit_id INTEGER, IN employee_id INTEGER)
BEGIN
    UPDATE customer SET active_balance = active_balance - (SELECT value FROM deposit WHERE deposit_id = deposit_id) WHERE customer_id = (SELECT customer FROM deposit WHERE deposit_id = deposit_id);

    UPDATE deposit SET approved = false, employee = employee_id, pending_approval = false WHERE deposit_id = deposit_id;

    INSERT INTO employee_customer (employee, customer) VALUES (employee_id, (SELECT customer FROM deposit WHERE deposit_id = deposit_id));
END;

\\
DELIMITER ;
