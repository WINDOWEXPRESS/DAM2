<?php
    require_once("../src/config/init.php");
    require_once("./PublicValid.php");

    $error = "";
    
    if(isset($_POST['loguea'])){
        $correo = $_POST['correo'];
        $passwd = $_POST['passwd'];
        $baseDato -> ejecuta("SELECT * FROM usuarios WHERE email = ?",$correo);
        $user = $baseDato -> obtenDatos();

        if(empty($user) || !password_verify($passwd,$user[0]['contrasenia'])){
            $error = "Error correo de usuario o contraseña incorrecto";
        }else{
            $_SESSION['user'] = $user[0]['ID'];
            header("location: ../private/user/homeUsuario.php?login=Successful");
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
        <link href="../css/comun.css" rel="stylesheet"/>
        <link href="../css/login.css" rel="stylesheet"/>
        <script src="../javascript/login.js" defer></script>
    </head>
    <body>
        <div class="mx-auto p-3" id="divFondo">
            <img class="rounded" id="imgLogo" src="../src/img/logo.svg" alt="logo"/>
            <h1>Loguea</h1>
            <hr/>
            <form class="row g-2" id="formLogin" method="post">
                <div class="mb-3">
                    <label for="correo" class="form-label">Correo Electronico</label>
                    <input type="email" class="form-control" name="correo" id="correo" placeholder="Introduce tu correo electronico">
                    <div class="invalid-feedback">
                        <p>Error correo electronico incorrecto!</p>
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
                <div class="mb-3">
                    <a href="./Register.php">No Tengo La Cuenta</a>
                </div>
                <button type="submit" class="btn btn-primary" name="loguea" value="loguea">Loguea</button>
            </form>
        </div>
        <hr id="hrfood">
        <p class="mt-2 mb-3 text-body-secondary text-center">&copy; 2024</p>
    </body>
</html>