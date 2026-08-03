CREATE TABLE inventory (
    item_id INT PRIMARY KEY,
    item_name VARCHAR(30) NOT NULL,
    quantity INT NOT NULL,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- ============================================================
-- INSERT SAMPLE DATA
-- ============================================================

INSERT INTO inventory (item_id, item_name, quantity)
VALUES
    (1, 'Keyboard', 2);

SELECT *
FROM inventory;


-- ============================================================
-- TRIGGER FUNCTION: UPDATE last_updated
-- ============================================================

CREATE OR REPLACE FUNCTION fn_sync_last_updated()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    NEW.last_updated := CURRENT_TIMESTAMP;

    RETURN NEW;
END;
$$;


-- ============================================================
-- TRIGGER: RUN BEFORE INVENTORY UPDATE
-- ============================================================

CREATE TRIGGER trg_inventory_update_timestamp
BEFORE UPDATE ON inventory
FOR EACH ROW
EXECUTE FUNCTION fn_sync_last_updated();


-- ============================================================
-- CREATE INVENTORY AUDIT LOG TABLE
-- ============================================================

CREATE TABLE inventory_audit_log (
    audit_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    item_id INT NOT NULL,
    old_item_name VARCHAR(30),
    new_item_name VARCHAR(30),
    old_quantity INT,
    new_quantity INT,
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- ============================================================
-- TRIGGER FUNCTION: RECORD INVENTORY CHANGES
-- ============================================================

CREATE OR REPLACE FUNCTION fn_log_inventory_update()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO inventory_audit_log (
        item_id,
        old_item_name,
        new_item_name,
        old_quantity,
        new_quantity,
        changed_at
    )
    VALUES (
        OLD.item_id,
        OLD.item_name,
        NEW.item_name,
        OLD.quantity,
        NEW.quantity,
        CURRENT_TIMESTAMP
    );

    RETURN NEW;
END;
$$;


-- ============================================================
-- TRIGGER: ADD UPDATE TO AUDIT LOG
-- ============================================================

CREATE TRIGGER trg_inventory_audit_log
AFTER UPDATE ON inventory
FOR EACH ROW
EXECUTE FUNCTION fn_log_inventory_update();


-- ============================================================
-- TEST THE TRIGGERS
-- ============================================================

UPDATE inventory
SET quantity = 10
WHERE item_id = 1;


-- View the updated inventory record.
SELECT *
FROM inventory;


-- View the recorded changes.
SELECT *
FROM inventory_audit_log;