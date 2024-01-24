<?php

require("db.php"); //importo base de datos

$stmt = $conn->prepare("INSERT INTO emp(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES (?,?,?,12-12-12,?,100,10)"); //preparo consulta

$stmt->execute([$_POST["idEmp"],$_POST["nombreEmp"],$_POST["trabajoEmp"],$_POST["salarioEmp"]]);//ejecuto
    
header("Location: empleado.php");//me voy 

die(); //paro la ejecución de este php

?>