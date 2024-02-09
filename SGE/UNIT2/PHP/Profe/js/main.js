
window.onload = iniciar

function iniciar(){
document.getElementById("new2").addEventListener("keyup",comprobar,false)
document.getElementById("botonCambiarPass").addEventListener("click",botonEnviar,false)
}

function comprobar(){
    passnew = document.getElementById("new").value
    passold = document.getElementById("new2").value
    if(passnew != passold)
    {
        document.getElementById("error").innerHTML="las contraseñas no coinciden";
        return false
    }
    else
    {
        document.getElementById("error").innerHTML=""
        return true
    }
}

function botonEnviar(event)
{
    if(!comprobar())
        event.preventDefault();
}
