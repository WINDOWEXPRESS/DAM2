<?php require("../back/db.php")?>
<!DOCTYPE html>
<html lang="es">
	<head>
		<title>Cambiar Contraseña</title>
		<meta charset="utf-8"/>
		<meta name="Author"	content="Zhouyi Chen"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
        <script src="./js/main.js" defer></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	</head>
    <body class="p-3 m-0 border-0 bd-example m-0 border-0">
        <form action="../back/UpdatePassword.php" method="post">
            <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Email address</label>
                <input type="email" class="form-control" id="email" aria-describedby="emailHelp" name="account" value="<?php echo $_SESSION["acount"]?>">
                <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
            </div>
            <div class="mb-3" id="divContresenia">
                <label for="exampleInputPassword1" class="form-label">Password</label>
                <input type="password" class="form-control" id="oldPassword" name="passw">
                <label for="exampleInputPassword1" class="form-label">New Password</label>
                <input type="password" class="form-control" id="newPassword" name="newPassword">
                <label for="exampleInputPassword1" class="form-label">Repeat New Password</label>
                <input type="password" class="form-control" id="newPasswordRepeat" name="newPasswordRepeat">
                <p id = "mensajeError"></p>
            </div>
            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="exampleCheck1">
                <label class="form-check-label" for="exampleCheck1">Check me out</label>
            </div>
            <button type="submit" class="btn btn-primary" id="botonEnviar" >Submit</button>
        </form>
	</body>
</html>

