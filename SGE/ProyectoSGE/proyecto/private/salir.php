<?php
    require_once("../src/config/init.php");
    if(isset($_SESSION['user'])){
        unset($_SESSION['user']);
        setcookie('tiempoDia', '', -1, '/');
        setcookie('idioma', '', -1, '/'); 
        header("location:../public/Login.php?LogOut=true");
        exit();
    }
    if(isset($_SESSION['admin'])){
        unset($_SESSION['admin']);
        header("location:./admin/LoginAdmin.php?LogOut=true");
        exit();
    }
?>