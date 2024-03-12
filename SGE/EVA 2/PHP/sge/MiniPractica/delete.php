<?php

require("db.php");


    
    $stmt = $conn->prepare("UPDATE emp SET ENAME = ? , JOB =? , SAL = ? WHERE EMPNO=?");
    $stmt->execute([$_POST["nombreEmp"],$_POST["trabajoEmp"],$_POST["salarioEmp"],$_POST["idEmpleado"]]);
    
    $result = $stmt->fetchAll();


    header("Location: empleado.php");
    die();



?>     