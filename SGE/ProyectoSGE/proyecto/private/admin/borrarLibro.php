<?php
require_once("../../src/config/init.php");
$noPrestado = 0;

try {
    $stmt = $conn->prepare("DELETE FROM libros WHERE libros.ID=? and prestado = $noPrestado");
    $stmt->bindParam(1, $_GET["id"]);
    $stmt->execute();
} catch (PDOException $e) {
    echo "Error: " . $e->getMessage();
}
$conn = null;


header("Location: ./homeAdmin.php");

die();
