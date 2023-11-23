CREATE OR REPLACE TYPE hijos AS OBJECT (
    nombre VARCHAR(25)
);
/

CREATE TYPE tabla_hijos AS
    TABLE OF hijos;
/

CREATE TABLE empleado (
    idemp      NUMBER,
    nombre     VARCHAR(30),
    apellidos  VARCHAR(30),
    hijos      tabla_hijos
)
NESTED TABLE hijos STORE AS t_hijos
/

INSERT INTO empleado VALUES (
    1,
    'Fernando',
    'Moreno',
    tabla_hijos(hijos('Elena'), hijos('Pablo'))
);

    INSERT INTO empleado VALUES (
        2,
        'David',
        'Sanchez',
        tabla_hijos(hijos('Carmen'), hijos('Candela'))
    );
/

SELECT
   e.idemp, e.nombre,hijo.nombre
FROM
    empleado             e,
    TABLE ( e.hijos )    hijo;
/

UPDATE empleado
SET
    hijos = tabla_hijos(hijos('Carmen'), hijos('Candela'), hijos('Cayetana'))
WHERE
    idemp = 1;