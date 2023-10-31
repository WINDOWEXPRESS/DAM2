DROP TABLE temporal;

CREATE TABLE temporal(
    info VARCHAR2(50 BYTE)
);

CREATE OR REPLACE TRIGGER remplaze
BEFORE UPDATE ON EMPLOYEE
FOR EACH ROW
BEGIN
    IF :NEW.job_id = 671 THEN
        :NEW.job_id := 672;
    END IF;
    INSERT INTO temporal VALUES('Cambio de trabajo actualizado');
END;
/

UPDATE employee SET job_id = 671 WHERE employee_id = 7499;

SELECT * FROM temporal;

