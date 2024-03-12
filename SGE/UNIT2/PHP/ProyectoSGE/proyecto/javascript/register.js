var provincias = document.getElementById("provincias");
var poblacion = document.getElementById("poblacion");
poblacion.disabled = true;

fetch("../src/json/provincia.json")
.then(response => response.json())
.then(data => createProvincia(data,provincias))
.catch(function (error){
    alert(error.message);
})

provincias.addEventListener("change",(e)=>{createPoblacion(e,poblacion)})
provincias.addEventListener("click",(e)=>{
    var nullOption = document.getElementById("nullOption");
    nullOption.hidden = true;
})

var userName = document.getElementById("userName");
var correo = document.getElementById("correo");
var passwd = document.getElementById("passwd");
var rePasswd = document.getElementById("rePasswd");
var register = document.getElementById("formRegiste");
var espanio = document.getElementById("es");
var ingles = document.getElementById("en");
var pError = document.getElementById("pError");

register.addEventListener("submit",(e)=>{ ValidateRegiste(e)})

function createProvincia(data,provincias){
    data.forEach(value => {
        var op = document.createElement("option");
        op.setAttribute("value",value.code);
        var texto = document.createTextNode(value.label);
        op.appendChild(texto);
        provincias.appendChild(op);
    });
}

function createPoblacion(e,poblacion){
    var code = e.target.value;
    poblacion.disabled = false;
    while(poblacion.hasChildNodes()){
        poblacion.removeChild(poblacion.firstChild);
    }
    fetch("../src/json/poblacion.json")
    .then(response => response.json())
    .then(data =>{
        data.forEach(value =>{
            if(value.parent_code == code){
                var op = document.createElement("option");
                op.setAttribute("value",value.code);
                var texto = document.createTextNode(value.label);
                op.appendChild(texto);
                poblacion.appendChild(op);
            }            
        })
    })
    .catch(function (error){
        alert(error.message);
    })
}

function ValidateEmail(inputText){
    const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    if(emailRegex.test(inputText.value)){
        return true;
    }else{
        return false;
    }
}

function ValidateRegiste(e){
    var option = true;

    if(userName.value == ""){
        option = false;
        IsInValid(userName);
    }else{
        IsValid(userName);
    }

    if(passwd.value == "" || passwd.value.length < 6){
        option = false;
        IsInValid(passwd);
    }else{
        IsValid(passwd);
    }

    if(rePasswd.value == "" || rePasswd.value != passwd.value || rePasswd.length < 6){
        option = false;
        IsInValid(rePasswd);
    }else{
        IsValid(rePasswd);
    }

    if(provincias.options[provincias.selectedIndex].value == "0"){
        option = false;
        IsInValid(provincias);
    }else{
        IsValid(provincias);
    }

    if(poblacion.options.length == 0){
        option = false;
        IsInValid(poblacion);
    }else{
        IsValid(poblacion);
    }

    if(!ValidateEmail(correo)){
        option = false;
        IsInValid(correo);
    }else{
        IsValid(correo);
    }

    if(!espanio.checked && !ingles.checked){
        option = false;
        IsInValid(espanio);
        IsInValid(ingles);
    }else{
        IsValid(espanio);
        IsValid(ingles);
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

        register.appendChild(provinciaHiddenInput);
        register.appendChild(poblacionHiddenInput);
    }
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