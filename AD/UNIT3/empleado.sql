DROP TYPE colec_hijos;

DROP TABLE empleado;

DROP TYPE empleado;

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
    'Pérez',
    colec_hijos('Luis', 'Ursula')
);

INSERT INTO empleado VALUES (
    2,
    'Esperanza ',
    'Jiménez',
    colec_hijos('José', 'Carlos', 'Pedro')
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

SET SERVEROUTPUT ON;

DECLARE
    CURSOR fcur IS
    SELECT
        *
    FROM
        empleado
    WHERE
        idemp = 1
    FOR UPDATE;

    v_hijos colec_hijos;
BEGIN
    FOR j IN fcur LOOP
        FOR i IN 1..j.hijos.count() LOOP
            -- Mostrar información antes de añadir nuevos hijos
            IF j.hijos.EXISTS(1) THEN
                dbms_output.put_line('El hijo: '
                                     || i
                                     || ' se llama: '
                                     || j.hijos(i));

            END IF;
        END LOOP;

        v_hijos := j.hijos;  -- Copiar la colección existente
        v_hijos.extend(3);    -- Extenderla por 3 elementos
        v_hijos(3) := 'Luis';
        v_hijos(4) := 'Luis';
        v_hijos(5) := 'Luis';
        
         -- Actualizar la tabla con la nueva colección de hijos
        UPDATE empleado
        SET
            hijos = v_hijos
        WHERE
            CURRENT OF fcur;
            
         -- Mostrar información después de añadir nuevos hijos
        dbms_output.put_line('Hijos despues de añadir ');
        FOR k IN 1..v_hijos.count() LOOP
            IF v_hijos.EXISTS(k) THEN
                dbms_output.put_line('El hijo: '
                                     || k
                                     || ' se llama: '
                                     || v_hijos(k));

            END IF;
        END LOOP;

    END LOOP;
END;
/
drop view user_varrays;
CREATE OR REPLACE VIEW user_varrays AS
SELECT
    *
FROM
    empleado;

-- Consulta para obtener información sobre los varrays
SELECT *
FROM user_varrays
/
DESCRIBE colec_hijos;
--ROLLBACK;