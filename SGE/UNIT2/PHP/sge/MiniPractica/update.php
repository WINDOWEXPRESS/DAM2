<?php

//var_dump($_POST);

require("db.php");

if(isset($_POST["Volver"])){
    header("Location: empleado.php");
    die();
}else{
    
     $stmt = $conn->prepare("UPDATE emp SET ENAME = ? , JOB =? , SAL = ? WHERE EMPNO=?");
    $stmt->execute([$_POST["nombreEmp"],$_POST["trabajoEmp"],$_POST["salarioEmp"],$_POST["idEmpleado"]]);
    
    $result = $stmt->fetchAll();

    print_r($result);
    echo "el tamaño del array es ". $stmt->rowCount(); 

    header("Location: empleado.php");
    die();
}



?>     