DROP TABLE temporal;

CREATE TABLE temporal(
    usuario VARCHAR2(100 BYTE),
    fecha_sistema DATE,
    codigo_emp NUMBER,
    mensaje VARCHAR2(100 BYTE)
);

CREATE OR REPLACE TRIGGER trigger_log_detalles
AFTER INSERT OR UPDATE OR DELETE ON employee
FOR EACH ROW
DECLARE
    v_usuario VARCHAR2(100 BYTE);
    v_fecha_sistema DATE;
    v_codigo_emp NUMBER;
    v_mensaje VARCHAR2(100 BYTE);
BEGIN
    v_usuario := USER;
    v_fecha_sistema := SYSDATE;
    v_codigo_emp := :NEW.employee_id;

    IF DELETING THEN
        v_codigo_emp := :OLD.employee_id;
        v_mensaje := 'Detalle borrado';
    ELSIF INSERTING THEN
        v_mensaje := 'Detalle dado de alta';
    ELSIF UPDATING THEN
        v_mensaje := 'Detalle modificado';
    END IF;
    INSERT INTO temporal VALUES(v_usuario, v_fecha_sistema, v_codigo_emp, v_mensaje);
END;
/

UPDATE employee SET salary = 1990 WHERE employee_id =7506;

SELECT * FROM temporal;