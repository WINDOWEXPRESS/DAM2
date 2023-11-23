DROP TYPE telefono;

DROP TYPE listin;

DROP TABLE clientes;

CREATE TYPE telefono AS OBJECT (
    tipo    VARCHAR2(30),
    numero  NUMBER
)
/

CREATE OR REPLACE TYPE listin AS
    TABLE OF telefono;
/

CREATE TABLE clientes (
    id_cli     NUMBER,
    nombre     VARCHAR(30),
    apellido   VARCHAR(30),
    dirección  VARCHAR(30),
    población  VARCHAR(30),
    provincia  VARCHAR(30),
    telefonos  listin
)
NESTED TABLE telefonos STORE AS tel_tab;
/

INSERT INTO clientes VALUES (
    1,
    'Franciso',
    'Perez',
    'C/ Sol 14',
    'Madrid',
    'Madrid',
    listin(telefono('telefono', 611222333), telefono('movil', 611222334), telefono('movil', 611222335))
);

INSERT INTO clientes VALUES (
    2,
    'Fernando',
    'Alonso',
    'C/ Sol 34',
    'Madrid',
    'Madrid',
    listin(telefono('telefono', 611111111), telefono('movil', 611111112), telefono('movil', 611111113))
);

    INSERT INTO clientes VALUES (
        3,
        'Mauriño',
        'Alonso',
        'C/ Luna 34',
        'Sevilla',
        'Andalucia',
        listin(telefono('telefono', 622222221), telefono('movil', 622222222), telefono('movil', 622222223))
    );
/

SELECT
    *
FROM
    clientes;
/

SELECT
    object_name
FROM
    user_objects
WHERE
    object_type = 'TABLE'
ORDER BY
    object_name;
/

SELECT
    *
FROM
    user_nested_tables;
/

SELECT
    c.id_cli,
    c.nombre,
    c.apellido,
    tel.tipo,
    tel.numero
FROM
    clientes                 c,
    TABLE ( c.telefonos )    tel
WHERE
    id_cli = 3;
/

SELECT
    tel.numero
FROM
    clientes                 c,
    TABLE ( c.telefonos )    tel;
/

UPDATE clientes
SET
    telefonos = listin(telefono('fijo', 934444444), telefono('movil personal', 65555555), telefono('movilempresa', 644444444))
WHERE
    id_cli = 1;