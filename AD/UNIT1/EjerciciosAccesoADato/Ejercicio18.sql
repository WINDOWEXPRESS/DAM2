DROP TABLE temporal;
CREATE TABLE temporal(
    clid NUMBER(6,0),
    INFO VARCHAR2(50 BYTE)
);

DECLARE
    v_cliente_id NUMBER;
    v_cliente_id2 NUMBER;
BEGIN
    v_cliente_id := &id;
    SELECT customer_id INTO v_cliente_id2 FROM customer WHERE customer_id = v_cliente_id;
    
    INSERT INTO temporal VALUES (v_cliente_id2,'EXISTE');
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            INSERT INTO temporal VALUES (v_cliente_id,'NO EXISTE');
END;
/

SELECT * FROM temporal;