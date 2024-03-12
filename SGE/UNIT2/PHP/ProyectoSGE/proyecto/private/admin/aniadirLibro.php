<?php
require_once("../../src/config/init.php");
echo($_GET["titulo"].$_GET["autor"].$_GET["isbn"]);
try {
    $stmt = $conn->prepare("INSERT INTO libros (titulo,autor, isbn, prestado) VALUES (?,?,?,0)");
    $stmt->bindParam(1, $_GET["titulo"]);
    $stmt->bindParam(2, $_GET["autor"]);
    $stmt->bindParam(3, $_GET["isbn"]);
    $stmt->execute();

} catch (PDOException $e) {
    echo "Error: " . $e->getMessage();
}
$conn = null;

header("Location: ./homeAdmin.php");

die();
