<?php
    require("db.php");

    try{
        $stmt = $conn->prepare("INSERT into di.users( username , pass ) VALUES (:user , :pw)");
        $stmt -> bindParam(':user',$_POST['account']);
        $stmt -> bindParam(':pw',$_POST['passw']);

        $stmt->execute();
        $result = $stmt->fetchAll();

        print_r($result);
        
    } catch(PDOException $e) {
        echo "Error: " . $e->getMessage();
    }
    $conn = null;

?>

