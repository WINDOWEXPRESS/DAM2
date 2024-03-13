
function preguntarActualizar(id) {
    actualizar = confirm("¿Deseas actualizar este registro?");
    if (actualizar)
        //Redireccionamos si das a aceptar
        window.location.href = "./editarLibro.php?id=" + id

    else

        alert('No se ha podido actualizar el registro..')
}


function botonEnviar(event) {

    isbn = document.getElementById("isbnEditar").value;

    if (isNaN(isbn)) {
        alert("ISBN no es numérico.");
        event.preventDefault();
    }

}
