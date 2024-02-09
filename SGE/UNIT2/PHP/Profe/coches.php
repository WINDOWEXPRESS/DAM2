<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Coches</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<table class="table table-striped">
    <tr>
        <th>Modelo </th>
        <th>Potencia </th>
        <th>Autonomia </th>
        <th>Precio </th>
        <th></th>
        <th></th>
    </tr>
<?php
include("header.php");
include("db.php");

$stmt = $conn->prepare("SELECT * FROM coches");

$stmt->execute();
$result = $stmt->fetchAll();


//print_r($result);

//$result

echo '';
foreach ($result as $row => $coche) {
    echo "<tr>";
    echo   '<td>' . $coche["modelo"] . '</td> <td> '. $coche["potencia"] . 
    '</td> <td>' . $coche["autonomia"] . '</td> <td> ' . $coche["precio"] . '</td>' ;
    echo "<td> <a href='editarCoche.php?id=". $coche["id"] ."'>Editar</a> </td>";
    echo "<td> <a href=''>Borrar</a> </td>";

    echo "</tr>";
    }

//echo "el tamaño del array es ". count($result);

?>
<form action="crearCoche.php" method="post">
<td><input type="text" name="modelo" id=""></td>
<td><input type="text" name="autonomia" id=""></td>
<td><input type="text" name="potencia" id=""></td>
<td><input type="text" name="precio" id=""></td>
<td><input type="submit" value="Añadir nuevo"></td>
</form>

</table>
</body>
</html>

<script>
   
</script>