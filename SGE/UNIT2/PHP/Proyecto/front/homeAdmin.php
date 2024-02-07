<!doctype html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Home de admin</title>
	<link rel="stylesheet" href=".././css/home.css" type="text/css">
	<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"> -->
</head>

<body class="p-3 m-0 border-0 bd-example m-0 border-0">
	<div class="header">
		<div class="Bienvenido">
			<img class="Favicon32x32" src=".././img/logo.svg" alt="Favicon32x32">
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
                        <a href="#">Opciones</a>
                            <ul>
                                <li><a href="#">Usuarios</a></li>
                                <li><a href="#">Libros</a></li>
                            </ul>
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
					<div class="titulo">
                        <h1 style="color: white;">Administar libros</h1>
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
                                    <th>Editar</th>
                                    <th>Borrar</th>
								</tr>
							</thead>
	
                            <tbody>
                                <?php
                                include(".././back/db.php");

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
                                        
                                        echo ("<td> <a href='editarLibro.php?id=" . $libro["ID"] . "'>Editar</a> </td>");
                                        echo ("<td> <a href='borrarLibro.php?id=" . $libro["ID"] . "'>Borrar</a> </td>");
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