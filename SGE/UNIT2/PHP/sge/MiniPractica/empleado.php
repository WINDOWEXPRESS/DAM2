<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Coche</title>
    <style>
        input{width : 100px}
        table,td,tr,th{border:1px solid red}
    </style>
    <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous"> -->
</head>

<body>
    <table class="table table-success table-striped">
        <thead>
            <tr>
                <th scope="POSICION">#</th>
                <th scope="EMPNO">EMPNO</th>
                <th scope="ENAME">ENAME</th>
                <th scope="JOB">JOB</th>
                <th scope="SAL">SAL</th>
                <th scope="EDITAR">EDITAR</th>
                <th scope="BORRAR">BORRAR</th>
            </tr>
        </thead>
        <tbody>

            <?php
            include("header.php");
            include("db.php");

            try {
                $stmt = $conn->prepare("SELECT * FROM emp");

                $stmt->execute();
                $result = $stmt->fetchAll();
                $posicion = 1;
                foreach ($result as $row => $emp) {

                    echo ("<tr>");
                    echo ("<th scope='row'>" . $posicion . "</th>");
                    echo ("<td>" .   $emp["EMPNO"] . "</td> ");
                    echo ("<td>" .   $emp["ENAME"] . "</td>");
                    echo ("<td>" .   $emp["JOB"] . "</td>");
                    echo ("<td>" .   $emp["SAL"] . "</td>");
                    echo ("<td> <a href='editarEmpleado.php?id=" . $emp["EMPNO"] . "'>Editar</a> </td>");
                    echo ("<td> <a href='borrar.php?id=" . $emp["EMPNO"] . "'>Borrar</a> </td>");
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
    </form>
    <form action="insert.php" method="post">
        <table class="table table-success table-striped">
            <thead>
                <tr>
                    <th scope="POSICION">#</th>
                    <th scope="EMPNO">EMPNO</th>
                    <th scope="ENAME">ENAME</th>
                    <th scope="JOB">JOB</th>
                    <th scope="SAL">SAL</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope='row'>1</th>
                    <td><input type="text" name="idEmp"></td>
                    <td><input type="text" name="nombreEmp"></td>
                    <td><input type="text" name="trabajoEmp"></td>
                    <td><input type="text" name="salarioEmp"></td>
                    <td><input type="submit" value="Aniadir nuevo" name="aniadir" width="200px"></td>
                </tr>
            </tbody>
        </table>
    </form>
</body>

</html>