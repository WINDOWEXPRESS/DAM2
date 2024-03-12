<?php
    var_dump($_POST);
    require("db.php");
    try{
        $stmt = $conn->prepare("DELETE FROM di.users WHERE pass = ? and username = ?");
        //$newPassword = hash("sha256",$_POST['newPassword']);
        $stmt->execute([$_POST['passw'],$_POST['account']]);
        $result = $stmt->fetchAll();

        if($stmt->rowCount() == 1){
            $_SESSION["delMSG"] = "El usuario se ha borrado correctamente.";
        }else{
            $_SESSION["delMSG"] = "ha habido un fallo a la hora de borrar el usuario.";
        }
        
        print_r($result);
        
        header("Location: borrarUser.php");
        die();

    } catch(PDOException $e) {
        echo "Error: " . $e->getMessage();
    }
    $conn = null;

?>

