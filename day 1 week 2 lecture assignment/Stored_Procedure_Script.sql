-- ============================================================
-- CREATE TABLE
-- ============================================================

CREATE TABLE client_accounts (
    account_id INT PRIMARY KEY,
    owner_name VARCHAR(30) NOT NULL,
    balance DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) DEFAULT 'ACTIVE'
);

INSERT INTO client_accounts
VALUES
    (1, 'Alice', 1000.00, 'ACTIVE'),
    (2, 'Bob', 500.00, 'ACTIVE');


-- ============================================================
-- STORED PROCEDURE: TRANSFER FUNDS
-- ============================================================

CREATE OR REPLACE PROCEDURE pr_transfer_funds(
    sender_id INT,
    receiver_id INT,
    amount DECIMAL
)
LANGUAGE plpgsql
AS $$
BEGIN

    UPDATE client_accounts
    SET balance = balance - amount
    WHERE account_id = sender_id;

    UPDATE client_accounts
    SET balance = balance + amount
    WHERE account_id = receiver_id;

    COMMIT;

END;
$$;

-- Execute procedure
CALL pr_transfer_funds(1, 2, 150);

SELECT * FROM client_accounts;


-- ============================================================
-- STORED PROCEDURE: OUT PARAMETERS
-- ============================================================

CREATE OR REPLACE PROCEDURE pr_get_account_details(
    acc_id INT,
    OUT acc_owner VARCHAR,
    OUT acc_bal DECIMAL
)
LANGUAGE plpgsql
AS $$
BEGIN

    SELECT owner_name, balance
    INTO acc_owner, acc_bal
    FROM client_accounts
    WHERE account_id = acc_id;

END;
$$;

-- Execute procedure
CALL pr_get_account_details(2, NULL, NULL);


-- ============================================================
-- ASSIGNMENT
-- Stored Procedure Using One INOUT Parameter
-- ============================================================

CREATE OR REPLACE PROCEDURE pr_apply_bonus(
    INOUT bonus_amount DECIMAL
)
LANGUAGE plpgsql
AS $$
BEGIN

    -- Increase the value by 10%
    bonus_amount := bonus_amount * 1.10;

END;
$$;

-- Execute procedure
CALL pr_apply_bonus(100.00);