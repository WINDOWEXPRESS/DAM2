SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE DELETEORDER(clid number)
is
BEGIN
    DELETE FROM sales_order WHERE customer_id = clid;
    DBMS_OUTPUT.put_line('Borrado ha sido corrcto!');
END;
/
EXECUTE DELETEORDER(622);
