<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar coche</title>
</head>

<body>
    <?php
    include("db.php");
    $stmt = $conn->prepare("SELECT * FROM emp where EMPNO = ?");

    $stmt->execute([$_GET["id"]]);
    $result = $stmt->fetchAll();

    ?>

    <form action="update.php" method="post">
        Id empeado <input type="text" name="idEmp" value='<?php echo ($result[0]["EMPNO"]) ?>' disabled><br>
        Nombre <input type="text" name="nombreEmp" value='<?php echo ($result[0]["ENAME"]) ?>'><br>
        Trabajo <input type="text" name="trabajoEmp" value='<?php echo ($result[0]["JOB"]) ?>'><br>
        Salario <input type="text" name="salarioEmp" value='<?php echo ($result[0]["SAL"]) ?>'><br>
        <input type="hidden" name="idEmpleado" value='<?php echo $result[0]["EMPNO"]?>'>
        <input type="submit" value="Aceptar Cambios" name="cambiar">
        <input type="submit" value="Volver" name="Volver">
    </form>
</body>

</html>