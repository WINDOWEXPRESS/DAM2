<?php require("db.php")?>
<!DOCTYPE html>
<html lang="es">
	<head>
		<title>Borrar usuario</title>
		<meta charset="utf-8"/>
		<meta name="Author"	content="Zhouyi Chen"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	</head>
    <body class="p-3 m-0 border-0 bd-example m-0 border-0">
        <form action="deleteUser.php" method="post">
            <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Email address</label>
                <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="account">
                <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
            </div>
            <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label">Password</label>
                <input type="password" class="form-control" id="exampleInputPassword1" name="passw">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
        <?php
            if(isset($_SESSION["delMSG"])){
                echo($_SESSION["delMSG"]);
            }
        ?>
	</body>
</html>

