<?php
require_once("../../src/config/init.php");

try {
    $stmt = $conn->prepare("UPDATE libros SET titulo=?,autor=?,isbn=?,prestado=? WHERE ID = ?");
    $stmt->bindParam(1, $_GET["titulo"]);
    $stmt->bindParam(2, $_GET["autor"]);
    $stmt->bindParam(3, $_GET["isbn"]);
    $stmt->bindParam(4, $_GET["estado"]);
    $stmt->bindParam(5, $_GET["id"]);
    $stmt->execute();
} catch (PDOException $e) {
    echo "Error: " . $e->getMessage();
}
$conn = null;


header("Location: ./homeAdmin.php");

die();
