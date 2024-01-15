<?php
    var_dump($_POST);
    require("db.php");
    try{
        $stmt = $conn->prepare("UPDATE di.users set pass = ? where username = ? and pass = ?");
        //$newPassword = hash("sha256",$_POST['newPassword']);
        $stmt->execute([$_POST['newPassword'],$_POST['account'],$_POST['passw']]);
        $result = $stmt->fetchAll();

        if($stmt->rowCount() == 1){
            header("Location: login.php");
        }else{
            header("Location: cambiarContrasenia.php");
        }
        
        print_r($result);
        
    } catch(PDOException $e) {
        echo "Error: " . $e->getMessage();
    }
    $conn = null;

?>

