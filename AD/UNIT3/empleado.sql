CREATE TYPE colec_hijos AS
    VARRAY(10) OF VARCHAR(30);
/

CREATE TABLE empleado (
    idemp      NUMBER,
    nombre     VARCHAR(30),
    apellidos  VARCHAR(30),
    hijos      colec_hijos
);
/

INSERT INTO empleado VALUES (
    1,
    'Francisco',
    'P�rez',
    colec_hijos('Luis', 'Ursula')
);

    INSERT INTO empleado VALUES (
        2,
        'Esperanza ',
        'Jim�nez',
        colec_hijos('Jos�', 'Carlos', 'Pedro')
    );
/

SELECT
    *
FROM
    empleado;
/

SELECT
    hijos
FROM
    empleado
WHERE
    idemp = 1;
/

SELECT
    hijos
FROM
    empleado;
/

SET SERVEROUTPUT ON;

DECLARE
    CURSOR fcur IS
    SELECT
        *
    FROM
        empleado;

BEGIN
    FOR j IN fcur LOOP
        IF j.hijos.EXISTS(1) THEN
            dbms_output.put_line('IDEMP: '
                                 || j.idemp
                                 || ' NOMBRE: '
                                 || j.nombre
                                 || ' APELLIDOS: '
                                 || j.apellidos
                                 || ' total de hijos es : '
                                 || j.hijos.count);

        END IF;
    END LOOP;
END;
/