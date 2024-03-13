var formLogin = document.getElementById("formLogin");
var correo = document.getElementById("correo");
var passwd = document.getElementById("passwd");

formLogin.addEventListener("submit",(e)=>{
    var option = true;
    if(!ValidateEmail(correo)){
        option = false;
        IsInValid(correo);
    }else{
        IsValid(correo);
    }

    if(passwd.value == "" || passwd.value.length < 6){
        option = false;
        IsInValid(passwd);
    }else{
        IsValid(passwd);
    }

    if(!option){
        e.preventDefault();
    }

});


function ValidateEmail(inputText){
    const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    if(emailRegex.test(inputText.value)){
        return true;
    }else{
        return false;
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