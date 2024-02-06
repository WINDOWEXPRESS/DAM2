<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Sitio Web</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <style>
        * {
            box-sizing: border-box;
        }

        div.search {
            padding: 30px 0;
        }

        form {
            position: relative;
            width: 300px;
            margin: 0 auto;
        }

        input,
        button {
            border: none;
            outline: none;
        }

        input {
            width: 100%;
            height: 42px;
            padding-left: 13px;
        }

        button {
            height: 42px;
            width: 42px;
            cursor: pointer;
            position: absolute;
        }

        /*搜索框1*/
        .bar1 {
            background: #A3D0C3;
        }

        .bar1 input {
            border: 2px solid #7BA7AB;
            border-radius: 5px;
            background: #F9F0DA;
            color: #9E9C9C;
        }

        .bar1 button {
            top: 0;
            right: 0;
            background: #7BA7AB;
            border-radius: 0 5px 5px 0;
        }

        .bar1 button:before {
            content: "f002";
            font-family: FontAwesome;
            font-size: 16px;
            color: #F9F0DA;
        }
        table {
	border-collapse: collapse;
	margin: 0 auto;
	text-align: center;
}

/*ESTILOS PARA TABLA*/
table td,
table th {
	border: 1px solid #cad9ea;
	color: #666;
	height: 30px;
}

table thead th {
	background-color: #CCE8EB;
	width: 100px;
}

table tr:nth-child(odd) {
	background: #fff;
}

table tr:nth-child(even) {
	background: #F5FAFA;
}
    </style>
</head>

<body>

    <div class="navbar">
        <p>hola</p>
        <a href="#inicio">Inicio</a>
        <a href="#acerca-de">Acerca de</a>
        <a href="#servicios">Servicios</a>
        <a href="#contacto">Contacto</a>
    </div>
    <div class="mi-div">
        <!-- Contenido del div -->
        <h1>Consulta de libros</h1>
        <h1>Consulta de libros</h1>

        <h1>Consulta de libros</h1>
        <h1>Consulta de libros</h1>
        <h1>Consulta de libros</h1>
    </div>
    <!-- El resto del contenido de tu página -->
    <div id="container">
        <div class="search bar1">
            <h1>Consulta de libros</h1>
            <form>
                <input type="text" placeholder="请输入您要搜索的内容...">
                <button type="submit"></button>
            </form>
            <div class="cuerpo">
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
            </div>
        </div>

    </div>

</html>