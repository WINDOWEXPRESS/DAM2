
<?php
require_once("../../src/config/init.php");

try {
    $newPasswd = password_hash($_GET['contrasenia'],PASSWORD_DEFAULT);
    $stmt = $conn->prepare("UPDATE usuarios SET contrasenia = ? WHERE id = ?");
    $stmt->bindParam(1, $newPasswd); 
    $stmt->bindParam(2, $_GET["id"]);
    $stmt->execute();
} catch (PDOException $e) {
    echo "Error: " . $e->getMessage();
}
$conn = null;


header("Location: ./usuarioAdmin.php");

die();
