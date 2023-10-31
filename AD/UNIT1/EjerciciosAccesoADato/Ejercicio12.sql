CREATE TABLE pl (
    plid NUMBER,
    clientid NUMBER
);

CREATE OR REPLACE PACKAGE Actualiza AS
    PROCEDURE Altp(plid NUMBER, clid NUMBER);
    PROCEDURE Bajp(clid NUMBER);
    PROCEDURE Lisp(clid NUMBER, ped OUT sys_refcursor);
END Actualiza;
/
CREATE OR REPLACE PACKAGE BODY Actualiza AS
    PROCEDURE Altp(plid NUMBER, clid NUMBER) AS
    BEGIN
        INSERT INTO pl VALUES (plid, clid);
    END Altp;
    PROCEDURE Bajp(clid NUMBER) AS
    BEGIN
        DELETE FROM pl WHERE clientid = clid;
    END Bajp;
    PROCEDURE Lisp(clid NUMBER, pedido OUT sys_refcursor) AS
    BEGIN
        OPEN pedido FOR
        SELECT plid FROM pl WHERE clientid = clid;
    END Lisp;
END Actualiza;
/