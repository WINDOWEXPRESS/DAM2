<?php
    require_once("../../src/config/init.php");

    var_dump($_GET);
    $user = $_GET['user'];
    $idioma = $_GET['idioma'];
    $baseDato->ejecuta("UPDATE usuarios SET idioma = ? WHERE ID = ?",$idioma,$user);
    setcookie("idioma",$idioma);
    header("Location:./homeUsuario.php?idioma_changer=successful");
    exit();
?>