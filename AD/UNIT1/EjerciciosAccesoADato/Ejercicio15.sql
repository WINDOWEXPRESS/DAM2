DROP TABLE temporal;

CREATE TABLE temporal(
   info VARCHAR2(50 BYTE)
);

CREATE OR REPLACE TRIGGER SubidaSalarioEmpleado
AFTER UPDATE ON employee
FOR EACH ROW
BEGIN
    IF :NEW.salary > :OLD.salary THEN
        INSERT INTO temporal VALUES('Subida de salario empleado: ' || :NEW.employee_id);
    END IF;
END;
/

UPDATE employee SET salary = 1900 WHERE employee_id =7506;

SELECT * FROM temporal;
