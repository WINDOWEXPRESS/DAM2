DROP TABLE PL;
CREATE TABLE PL (
    plid INTEGER NOT NULL,
    firstname VARCHAR(15) NOT NULL
);

CREATE OR REPLACE PROCEDURE SHOWCLIENT
IS
    vplid INTEGER DEFAULT(1);
    vfirstName employee.first_name%type;
    CURSOR C1 IS SELECT FIRST_NAME FROM employee;    
BEGIN
    OPEN C1;
    LOOP
        FETCH C1 INTO vfirstname;
        EXIT WHEN c1%NOTFOUND;
            INSERT INTO PL VALUES(vplid,vfirstname);
            vplid := vplid + 1;
    END LOOP;
    CLOSE C1;
END;
/

EXECUTE SHOWCLIENT();

SELECT * FROM PL;