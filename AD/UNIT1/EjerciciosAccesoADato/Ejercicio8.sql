SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE addproduct(ordenid number, productid number, cant number,clientid number)
AS
BEGIN
    INSERT INTO SALES_ORDER VALUES(ordenid,SYSDATE,clientid,SYSDATE,cant);
    INSERT INTO ITEM VALUES(ordenid,1,productid,32,1,100);
    DBMS_OUTPUT.PUT_LINE('Producto añadido corrcto!');
END;
/

EXECUTE addproduct(622,100860,10,205);