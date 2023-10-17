DROP TABLE temporal;

CREATE TABLE temporal(
    empid NUMBER(4,0),
    vfistname VARCHAR2(15 BYTE),
    did NUMBER(2,0)
);

CREATE OR REPLACE TRIGGER BorraEmpleado
AFTER DELETE ON employee
FOR EACH ROW
BEGIN
    INSERT INTO temporal VALUES(:OLD.employee_id, :OLD.last_name, :OLD.department_id);
END;
/