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
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../.././css/home.css" type="text/css">
<<<<<<< HEAD
	<script src="../../javascript/confirmarActualizar.js" defer></script>
    <title>Editar libro</title>
=======

    <title>LibrayControl</title>
>>>>>>> be0173d7f39989d30cd2630c0cf33a8c3d9e7d4d
</head>

<body>
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
                            <li><a href="./prestacionHistorial.php">Prestacion</a></li>

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
            <h1 style="color: white;">Editar libro</h1>
            <table width="90%" class="table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Titulo</th>
                        <th>Autor</th>
                        <th>Isbn</th>
                        <th>Estado</th>
                        <th>Confirmar</th>
                    </tr>
                </thead>
                <tbody>
                    <form action="actualizarLibro.php" method="Get">
                        <?php
                        try {
                            $stmt = $conn->prepare("SELECT * FROM libros where ID = ?");

                            $stmt->execute([$_GET["id"]]);
                            $result = $stmt->fetchAll();

                            foreach ($result as $row => $libro) {

                                echo ("<tr>");
                                echo ("<td><input type='text' name='' value='" .   $libro["ID"] . "' Disabled></td>");
                                echo ("<td><input type='text' name='titulo' value='" .   $libro["titulo"] . "' ></td>");
                                echo ("<td><input type='text' name='autor' value='" .   $libro["autor"] . "' ></td>");
                                echo ("<td><input type='text' name='isbn' id = 'isbnEditar' value='" .   $libro["isbn"] .  "' ></td>");
                                echo ("<input type='hidden' name='id' value='" .   $libro["ID"] .  "' >");

                                switch ($libro["prestado"]) {
                                    case 1:
                                        echo ("<td><input type='text' name='estado' value='prestado' ></td>");
                                        break;
                                    case 0:
                                        echo ("<td><input type='text' name='estado' value='No prestado' ></td>");
                                        break;
                                    case is_null($libro["prestado"]):
                                        echo ("<td> Null </td>");
                                        break;
                                }
                                //echo ("<td> <a href='./actualizarLibro.php?titulo=" . $libro["titulo"] . "&autor=" . $libro["autor"] . "&isbn=" . $libro["isbn"] . "&prestado=" . $libro["prestado"] . "'>Confirmar</a> </td>");
                                echo ("<td> <input type='submit' value='Aceptar Cambios' name='cambiar' id='botonEditar' onclick='botonEnviar(event)'> </td>");

                                echo ("</tr>");
                            }
                        } catch (PDOException $e) {
                            echo "Error: " . $e->getMessage();
                        }
                        $conn = null;
                        ?>
                    </form>
                </tbody>
            </table>
        </div>
    </div>
</body>

</html>