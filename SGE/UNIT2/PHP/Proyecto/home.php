<!doctype html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Bootstrap Example</title>
	<link rel="stylesheet" href="./headers.css" type="text/css">
	<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"> -->
</head>

<body class="p-3 m-0 border-0 bd-example m-0 border-0">
	<div class="header">
		<div class="Bienvenido">
			<img class="Favicon32x32" src="#" alt="Favicon32x32">
			<p> Bienvenido</p>
		</div>
		<div class="Nav-Menu">
			<div class="Dest">
				<p>Tiempo</p>
			</div>
			<nav class="Menu">
				<ul>
					<li>
						<a href="#">Idioma</a>
						<ul>
							<li><a href="#">ES</a></li>
							<li><a href="#">IN</a></li>
						</ul>
					</li>
					<li>
						<a href="#">Mis datos</a>
					</li>
					<li>
						<a href="#">Logout</a>
					</li>
				</ul>
			</nav>
		</div>
	</div>

	<div class="cuerpo">
		<div class="search bar1">
				<h1>Consulta de libros</h1>
				<form >
					<input type="text" placeholder="Introducir el titulo de libro...">
					<button type="submit" ><img src="./fi-rr-search.svg" width="25" height="25" alt="Icono"></button>
				</form>

					<div class="titulo">
						<table width="90%" class="table">
							<caption>

							</caption>
							<thead>
								<tr>
									<th>ID</th>
									<th>Titulo</th>
									<th>Autor</th>
									<th>Isbn</th>
									<th>Estado</th>
								</tr>
							</thead>
							<tr>
								<td>1</td>
								<td>COLMENA</td>
								<td>LOPEZ DE VEGA</td>
								<td>22132133</td>
								<td>Prestado</td>
							</tr>
						</table>
					</div>
				<form >
					<input type="text" placeholder="Introducir el titulo de libro...">
					<button type="submit" ><img src="./fi-rr-search.svg" width="25" height="25" alt="Icono"></button>
				</form>
			</div>
	</div>
</body>

</html>