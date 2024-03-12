var userName = document.getElementById("userName");
var passwd = document.getElementById("passwd");
var rePasswd = document.getElementById("rePasswd");
var register = document.getElementById("formRegiste");

register.addEventListener("submit",(e)=>{ ValidateRegiste(e)})

function ValidateRegiste(e){
    var option = true;

    if(userName.value == ""){
        option = false;
        IsInValid(userName);
    }else{
        IsValid(userName);
    }

    if(passwd.value == "" || passwd.value.length < 4){
        option = false;
        IsInValid(passwd);
    }else{
        IsValid(passwd);
    }

    if(rePasswd.value == "" || rePasswd.value != passwd.value){
        option = false;
        IsInValid(rePasswd);
    }else{
        IsValid(rePasswd);
    }
    if(!option){
        e.preventDefault();
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