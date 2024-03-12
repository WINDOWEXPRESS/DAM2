<?php
    require_once("../../src/config/init.php");
    require_once("./PrivateValid.php");
    $error = "";

    if(isset($_POST['loguea'])){
        $nombre = $_POST['userName'];
        $passwd = $_POST['passwd'];
        $baseDato -> ejecuta("SELECT * FROM admini WHERE nombre = ?",$nombre);
        $admini = $baseDato -> obtenDatos();

        if(empty($admini) || !password_verify($passwd,$admini[0]['contrasenia'])){
            $error = "Error nombre de administrador o contraseña incorrecto";
        }else{
            $_SESSION['admin'] = $admini[0]['ID'];
            header("location: ./homeAdmin.php");
            exit();
        }
    }
?>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>LibrayControl</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="../../css/comun.css" rel="stylesheet"/>
        <link href="../../css/login.css" rel="stylesheet"/>
        <script src="../../javascript/loginAdmin.js" defer></script>
    </head>
    <body>
        <div class="mx-auto p-3" id="divFondo">
            <img class="rounded" id="imgLogo" src="../../src/img/logo.svg" alt="logo"/>
            <h1>Logueate</h1>
            <hr/>
            <form class="row g-2" id="formLogin" method="post">
                <div class="mb-3">
                    <label for="userName" class="form-label">Nombre</label>
                    <input type="text" class="form-control" name="userName" id="userName" placeholder="Introduce nombre de administrador">
                    <div class="invalid-feedback">
                        <p>Error nombre incorrecto!</p>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="passwd" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" name="passwd" id="passwd" placeholder="Introduce tu contraseña">
                    <div class="invalid-feedback">
                        <p>Error contraseña incorrecto!</p>
                    </div>
                </div>
                <div class="mb-2">
                    <p class="text-danger"><?php echo($error) ?></p>
                </div>
                <button type="submit" class="btn btn-primary" name="loguea">Loguea</button>
            </form>
        </div>
    </body>
</html>