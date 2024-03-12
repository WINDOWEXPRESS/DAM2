<?php
//var_dump($_POST);

//si ha pulsado volver, header(location: coches.php)
//else, hacemos el update en la base de datos

require("db.php");

if(isset($_POST["Volver"])){
    header("Location: coches.php");
    die();
}
else
{
     
    
    $stmt = $conn->prepare("UPDATE coches SET modelo = ? , potencia = ? , autonomia = ? , precio =? 
    WHERE id = ?");
    $stmt->execute([$_POST["modelo"],$_POST["potencia"],$_POST["autonomia"],
                    $_POST["precio"],$_POST["id"]]);
    
    $result = $stmt->fetchAll();


    /*if($stmt->rowCount()<1)
     echo "ha habido un fallo"
    */  
    header("Location: coches.php");
    die();

}




?>