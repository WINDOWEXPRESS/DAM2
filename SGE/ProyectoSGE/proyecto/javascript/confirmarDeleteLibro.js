
function preguntar(id) {
    eliminar = confirm("¿Deseas eliminar este registro?");
    if (eliminar)
        //Redireccionamos si das a aceptar

        window.location.href = "./borrarLibro.php?id=" + id

    else

        alert('No se ha podido eliminar el registro..')
}
