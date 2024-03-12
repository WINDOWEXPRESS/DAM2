<?php
    require_once("../src/config/init.php");
    require_once("./PublicValid.php");
    require_once("../private/pediPoblacionId.php");
    if(isset($_POST['registrar'])){
        $poblacionSelected = $_POST['poblacionSelected'];
        $poblacionId = pedirPoblacionId($poblacionSelected,"../src/csv/codigo_municipios_AEMET.csv");
        $baseDato -> ejecuta("INSERT INTO usuarios(nombre,email,contrasenia,provincia,poblacion,poblacion_id,idioma) VALUES(?,?,?,?,?,?,?)",
            $_POST['userName'],
            $_POST['correo'],
            password_hash( $_POST['passwd'],PASSWORD_DEFAULT),
            $_POST['provinciaSelected'],
            $_POST['poblacionSelected'],
            $poblacionId,
            $_POST['idioma']
        );
        
        header("location:./Login.php?register=successful");
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
        <link href="../css/comun.css" rel="stylesheet"/>
        <link href="../css/register.css" rel="stylesheet"/>
        <script src="../javascript/register.js" defer></script>
    </head>
    <body>
        <div class="mx-auto p-3" id="divFondo">
            <img class="rounded" id="imgLogo" src="../src/img/logo.svg" alt="logo"/>
            <h1>Registra</h1>
            <hr/>
            <form class="row g-2 needs-validation" novalidate id="formRegiste" method="post">
                <div class="mb-1">
                    <label for="userName" class="form-label">Nombre de usuario</label>
                    <input type="text" class="form-control" name="userName" id="userName" placeholder="Introduce Nombre de usuario">
                    <div class="invalid-feedback">
                        Deben rellenar nombre de usuario
                    </div>
                </div>
                <div class="mb-1">
                    <label for="correo" class="form-label">Correo Electronico</label>
                    <input type="email" class="form-control" name="correo" id="correo" placeholder="Introduce tu correo electronico">
                    <div class="invalid-feedback">
                        Deben rellenar Correo electronico
                    </div>
                </div>
                <div class="mb-1">
                    <label for="passwd" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" name="passwd" id="passwd" placeholder="Introduce tu contraseña">
                    <div class="invalid-feedback">
                        Deben rellenar contraseña con minimo 6 digito
                    </div>
                </div>
                <div class="mb-1">
                    <label for="rePasswd" class="form-label">Repite Contraseña</label>
                    <input type="password" class="form-control" id="rePasswd" placeholder="Repite la contaseña">
                    <div class="invalid-feedback">
                        Contraseña repetido no es igual a contraseña anterior
                    </div>
                </div>
                <div class="d-flex flex-wrap">
                    <p class="pPais">Provincias</p>
                    <select class="form-select" id="provincias" name="provincias">
                        <option id="nullOption" value="0" selected>--Selecciona Un Provincia--</option>
                    </select>
                    <div class="invalid-feedback">
                        Seleccionar un provincia
                    </div>
                </div>
                <div class="d-flex flex-wrap">
                    <p class="pPais">Poblacion</p>
                    <select class="form-select" id="poblacion" name="poblacion"></select>
                    <div class="invalid-feedback">
                        Seleccionar un poblacion
                    </div>
                </div>
                <div class="mb-1">
                    <p id="pIdioma">Idioma</p>
                    <input class="form-check-input me-1" type="radio" name="idioma" value="es" id="es">
                    <label for="es">Español</label>
                    <input class="form-check-input me-1" type="radio" name="idioma" value="en" id="en">
                    <label for="en">Ingles</label>
                    <div class="invalid-feedback">
                        Seleccionar un idiomas
                    </div>
                </div>
                <div class="mb-1">
                    <a href="./Login.php"> Ya Tengo La Cuenta</a>
                </div>
                <div class="mb-1">
                    <button type="submit" class="btn btn-primary" id="register" name="registrar">Registrar</button>
                </div>
            </form>
        </div>
        <hr id="hrfood">
        <p class="mt-2 mb-3 text-body-secondary text-center">&copy; 2024</p>
    </body>
</html>