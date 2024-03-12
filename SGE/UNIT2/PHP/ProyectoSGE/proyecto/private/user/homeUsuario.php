<?php
require_once("../../src/config/init.php");
require_once("./idioma.php");

if (isset($_SESSION['admin'])) {
	header("location: ../admin/homeAdmin.php?Message=User_Area");
	exit();
}
$idiomaSelect = "";
$poblacion = "";
if (!isset($_SESSION['user'])){
	header("location: ../../public/Login.php?Message=User_Is_Not_Login");
	exit();
} else {
	$baseDato->ejecuta("SELECT * FROM usuarios WHERE ID = ?", $_SESSION['user']);
	$user = $baseDato->obtenDatos();
	unset($baseDato);
	setcookie("idioma",$user[0]['idioma']);
	$idiomaSelect = $idioma[$user[0]['idioma']];
	
	$poblacion = "<h6 id='tiempoPoblacion'>" . $user[0]['poblacion'] . "</h6> <input type='hidden' id='poblacionId' name='poblacionHidden' value='" . $user[0]['poblacion_id'] . "'/>";
}

if(isset($_COOKIE['idioma'])){
	$idiomaSelect = $idioma[$_COOKIE['idioma']];
}

if(isset($_SESSION['mensajeErrorPrestacion'])){
	$_SESSION['mensajeErrorPrestacion'] = $idiomaSelect['ErrorPrestacion'];
}

?>

<!doctype html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>LibrayControl</title>
	<script src="https://code.jquery.com/jquery-3.6.3.min.js" defer></script>
	<script src="../../javascript/confirmarPrestacion.js" defer></script>
	<script src="../../javascript/tiempoActual.js" defer></script>
	<link rel="stylesheet" href="../../css/home.css" type="text/css">
</head>

<body class="p-3 m-0 border-0 bd-example m-0 border-0">
	<div class="header">
		<div class="Bienvenido">
			<img class="Favicon32x32" src="../../src/img/logo.svg" alt="Favicon32x32">
			<p><?php echo$idiomaSelect['Bienvenido']?></p>
		</div>
		<div class="Nav-Menu">
			<div class="Dest">
				<?php echo ($poblacion) ?>
			</div>
			<nav class="Menu">
				<ul>
					<li>
						<a><?php echo$idiomaSelect['Idioma']?></a>
						<ul>
							<li><a href=<?php echo "./cambiarIdioma.php?user=".$_SESSION['user']."&idioma=es"?>><?php echo$idiomaSelect['ES']?></a></li>
							<li><a href=<?php echo "./cambiarIdioma.php?user=".$_SESSION['user']."&idioma=en"?>><?php echo$idiomaSelect['EN']?></a></li>
						</ul>
					</li>
					<li>
						<a href="./datosUsuario.php"><?php echo$idiomaSelect['Mis datos']?></a>
					</li>
					<li>
						<a href="../salir.php"><?php echo$idiomaSelect['Salir']?></a>
					</li>
				</ul>
			</nav>
		</div>
	</div>

	<div class="cuerpo">
		<div class="search bar1">
			<h1 style="color: white;"><?php echo$idiomaSelect['Introducir el id para prestacion']?></h1>
			<form action="./prestacionLibro.php" method="post" id="formularioEnvioPrestacion">
				<input type="text" placeholder= "<?php echo$idiomaSelect['Introducir el id de libro']?>" id="idInputLibro" name="idLibro">
				<button type="submit" id="botonConfirmar"><img src="../../src/img/check-circle.svg" width="25" height="25" alt="Icono"></button>
			</form>
			<?php
			if (isset($_SESSION["mensajeErrorPrestacion"])) {
				echo "<h1 style=\"color: white;\">" . $_SESSION["mensajeErrorPrestacion"] . "</h1>";
			}
			?>

			<div class="tablaPrestacion">
				<table width="90%" class="table">
					<thead>
						<tr>
							<th><?php echo$idiomaSelect['ID']?></th>
							<th><?php echo$idiomaSelect['Titulo']?></th>
							<th><?php echo$idiomaSelect['Autor']?></th>
							<th><?php echo$idiomaSelect['Isbn']?></th>
							<th><?php echo$idiomaSelect['Estado']?></th>
						</tr>
					</thead>
					<tbody>
						<?php

						try {
							$stmt = $conn->prepare("SELECT * FROM libros");

							$stmt->execute();
							$result = $stmt->fetchAll();
							$posicion = 1;
							foreach ($result as $row => $libro) {

								echo ("<tr>");
								echo ("<td>" .   $libro["ID"] . "</td> ");
								echo ("<td>" .   $libro["titulo"] . "</td>");
								echo ("<td>" .   $libro["autor"] . "</td>");
								echo ("<td>" .   $libro["isbn"] . "</td>");
								switch ($libro["prestado"]) {
									case 1:
										echo ("<td>".$idiomaSelect['Prestado']."</td>");
										break;
									case 0:
										echo ("<td>".$idiomaSelect['No prestado']."</td>");
										break;
									case is_null($libro["prestado"]):
										echo ("<td> Null </td>");
										break;
								}
								echo ("</tr>");
								$posicion += 1;
							}
						} catch (PDOException $e) {
							echo "Error: " . $e->getMessage();
						}
						$conn = null;

						?>
					</tbody>
				</table>
			</div>
			<div class="tablaDevolver">
				<br><br><br>
				<h1 style="color: white;"><?php echo$idiomaSelect['Libros prestado']?></h1>
				<table width="90%" class="table">
					<thead>
						<tr>
							<th><?php echo$idiomaSelect['Titulo']?></th>
							<th><?php echo$idiomaSelect['Fecha Inicio']?></th>
							<th><?php echo$idiomaSelect['Devolucion']?></th>
						</tr>
					</thead>
					<tbody>
						<?php
						try {
							$stmt = $conn1->prepare("SELECT idLibro, titulo,fechaInicio FROM libros , prestamos 
								where libros.ID = prestamos.idLibro and idUsuario = ? and fechaFinal is null ");
								
							$stmt->bindParam(1, $_SESSION["user"]);
							$stmt->execute();

							$result = $stmt->fetchAll();
							$posicion = 1;
							foreach ($result as $row => $libro) {

								echo ("<tr>");
								echo ("<td>" .   $libro["titulo"] . "</td>");
								echo ("<td>" .   $libro["fechaInicio"] . "</td>");
								echo ("<td> <a href='./devolverLibro.php?idLibro=" . $libro["idLibro"]  . "'>".$idiomaSelect['Devolver']."</a> </td>");
								echo ("</tr>");
								$posicion += 1;
							}
						} catch (PDOException $e) {
							echo "Error: " . $e->getMessage();
						}
						$conn = null;

						?>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>

</html>