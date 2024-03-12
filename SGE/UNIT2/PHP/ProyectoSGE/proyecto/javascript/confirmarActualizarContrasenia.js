window.onload = busqueda;

function preguntarActualizar(event) {
    actualizar = confirm("¿Deseas cambiar contraseña de este registro?");
    if (!actualizar){
      alert('No se ha podido cambiar contraseña del registro..')
      event.preventDefault();
    }
}

var busqueda = document.getElementById('buscar');
var table = document.getElementById("tabla").tBodies[0];

buscaTabla = function(){
  texto = busqueda.value.toLowerCase();
  var r=0;
  while(row = table.rows[r++])
  {
    if ( row.innerText.toLowerCase().indexOf(texto) !== -1 )
      row.style.display = null;
    else
      row.style.display = 'none';
  }
}

busqueda.addEventListener('keyup', buscaTabla);