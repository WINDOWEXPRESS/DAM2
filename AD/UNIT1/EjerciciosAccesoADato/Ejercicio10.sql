DROP TABLE codemp;

CREATE TABLE codemp(
    INFO VARCHAR2(50 BYTE)
);

CREATE OR REPLACE PROCEDURE cgjob(empid number)
IS
   vofold job.function%type;
   vofnew job.function%type;
BEGIN
    SELECT j.function INTO vofold FROM employee e , job j WHERE e.job_id = j.job_id and e.employee_id = empid;
    UPDATE employee SET job_id = 671 WHERE employee_id = empid;
    SELECT j.function INTO vofnew FROM employee e , job j WHERE e.job_id = j.job_id and e.employee_id = empid;
    INSERT INTO codemp VALUES('oficio anterio: '||vofold || ' oficio nuevo: ' ||vofnew);
END;
/

EXECUTE cgjob(7609);

SELECT * FROM codemp;