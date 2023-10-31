DROP TABLE TEMPORAL;
CREATE TABLE TEMPORAL(
    VEMPLOYEE_ID NUMBER(4,0) NOT NULL,
    VNAME VARCHAR2(15 BYTE) NOT NULL,
    VJOB VARCHAR2(30 BYTE) NOT NULL
);

DECLARE
    TYPE T_REG IS RECORD(
        VEMPLOYEE_ID employee.employee_id%TYPE,
        VNAME employee.first_name%TYPE,
        VJOB job.function%TYPE
        );
    VEMPLOYEE1 T_REG;
BEGIN
    select em.employee_id,em.first_name,jb.function into vemployee1 from employee em, job jb WHERE em.job_id = jb.job_id and em.employee_id = 7782;
    
    INSERT INTO temporal VALUES (vemployee1.VEMPLOYEE_ID,vemployee1.VNAME,vemployee1.VJOB);
END;
/

SELECT * FROM temporal;