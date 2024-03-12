
window.onload = iniciar
//Cuando carga la ventana inicia el metodo iniciar que tiene un listener
function iniciar() {
    document.getElementById("aniadir").addEventListener("click", botonEnviar, false)
}

function botonEnviar(event) {

    titulo = document.getElementById("aniadirTituloLibro").value;
    autor = document.getElementById("aniadirAutorLibro").value;
    isbn = document.getElementById("aniadirIsbnLibro").value;
    
    aniadir = confirm("¿Deseas añadir este registro?");
    if (aniadir) {
        //Redireccionamos si das a aceptar
        if (titulo == null || autor == null || isbn == null) {
            alert("Hay campo vacio.");
            event.preventDefault();
        }else if(isNaN(isbn)){
            alert("ISBN no es numérico.");
            event.preventDefault();
        }

    }else {
        alert('No se ha podido añadir el registro..');
        event.preventDefault();
    }
    



}
