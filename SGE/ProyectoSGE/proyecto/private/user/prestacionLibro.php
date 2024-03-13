<?php
    require_once("../../src/config/init.php");
    require_once("./idioma.php");
    $prestado = 1;
    //$noPrestado = 0;
    $stmt = $conn->prepare("UPDATE libros SET prestado = ?  WHERE ID=?");
    $stmt->execute([$prestado,$_POST["idLibro"]]);
    
    $result = $stmt->fetchAll();
    
    //print_r($result);
    //echo "el tamaño del array es ".  $stmt->rowCount();
    if($stmt->rowCount() == 0){
        if(isset($_COOKIE['idioma'])){
            $idiomaSelect = $idioma[$_COOKIE['idioma']];
        }
        $_SESSION["mensajeErrorPrestacion"] = $idiomaSelect['ErrorPrestacion'];
    }else{
        if(isset($_SESSION["mensajeErrorPrestacion"]))
            unset($_SESSION['mensajeErrorPrestacion']);
        try{
            $stmt = $conn->prepare("INSERT into prestamos ( idLibro , idUsuario ,fechaInicio ) VALUES (? , ? ,sysdate())");
            $stmt->execute([$_POST["idLibro"],$_SESSION["user"]]);

            $result = $stmt->fetchAll();
    
            print_r($result);
            
        } catch(PDOException $e) {
            echo "Error: " . $e->getMessage();
        }
        $conn = null;
    
    }

    header("Location: ./homeUsuario.php");

    die(); 

?>