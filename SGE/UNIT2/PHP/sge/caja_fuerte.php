<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Divinar Contraseña</title>
</head>
<body>
    <form action="caja_fuerte.php" method="post">
        <h1>Caja Fuerte</h1>
        <p>Es un numero entre 0 a 10 y hay 5 intentos</p>
        <br>
        Adivinar <input type="text" name="numero" id="numero">
        <button type="submit">Enviar</button>
    </form>
</body>
</html>

<?php
    session_start();
    if(count($_POST) == 0){
        $_SESSION["intentos"] = 5;
        $_SESSION["contrasenia"] = rand(0,10);
    }
 

    echo($_SESSION["intentos"] ."<br>");
    echo($_SESSION["contrasenia"] . "<br>");

        if($_POST["numero"] != $_SESSION["contrasenia"] && $_SESSION["intentos"] > 0){
            echo "Te quedas " . $_SESSION["intentos"] , " intentos <br>";
            $_SESSION["intentos"]--;
        }
        if($_SESSION["intentos"] == 0){
            echo("Se acabo el numero de intentos");
        }
        if($_SESSION["intentos"] == $_SESSION["contrasenia"]){
            echo("Has adivinado el numero secreto " . $_SESSION["contrasenia"]);
        }
    //session_destroy();
?>