<?php
require_once("../../src/config/init.php");
if (isset($_SESSION['user'])) {
    header("location: ../user/homeUsuario.php?Message=Area_Admin");
    exit();
}
if (!isset($_SESSION['admin'])) {
    header("location: ./LoginAdmin.php?Message=Admin_Is_Not_Login");
    exit();
}
?>

<!doctype html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="../../javascript/confirmarActualizarContrasenia.js" defer></script>
    <title>CLibrayControl</title>
    <link rel="stylesheet" href="../.././css/home.css" type="text/css">
    <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"> -->
</head>

<body class="p-3 m-0 border-0 bd-example m-0 border-0">
    <div class="header">
        <div class="Bienvenido">
            <img class="Favicon32x32" src="../../src/img/logo.svg" alt="Favicon32x32">
            <p> Bienvenido</p>
        </div>
        <div class="Nav-Menu">
            <nav class="Menu">
                <ul>
                    <li>
                        <a href="#">Opciones</a>
                        <ul>
                            <li><a href="./usuarioAdmin.php">Usuarios</a></li>
                            <li><a href="./homeAdmin.php">Libros</a></li>
                            <li><a href="./prestacionHistorial.php">Prestación</a></li>

                        </ul>
                    </li>
                    <li>
                        <a href="../salir.php">Salir</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

    <div class="cuerpo">
        <div class="search bar1">
            <div class="titulo">
                <h1 style="color: white;">Tabla usuario</h1>
                <table width="90%" class="table" id="tabla">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Email</th>
                            <th>contraseña</th>
                            <th>provincia</th>
                            <th>población</th>
                            <th>Código postal</th>
                            <th></th>
                        </tr>
                        <td colspan="9">
                            <input id="buscar" type="text" class="form-control" placeholder="Escriba algo para filtrar" />
                        </td>
                    </thead>

                    <tbody>
                            <?php
                            try {
                                $stmt = $conn->prepare("SELECT * FROM usuarios");

                                $stmt->execute();
                                $result = $stmt->fetchAll();

                                foreach ($result as $row => $usuario) {

                                    echo("<form action='actualizarContrasenia.php' method='Get'>");
                                    echo ("<tr>");
                                    echo ("<td>" .   $usuario["ID"] . "</td> ");
                                    echo ("<td>" .   $usuario["nombre"] . "</td>");
                                    echo ("<td>" .   $usuario["email"] . "</td>");
                                    echo ("<td><input type='text' name='contrasenia' value='" .   $usuario["contrasenia"] .  "' ></td>");
                                    echo ("<td>" .   $usuario["provincia"] . "</td>");
                                    echo ("<td>" .   $usuario["poblacion"] . "</td>");
                                    echo ("<td>" .   $usuario["poblacion_id"] . "</td>");
                                    echo ("<input type='hidden' name='id' value='" .   $usuario["ID"] .  "' >");
                                    echo ("<td> <input type='submit'  value='Confirmar' onclick = \"preguntarActualizar(event)\"></td>");

                                    echo ("</tr>");
                                    echo ("</form>");
                                }
                            } catch (PDOException $e) {
                                echo "Error: " . $e->getMessage();
                            }
                            $conn = null;

                            ?>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>

</html>