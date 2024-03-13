
window.onload = iniciar

function iniciar() {
    document.getElementById("botonConfirmar").addEventListener("click", confirmar, false)

}

var confirmarPrestacion = false;
function confirmar(event) {
    var idLibro = document.getElementById("idInputLibro").value
    if (confirm("Confirmar prestacion id " + idLibro)) {
        confirmarPrestacion = true;
        
    }else{
        event.preventDefault();
    }
}
