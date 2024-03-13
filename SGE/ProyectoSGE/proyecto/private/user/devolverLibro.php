<?php
    require_once("../../src/config/init.php");
    //$prestado = 1;
    $noPrestado = 0;
    try{
    $stmt = $conn->prepare("UPDATE libros SET prestado = ?  WHERE ID=?");
    $stmt->bindParam(1, $noPrestado);
    $stmt->bindParam(2, $_GET["idLibro"]);   
    $stmt->execute();
    
    $result = $stmt->fetchAll();
    
    $stmt = $conn1->prepare("UPDATE prestamos SET fechaFinal = sysdate()  WHERE idLibro=?");
    $stmt->bindParam(1, $_GET["idLibro"]);
    $stmt->execute();
    
    $result = $stmt->fetchAll();
    }catch(PDOException $e) {
        echo "Error: " . $e->getMessage();
    }
    $conn = null;
    $conn1 = null;

    header("Location: ./homeUsuario.php");

    die(); 

?>