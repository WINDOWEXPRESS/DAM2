var cokies = getCookie("tiempoDia");
if(cokies != ""){
    tiempoPoblacion.innerHTML += " "+ cokies +"°";
}else{
    $(document).ready(function() {
        var apiKey = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4aW5nLmNoZW4yQGVkdWNhLm1hZHJpZC5vcmciLCJqdGkiOiIxZTRhNzBjYS1iZGVmLTQwMjgtYWM2Ni1lNjFiOTlkM2RhMmYiLCJpc3MiOiJBRU1FVCIsImlhdCI6MTcwOTI0NDczNiwidXNlcklkIjoiMWU0YTcwY2EtYmRlZi00MDI4LWFjNjYtZTYxYjk5ZDNkYTJmIiwicm9sZSI6IiJ9.6luua60XqZubCN1xBCJBy6w-8LLFLiiBbrq7SJfBP8I';
        var poblacionId = document.getElementById("poblacionId").value;
        
        var settings = {
            "async": true,
            "crossDomain": true,
            "url": "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria/" + poblacionId + "?api_key=" + apiKey,
            "method": "GET",
            "headers": {
            "cache-control": "no-cache"
            }
        };
        
        $.ajax(settings).done(function (response) {
            var dato = response.datos;
            fetch(dato)
            .then(response =>{
                if(!response.ok){
                    alert("Error No Puede conectarse a API Para tener tiempo!");
                }
                return response.json();
            })
            .then(data => {
                var dia = new Date().getDate();
                var tiempoDia;
                for (var value of data[0].prediccion.dia) {
                    var fecha = new Date(value.fecha);
                    if (fecha.getDate() == dia) {
                        tiempoDia = value.temperatura.maxima;
                        break;
                    }
                }
                var tiempoPoblacion = document.getElementById("tiempoPoblacion");
                setCookie("tiempoDia",tiempoDia,1);
                tiempoPoblacion.innerHTML += " "+ tiempoDia +"°";
            })
        });
    });
        
}


function setCookie(cname, cvalue, exdays) {
    const d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    let expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}
  
function getCookie(cname) {
let name = cname + "=";
let ca = document.cookie.split(';');
for(let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
    c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
    return c.substring(name.length, c.length);
    }
}
return "";
}