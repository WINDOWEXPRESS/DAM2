<?php 
    require_once("BaseDato.php");
    require_once("db.php");
    session_start();
    $baseDato = BaseDato::obtenerInstancia();
    $baseDato -> inicializa("proyecto_sge","root","");
?>