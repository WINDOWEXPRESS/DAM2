drop type tipo_direccion;
drop type tipo_contacto;
drop type tipo_persona;
drop type tipo_cliente;
drop type tipo_articulo;
drop type tabla_articulos;
drop type tipo_lista_detalle;
drop type tab_lista_detalle;
drop type tipo_lista_compra;
drop table clientes;
drop table compra;

CREATE TYPE tipo_direccion AS OBJECT (
    direccion   VARCHAR2(50),
    cod_postal  NUMBER(6)
);
/

CREATE TYPE tipo_contacto AS OBJECT (
    email     VARCHAR2(50),
    telefono  VARCHAR2(50)
);
/

CREATE TYPE tipo_persona AS OBJECT (
    id        NUMBER(6),
    nombre    VARCHAR2(50),
    apellido  VARCHAR2(50),
    direc     tipo_direccion,
    contac    tipo_contacto
) NOT FINAL;
/

CREATE TYPE tipo_cliente UNDER tipo_persona (
    numero_pedidos NUMBER(6)
);
/

DESCRIBE tipo_direccion;

DESCRIBE tipo_contacto;

DESCRIBE tipo_persona;

DESCRIBE tipo_cliente;

/

CREATE TYPE tipo_articulo AS OBJECT (
    id           NUMBER(6),
    nombre       VARCHAR2(50),
    descripcion  VARCHAR2(100),
    precio       NUMBER(6),
    iva          NUMBER(6, 2)
);
/

CREATE TYPE tabla_articulos AS
    TABLE OF tipo_articulo; 
/ 
describe tipo_articulo;
/
CREATE TYPE tipo_lista_detalle AS OBJECT (
    numero    NUMBER(6),
    cantidad  NUMBER(6),
    articulo  tipo_articulo
);
/
CREATE TYPE tab_lista_detalle AS
    TABLE OF tipo_lista_detalle;
/

CREATE TYPE tipo_lista_compra AS OBJECT ( 
    id NUMBER(6), 
    fecha DATE, 
    cliente ref tipo_cliente,
    detalle tab_lista_detalle,
    MEMBER FUNCTION total RETURN NUMBER
);
/

CREATE OR REPLACE TYPE BODY tipo_lista_compra AS
    MEMBER FUNCTION total RETURN NUMBER IS
        total      NUMBER;
    BEGIN
        total := 0;
        for i in detalle.first .. detalle.last loop
        
            total := total + (detalle(i).cantidad * detalle(i).articulo.precio) + 
                ((detalle(i).cantidad * detalle(i).articulo.precio)*detalle(i).articulo.iva/100);
        end loop;
    RETURN total;
END;

END;
/
CREATE TABLE clientes OF tipo_cliente;
/

INSERT INTO clientes VALUES (
    1, 'Pepe', 'Lopez', tipo_direccion('C/Sol 2', 28055), tipo_contacto('PepeLopez69@gmail.com', '611 222 333'),
    0
);
/

INSERT INTO clientes VALUES (
    2, 'Lola', 'Lopez', tipo_direccion('C/Sol 6', 28035), tipo_contacto('LolaLopez69@gmail.com', '633 222 111'),
    0
);
/

CREATE TABLE compra OF tipo_lista_compra nested table detalle store as detalle_tab;
/

INSERT INTO compra VALUES ( 
    tipo_lista_compra(
        1, 
        sysdate,
        (
        SELECT
            ref(c)
        FROM
            clientes c
        WHERE
            c.id = 1
        ),
        tab_lista_detalle(
            tipo_lista_detalle(
                1001, 
                2, 
                tipo_articulo(
                    123,
                    'Pan', 
                    'Pan de galicia', 
                    1, 
                    20
                )
            ),
            tipo_lista_detalle(
                1002, 
                3, 
                tipo_articulo(
                    124,
                    'Leche', 
                    'Leche de vaca de Galicia', 
                    20, 
                    10
                )
            )
        )
    )
);
/
select * from compra;
/
select id , c.total() from compra c;
