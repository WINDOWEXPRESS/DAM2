<?php
//EJEMPLO DE CONEXION CON BASE DE DATO mysql
$servername = "localhost";
$username = "root";
$password = "";
try {
  $conn = new PDO("mysql:host=$servername;dbname=proyecto_sge", $username, $password);
  $conn1 = new PDO("mysql:host=$servername;dbname=proyecto_sge", $username, $password);
  // set the PDO error mode to exception
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  $conn1->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  echo("<script>console.log('PHP: Connected successfully');</script>");
} catch(PDOException $e) {
  echo "Connection failed: " . $e->getMessage();
}


?>


