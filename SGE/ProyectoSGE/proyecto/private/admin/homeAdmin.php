<?php
require_once("../../src/config/init.php");
if (isset($_SESSION['user'])) {
	header("location: ../user/homeUsuario.php?Message=Area_Admin");
	exit();
}
if (!isset($_SESSION['admin'])) {
	header("location: ./LoginAdmin.php?Message=Admin_Is_Not_Login");
	exit();
}
?>

<!doctype html>
<html lang="es">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>LibrayControl</title>
	<link rel="stylesheet" href="../.././css/home.css" type="text/css">
	<script src="../../javascript/confirmarDeleteLibro.js" defer></script>
	<script src="../../javascript/confirmarActualizar.js" defer></script>
	<script src="../../javascript/confirmarAniadir.js" defer></script>
	<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"> -->
</head>

<body class="p-3 m-0 border-0 bd-example m-0 border-0">
	<div class="header">
		<div class="Bienvenido">
			<img class="Favicon32x32" src="../../src/img/logo.svg" alt="Favicon32x32">
			<p> Bienvenido</p>
		</div>
		<div class="Nav-Menu">
			<nav class="Menu">
				<ul>
					<li>
						<a href="#">Opciones</a>
						<ul>
							<li><a href="./usuarioAdmin.php">Usuarios</a></li>
							<li><a href="./homeAdmin.php">Libros</a></li>
							<li><a href="./prestacionHistorial.php">Prestacion</a></li>

						</ul>
					</li>
					<li>
						<a href="../salir.php">Salir</a>
					</li>
				</ul>
			</nav>
		</div>
	</div>

	<div class="cuerpo">
		<div class="search bar1">
			<div class="titulo">
				<h1 style="color: white;">Administar libros</h1>
				<table width="90%" class="table">
					<thead>
						<tr>
							<th>ID</th>
							<th>Titulo</th>
							<th>Autor</th>
							<th>Isbn</th>
							<th>Estado</th>
							<th>Editar</th>
							<th>Borrar</th>
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
										echo ("<td> Prestado </td>");
										break;
									case 0:
										echo ("<td> No prestado </td>");
										break;
									case is_null($libro["prestado"]):
										echo ("<td> Null </td>");
										break;
								}

								echo ("<td> <a onclick = 'preguntarActualizar(" . $libro["ID"] . ")'>Editar</a> </td>");
								switch ($libro["prestado"]) {
									case 1:
										echo ("<td> Borrar </td>");
										break;
									case 0:
										echo ("<td> <a  onclick = 'preguntar(" . $libro["ID"] . ")'>Borrar</a> </td>");
										break;
									case is_null($libro["prestado"]):
										echo ("<td> <a  onclick = 'preguntar(" . $libro["ID"] . ")'>Borrar</a> </td>");
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

						<form action="aniadirLibro.php" method="get">
							<tr>
								<td></td>
								<td><input type="text" id="aniadirTituloLibro" name="titulo"></td>
								<td><input type="text" id="aniadirAutorLibro" name="autor"></td>
								<td><input type="text" id="aniadirIsbnLibro" name="isbn"></td>
								<td></td>
								<td colspan="2"><input type="submit" value="Añadir nuevo libro" id="aniadir" width="200px"></td>

							</tr>
						</form>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>

</html>