<?php
    require_once("../../src/config/init.php");
    require_once("./PrivateValid.php");

    if(isset($_POST['registrar'])){
        $baseDato -> ejecuta("INSERT INTO admini(nombre,contrasenia) VALUES(?,?)",
            $_POST['userName'],
            password_hash( $_POST['passwd'],PASSWORD_DEFAULT),
        );

        header("location:./LoginAdmin.php?register=successful");
        exit();
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
        <link href="../../css/register.css" rel="stylesheet"/>
        <script src="../../javascript/registerAdmin.js" defer></script>
    </head>
    <body>
        <div class="mx-auto mt-5 p-3" id="divFondo">
            <h1>Registre</h1>
            <hr/>
            <form class="row g-2 needs-validation" novalidate id="formRegiste" method="post">
                <div class="mb-1">
                    <label for="userName" class="form-label">Nombre</label>
                    <input type="text" class="form-control" name="userName" id="userName" placeholder="Introduce Nombre de administrador">
                    <div class="invalid-feedback">
                        Deben rellenar nombre administrador
                    </div>
                </div>
                <div class="mb-1">
                    <label for="passwd" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" name="passwd" id="passwd" placeholder="Introduce tu contraseña">
                    <div class="invalid-feedback">
                        Deben rellenar contraseña
                    </div>
                </div>
                <div class="mb-1">
                    <label for="rePasswd" class="form-label">Repite Contraseña</label>
                    <input type="password" class="form-control" id="rePasswd" placeholder="Repite la contaseña">
                    <div class="invalid-feedback">
                        Contraseña repetido no es igual a contraseña anterior
                    </div>
                </div>
                    <button type="submit" class="btn btn-primary" name="registrar" id="register">Registrar</button>
                </div>
            </form>
        </div>
    </body>
</html>