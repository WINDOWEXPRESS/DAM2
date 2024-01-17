<?php
    var_dump($_POST);
    print("Hola");
    require("db.php");

    try{
        session_start();
        $stmt = $conn->prepare("SELECT username , pass FROM di.users where username = :user and pass = :pw");
        $stmt -> bindParam(':user',$_POST['account']);
        $stmt -> bindParam(':pw',$_POST['passw']);

        $stmt->execute();
        $result = $stmt->fetchAll();

        print_r($result);

        if(count($result) == 1){
            $_SESSION["name"] = $_POST["account"];
            $_SESSION["login"] = true;
            header("Location: caja_fuerte.php");
            die();
        }else{
            header("Location: loginConComprobarLogin.php");
            die();
        }

    } catch(PDOException $e) {
        echo "Error: " . $e->getMessage();
    }
    $conn = null;
?>
