<?php

    require("db.php"); //importo base de datos
    $prestado = 1;
    //$noPrestado = 0;
    $stmt = $conn->prepare("UPDATE libros SET prestado = ?  WHERE ID=?");
    $stmt->execute([$prestado,$_POST["idLibro"]]);
    
    $result = $stmt->fetchAll();

    //print_r($result);
    //echo "el tamaño del array es ".  $stmt->rowCount();
    if($stmt->rowCount() == 0){
        $_SESSION["mensajeErrorPrestacion"] = "Error al prestar el libro ,Esto puede ser causa de ya es prestado o id incorrecto.";
        $php_errormsg = "Error al prestar el libro ,Esto puede ser causa de ya es prestado o id incorrecto.";
    }else{
        try{
            $stmt = $conn->prepare("INSERT into prestamos ( idLibro , idUsuario ,fechaInicio ) VALUES (? , ? ,sysdate())");
            $stmt->execute([$_POST["idLibro"],$_SESSION["ID"]]);

            $result = $stmt->fetchAll();
    
            print_r($result);
            
        } catch(PDOException $e) {
            echo "Error: " . $e->getMessage();
        }
        $conn = null;
    
    }

    header("Location: ./../front/homeUsuario.php");

    die(); 

?>