DROP TABLE TEMPORAL1;
DROP TABLE TEMPORAL2;
DROP TABLE TEMPORAL3;

CREATE TABLE TEMPORAL1 (
    clientid NUMBER(6,0) NOT NULL,
    clientname VARCHAR2(45 BYTE) NOT NULL,
    empid NUMBER(4,0) NOT NULL
);

CREATE TABLE TEMPORAL2 (
    clientid NUMBER(6,0) NOT NULL,
    clientname VARCHAR2(45 BYTE) NOT NULL,
    empid NUMBER(4,0) NOT NULL
);

CREATE TABLE TEMPORAL3 (
    clientid NUMBER(6,0) NOT NULL,
    clientname VARCHAR2(45 BYTE) NOT NULL,
    empid NUMBER(4,0) NOT NULL
);

DECLARE
    vclientid customer.customer_id%TYPE;
    vname customer.name%TYPE;
    vempid customer.salesperson_id%TYPE;
BEGIN
    FOR rec IN (SELECT customer_id, name, salesperson_id FROM customer) LOOP
        vclientid := rec.customer_id;
        vname := rec.name;
        vempid := rec.salesperson_id;
        INSERT INTO TEMPORAL1 VALUES(vclientid,vname,vempid);
    END LOOP;
END;
/

SELECT * FROM TEMPORAL1;

DECLARE
    vclientid customer.customer_id%TYPE;
    vname customer.name%TYPE;
    vempid customer.salesperson_id%TYPE;
    CURSOR c1 IS SELECT customer_id, name, salesperson_id FROM customer;
BEGIN
    OPEN c1;
    FETCH c1 INTO vclientid, vname, vempid;
    WHILE c1%FOUND LOOP
        INSERT INTO TEMPORAL2 VALUES (vclientid, vname, vempid);
        FETCH c1 INTO vclientid, vname, vempid;
    END LOOP;
    CLOSE c1;
END;
/

SELECT * FROM TEMPORAL2;

DECLARE
    vclientid customer.customer_id%TYPE;
    vname customer.name%TYPE;
    vempid customer.salesperson_id%TYPE;
    done BOOLEAN := FALSE;
    CURSOR c1 IS SELECT customer_id, name, salesperson_id FROM customer;
BEGIN
    OPEN C1;
    LOOP
        FETCH C1 INTO vclientid, vname, vempid;
        IF C1%NOTFOUND THEN
            done := TRUE;
        END IF;
        
        INSERT INTO TEMPORAL3 VALUES (vclientid, vname, vempid);
        
        IF done THEN
            EXIT;
        END IF;
    END LOOP;
    CLOSE C1;
END;
/

SELECT * FROM TEMPORAL3;