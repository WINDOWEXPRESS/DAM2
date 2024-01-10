<?php
    var_dump($_POST);
    print("Hola");
    require("db.php");

    try{
        $stmt = $conn->prepare("SELECT username , pass FROM di.users where username = :user and pass = :pw");
        $stmt -> bindParam(':user',$_POST['account']);
        $stmt -> bindParam(':pw',$_POST['passw']);

        $stmt->execute();
        $result = $stmt->fetchAll();

        print_r($result);

        if(count($result) == 1){
            header("Location: caja_fuerte.php");
        }else{
            header("Location: loginConComprobarLogin.php");
        }

    } catch(PDOException $e) {
        echo "Error: " . $e->getMessage();
    }
    $conn = null;
?>
