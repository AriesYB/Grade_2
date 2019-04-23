window.onload = function () {
    var tip = document.getElementById("hidden").value;
    if (tip == "error1") {
        document.getElementById("id").style.border = "1px solid red";
        document.getElementById("password").style.border = "1px solid #000";
        document.getElementById("tip_account").innerHTML = "账号不存在!";
        document.getElementById("tip_password").innerHTML = "";
    } else if (tip == "error2") {
        document.getElementById("id").style.border = "1px solid #000";
        document.getElementById("password").style.border = "1px solid red";
        document.getElementById("tip_account").innerHTML = "";
        document.getElementById("tip_password").innerHTML = "密码错误!";
    }
}
function myfocus(x) {
    document.getElementById(x).style.border = "1px solid rgb(32, 117, 228)";
    if (x == "id") {
        document.getElementById("tip_account").innerHTML = "";
    } else if (x == "password") {
        document.getElementById("tip_password").innerHTML = "";
    }
}
function myblur(x) {
    document.getElementById(x).style.border="1px solid #000";
}
function checkForm() {
    var a = document.form["id"].value;
    var b = document.form["password"].value;
    if (a == null || a == "") {
        document.getElementById("id").style.border = "1px solid red";
        document.getElementById("tip_account").innerHTML = "账号为空!";
        document.getElementById("tip_password").innerHTML = "";
        return false;
    } else if (b == null || b == "") {
        document.getElementById("password").style.border = "1px solid red";
        document.getElementById("tip_account").innerHTML = "";
        document.getElementById("tip_password").innerHTML = "密码为空!";
        return false;
    }
} 