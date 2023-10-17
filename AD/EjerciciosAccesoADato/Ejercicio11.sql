CREATE OR REPLACE FUNCTION Anual(
    salm NUMBER,
    com NUMBER
) RETURN NUMBER
IS
    sala NUMBER;
    vsalm NUMBER;
    vcom NUMBER;
BEGIN
    vsalm := salm;
    IF vsalm IS NULL THEN
        vsalm := 0;
    END IF;
    
    vcom := com;
    IF vcom IS NULL THEN
        vcom := 0;
    END IF;

    sala := (vsalm * 12) + vcom;

    RETURN sala;
END Anual;
/

 SELECT Anual(salary,commission) FROM employee WHERE employee_id = 7369;