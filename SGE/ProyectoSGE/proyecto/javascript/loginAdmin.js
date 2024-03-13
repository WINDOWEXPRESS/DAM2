var userName = document.getElementById("userName");
var passwd = document.getElementById("passwd");
var formLogin = document.getElementById("formLogin");

formLogin.addEventListener("submit",(e)=>{
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

    if(!option){
        e.preventDefault();
    }
});

function IsValid(element){
    element.classList.remove("is-invalid");
    element.classList.add("is-valid");
}

function IsInValid(element){
    element.classList.remove("is-valid");
    element.classList.add("is-invalid");
}