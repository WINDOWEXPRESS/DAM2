<?php
    require_once("../../src/config/init.php");
    require_once("../pediPoblacionId.php");

    $user = "";
    $nombre = "";
    $correo = "";
    $provincia = "";
    $poblacion = "";
    $oldPasswd = "";
    $error = "";

    if(isset($_SESSION['admin'])){
        header("location: ../admin/homeAdmin.php?Message=User_Area");
        exit();
    }

    if(isset($_SESSION['user']) || isset($_POST['anular'])){
        $baseDato -> ejecuta("SELECT * FROM usuarios WHERE ID = ?",$_SESSION['user']);
        $user = $baseDato -> obtenDatos();
        $nombre = $user[0]['nombre'];
        $correo = $user[0]['email'];
        $oldPasswd = $user[0]['contrasenia'];
        $provincia = $user[0]['provincia'];
        $poblacion = $user[0]['poblacion'];
    }else{
        header("location: ../../public/Login.php?Message=User_Is_Not_Login");
        exit();
    }

    if(isset($_POST['aceptar'])){
        if(!empty($_POST['oldPasswd']) && !empty($_POST['newPasswd'])){
            if(!password_verify($_POST['oldPasswd'],$oldPasswd)){
                $error = "Error contrasenia Antiguo Incorrecto";
            }else{
                $poblacionSelected = $_POST['poblacionSelected'];
                $poblacionId = pedirPoblacionId($poblacionSelected,"../../src/csv/codigo_municipios_AEMET.csv");
                $newPasswd = password_hash($_POST['newPasswd'],PASSWORD_DEFAULT);
                $baseDato -> ejecuta("UPDATE usuarios SET  `nombre` = ?, `contrasenia` = ?, `provincia` = ?, `poblacion` = ?, `poblacion_id` =? WHERE id = ?",
                    $_POST['userName'],
                    $newPasswd,
                    $_POST['provinciaSelected'],
                    $poblacionSelected,
                    $poblacionId,
                    $_SESSION['user']
                );
                header("location:./datosUsuario.php?changer=successful");
                exit();
            }
        }else{
            $poblacionSelected = $_POST['poblacionSelected'];
            $poblacionId = pedirPoblacionId($poblacionSelected,"../../src/csv/codigo_municipios_AEMET.csv");
            $baseDato -> ejecuta("UPDATE usuarios SET  `nombre` = ?, `provincia` = ?, `poblacion` = ?, `poblacion_id` =? WHERE id = ?",
                $_POST['userName'],
                $_POST['provinciaSelected'],
                $poblacionSelected,
                $poblacionId,
                $_SESSION['user']
            );
            header("location:./datosUsuario.php?changer=successful");
            exit();
        }
        
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/datosUsuario.css">
    <script src="../../javascript/datosUsuario.js" defer></script>
    <title>LibrayControl</title>
</head>
<body>
    <div class="mx-auto p-3" id="divFondo">
        <div class="mb-1 d-flex flex-row mb-2">
            <img class="rounded" id="imgLogo" src="../../src/img/logo.svg" alt="logo"/>
            <h1 id="h1Title">Mis Datos</h1>
        </div>
        <form class="row g-2 needs-validation" novalidate id="formUserData" method="post">
            <div class="mb-1">
                <label for="userName" class="form-label">Nombre: </label>
                <input type="text" class="form-control" name="userName" id="userName" value="<?php echo($nombre)?>">
                <div class="invalid-feedback">
                    Deben rellenar nombre de usuario
                </div>
            </div>
            <div class="mb-1">
                <label for="correo" class="form-label">Correo Electronico :</label>
                <input type="email" class="form-control" id="correo" value="<?php echo($correo)?>">
            </div>
            <div class="mb-1">
                <label for="oldPasswd" class="form-label">Contraseña Antiguo</label>
                <input type="password" class="form-control" name="oldPasswd" id="oldPasswd">
                <div class="invalid-feedback">
                    Deben rellenar contraseña con minimo 6 digito
                </div>
            </div>
            <div class="mb-1">
                <label for="newPasswd" class="form-label">Contraseña Nueva</label>
                <input type="password" class="form-control" name="newPasswd" id="newPasswd" placeholder="Introduce Contraseña Nueva">
                <div class="invalid-feedback">
                    Deben rellenar contraseña con minimo 6 digito
                </div>
            </div>
            <div class="d-flex flex-wrap">
                <input type="hidden" id="hiddenProvincia" value="<?php echo$provincia?>">
                <p class="pPais">Provincias</p>
                <select class="form-select" id="provincias" name="provincias"></select>
                <div class="invalid-feedback">
                    Seleccionar un provincia
                </div>
            </div>
            <div class="d-flex flex-wrap">
                <input type="hidden" id="hiddenPoblacion" value="<?php echo$poblacion?>">
                <p class="pPais">Poblacion</p>
                <select class="form-select" id="poblacion" name="poblacion"></select>
                <div class="invalid-feedback">
                    Seleccionar un poblacion
                </div>
            </div>
            <div class="mb-2">
                    <p class="text-danger"><?php echo($error) ?></p>
                </div>
            <div class="mb-1 d-flex flex-row mb-3 justify-content-between">
                <button type="submit" class="btn btn-success" name="aceptar">Aceptar Cambio</button>
                <button class="btn btn-danger" id="btAnular">Anular Cambio</button>
                <a class="btn btn-primary" id="btVolver" href="./homeUsuario.php" role="button">Volver</a>
            </div>
        </form>
    </div>
</body>
</html>