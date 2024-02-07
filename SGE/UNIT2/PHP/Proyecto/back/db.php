<?php
//EJEMPLO DE CONEXION CON BASE DE DATO mysql
$servername = "localhost";
$username = "root";
$password = "";

session_start();


try {
  $conn = new PDO("mysql:host=$servername;dbname=proyecto_sge", $username, $password);
  // set the PDO error mode to exception
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  echo("<script>console.log('PHP: Connected successfully');</script>");
} catch(PDOException $e) {
  echo "Connection failed: " . $e->getMessage();
}


?>


