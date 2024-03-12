<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editando coche</title>
</head>
<body>

<?php

include("db.php");

$stmt = $conn->prepare("SELECT * FROM coches where id = ?");

$stmt->execute([$_GET["id"]]);
$result = $stmt->fetchAll();

?>
    
<form action="updateCar.php" method="post">

Modelo  <input type="text" name="modelo" id="" value='<?php echo $result[0]["modelo"]?>' ><br>
Potencia<input type="text" name="potencia" id="" value='<?php echo $result[0]["potencia"]?>'><br>
Autonomia<input type="text" name="autonomia" id="" value='<?php echo $result[0]["autonomia"]?>'><br>
Precio<input type="text" name="precio" id="" value='<?php echo $result[0]["precio"]?>'><br>
    <input type="hidden" name="id" value='<?php echo $result[0]["id"]?>'>
<input type="submit" value="Aceptar Cambios" name="cambiar">
<input type="submit" value="Volver" name="Volver">
</form>


</body>
</html>