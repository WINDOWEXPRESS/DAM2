
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
<form action="index1.php" method="post">
    Nombre<input type="text" name="nombre" id="nombre">
    Años<input type="text" name="anios" id="anios">

    <button type="submit">Enviar</button>


</form>


</body>
</html>

<?php
    if (isset($_POST["nombre"])) {
        echo "<br>Mi nombre es " . $_POST["nombre"] . " desde if";
    }
    if (count($_POST) == 2) {
        echo "<br>Mi nombre es " . $_POST["nombre"] . " y tengo ". $_POST["anios"] . " años";
    }
	$pasw = "madrid";
    //var_dump($_POST);
    if (isset($_POST["pass"])) {
		if ($pw = $_POST["pass"]) {
			header("Location: index.php");
			die();
		}
    }
    
?>