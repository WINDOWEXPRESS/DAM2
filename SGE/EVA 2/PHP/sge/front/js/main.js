
window.onload = iniciar

function iniciar(){
    document.getElementById("newPasswordRepeat").addEventListener("keyup",comprobar,false)
    document.getElementById("botonEnviar").addEventListener("click",botonEnviar,false)
}

function comprobar(){
    nuevoContrasenia = document.getElementById("newPassword").value;
    nuevoContraseniaRepetir =  document.getElementById("newPasswordRepeat").value;

    if(nuevoContrasenia != nuevoContraseniaRepetir ){
        document.getElementById("mensajeError").innerHTML = "La contraseña introducido no coincide.";
    }else{
        document.getElementById("mensajeError").innerHTML = null;
    }
}

function botonEnviar(event) {

    email = document.getElementById("email").value;
    contrasenia = document.getElementById("oldPassword").value;
    nuevoContrasenia = document.getElementById("newPassword").value;
    nuevoContraseniaRepetir =  document.getElementById("newPasswordRepeat").value;

    if(nuevoContrasenia == null||nuevoContrasenia != nuevoContraseniaRepetir || email==null || contrasenia == null){
        alert("Hay error en los campos.");
        event.preventDefault();
    }


}
