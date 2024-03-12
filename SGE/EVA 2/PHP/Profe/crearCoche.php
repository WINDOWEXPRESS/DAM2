<?php

require("db.php"); //importo base de datos

$stmt = $conn->prepare("INSERT into coches (modelo, potencia, autonomia,precio) VALUES (?,?,?,?)"); //preparo consulta

$stmt->execute([$_POST["modelo"],$_POST["potencia"],$_POST["autonomia"],$_POST["precio"]]);//ejecuto
    
header("Location: coches.php");//me voy 

die(); //paro la ejecución de este php

?>