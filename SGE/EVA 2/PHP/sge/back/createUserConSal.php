<?php
    require("db.php");

    try{
        $stmt = $conn->prepare("INSERT into di.users( username , pass ) VALUES (? , ?)");
        //$stmt -> bindParam(':user',$_POST['account']);
        //$stmt -> bindParam(':pw',hash("sha256",$_POST['passw']));
        //$stmt->execute();
        $pwSalt = hash("sha256",$_POST['passw']);
        $stmt->execute([$_POST['account'],$pwSalt]);
        
        $result = $stmt->fetchAll();

        print_r($result);
        
    } catch(PDOException $e) {
        echo "Error: " . $e->getMessage();
    }
    $conn = null;

?>

