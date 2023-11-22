CREATE OR REPLACE TYPE colec_tipo_nombres_dept AS
    VARRAY(7) OF VARCHAR(30);
/

CREATE TABLE departamentos (
    region  VARCHAR(25),
    dept    colec_tipo_nombres_dept
);
/

INSERT INTO departamentos VALUES (
    'Europa',
    colec_tipo_nombres_dept('shipping', 'sales', 'finances')
);

INSERT INTO departamentos VALUES (
    'America',
    colec_tipo_nombres_dept('sales', 'finances', 'shipping')
);

    INSERT INTO departamentos VALUES (
        'Asia',
        colec_tipo_nombres_dept('finances', 'payroll', 'shipping', 'sales')
    );
/

SELECT
    *
FROM
    departamentos;
/

SET SERVEROUTPUT ON;

DECLARE
    v_dept colec_tipo_nombres_dept := colec_tipo_nombres_dept('benefits', 'advertising', 'contracting', 'executive', 'marketing');
BEGIN
    -- Inicia una transacción explícita
        BEGIN
        -- Intenta actualizar la fila
                UPDATE departamentos d
        SET
            d.dept = v_dept
        WHERE
            d.region = 'Europa';

        -- Si la actualización tiene éxito, imprime un mensaje
                dbms_output.put_line('Fila actualizada exitosamente.');
    EXCEPTION
        WHEN OTHERS THEN
            -- Si hay un error, imprime un mensaje de error
                        dbms_output.put_line('Error: ' || sqlerrm);
            ROLLBACK; -- Realiza un ROLLBACK en caso de error
        END;

    -- Imprime los valores de la colección después de la actualización o en caso de error
        FOR k IN 1..v_dept.count() LOOP
        IF v_dept.EXISTS(k) THEN
            dbms_output.put_line('departamentos = ' || v_dept(k));
        END IF;
    END LOOP;

    COMMIT; -- Confirma la transacción explícita
END;
/

SET SERVEROUTPUT ON;

DECLARE
    CURSOR fcur IS
    SELECT
        *
    FROM
        departamentos;

BEGIN
    FOR j IN fcur LOOP
        dbms_output.put_line('Region: ' || j.region);
        FOR k IN 1..j.dept.count() LOOP
            IF j.dept.EXISTS(k) THEN
                dbms_output.put_line('departamentos  ('
                                     || k
                                     || ')'
                                     || j.dept(k));

            END IF;
        END LOOP;

    END LOOP;
END;
/

SELECT
    *
FROM
    departamentos;