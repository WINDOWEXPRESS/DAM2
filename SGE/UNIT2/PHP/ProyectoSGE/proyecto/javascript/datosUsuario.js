var formUserData = document.getElementById("formUserData");
var correo = document.getElementById("correo");
var provincias = document.getElementById("provincias");
var poblacion = document.getElementById("poblacion");
var hiddenProvincia = document.getElementById("hiddenProvincia");
var hiddenPoblacion = document.getElementById("hiddenPoblacion");
var btAnular = document.getElementById("btAnular");
var userName = document.getElementById("userName");
var oldPasswd = document.getElementById("oldPasswd");
var newPasswd = document.getElementById("newPasswd");
var btVolver = document.getElementById("btVolver");

correo.disabled = true;

fetch("../../src/json/provincia.json")
.then(response => response.json())
.then(data =>{
    createProvinciaYPoblacion(data,provincias,hiddenProvincia,hiddenPoblacion);
});

btAnular.addEventListener("click",(e)=>{
    location.reload();
})

provincias.addEventListener("change",(e)=>{
    createPoblacion(e.target.value,poblacion,null);
});

formUserData.addEventListener("submit",(e)=>{
    var option = true;
    if(userName.value == ""){
        option = false;
        IsInValid(userName);
    }else{
        IsValid(userName);
    }

    if(oldPasswd.value != "" || newPasswd.value != ""){
        if(oldPasswd.value.length < 6){
            option = false;
            IsInValid(oldPasswd);
        }else{
            IsValid(oldPasswd);
        }

        if(newPasswd.value.length < 6){
            option = false;
            IsInValid(newPasswd);
        }else{
            IsValid(newPasswd);
        }
    }else{
        IsValid(oldPasswd);
        IsValid(newPasswd);
    }
    if(!option){
        e.preventDefault();
    }else{
        var provinciaSelected = provincias.options[provincias.selectedIndex].text;
        var provinciaHiddenInput = document.createElement("input");
        CreateHiddenInput(provinciaHiddenInput,"provinciaSelected",provinciaSelected);

        var poblacionSelected = poblacion.options[poblacion.selectedIndex].text;
        var poblacionHiddenInput = document.createElement("input");
        CreateHiddenInput(poblacionHiddenInput,"poblacionSelected",poblacionSelected);

        formUserData.appendChild(provinciaHiddenInput);
        formUserData.appendChild(poblacionHiddenInput);
    }
});

btVolver.addEventListener("click",()=>{
    document.cookie = "tiempoDia" +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
});

function createProvinciaYPoblacion(data,provincias,selectedProvincia,selectedPoblacion){
    data.forEach(value => {
        var op = document.createElement("option");
        op.setAttribute("value",value.code);
        var texto = document.createTextNode(value.label);
        if(texto.nodeValue == selectedProvincia.value){
            op.selected = true;
        }
        op.appendChild(texto);
        provincias.appendChild(op);
    });
    var code = provincias.options[provincias.selectedIndex].value;
    createPoblacion(code,poblacion,selectedPoblacion.value)
}

function createPoblacion(code,poblacion,selected){
    while(poblacion.firstChild){
        poblacion.removeChild(poblacion.firstChild);
    }
    fetch("../../src/json/poblacion.json")
    .then(response => response.json())
    .then(data =>{
        data.forEach(value =>{
            if(value.parent_code == code){
                var op = document.createElement("option");
                op.setAttribute("value",value.code);
                var texto = document.createTextNode(value.label);
                if(texto.nodeValue == selected){
                    op.selected = true;
                }
                op.appendChild(texto);
                poblacion.appendChild(op);
            }            
        })
    })
    .catch(function (error){
        alert(error.message);
    })
}

function IsValid(element){
    element.classList.remove("is-invalid");
    element.classList.add("is-valid");
}

function IsInValid(element){
    element.classList.remove("is-valid");
    element.classList.add("is-invalid");
}

function CreateHiddenInput(element,name,selected){
    element.setAttribute("type", "hidden");
    element.setAttribute("name", name);
    element.setAttribute("value",selected);

}