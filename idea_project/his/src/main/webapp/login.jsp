<%--
  Created by IntelliJ IDEA.
  User: HetFrame
  Date: 2019/8/15
  Time: 17:11
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="Bookmark" href="static/images/his.ico">
    <link rel="Shortcut Icon" href="static/images/his.ico"/>
    <title>东软云医院HIS系统</title>
    <link href="https://cdn.bootcss.com/normalize/8.0.0/normalize.min.css" rel="stylesheet">
    <!-- 它在默认的HTML元素样式上提供了跨浏览器的高度一致性 -->
    <link rel="stylesheet" href="static/login.css">
</head>

<body background="static/images/background.jpg">
<div class="main">
    <form action="check" method="post" name="form" onsubmit="return checkForm()">
        <div class="table">
            <div class="account">
                <img id='account-pic' src="static/images/account.png" alt="">
                <input class="account-content" type="text" name="username" id="username" size="15" placeholder=" 用户名"
                       onfocus="myfocus(this.getAttribute('id'))"
                       onblur="myblur(this.getAttribute('id'))">
            </div>
            <label id="tip_account" class="tip"></label>
            <div class="password">
                <img id='password-pic' src="static/images/password.png" alt="">
                <input class="password-content" type="password" name="password" id="password" size="15"
                       placeholder=" 请输入密码"
                       onfocus="myfocus(this.getAttribute('id'))" onblur="myblur(this.getAttribute('id'))">
                <label id="tip_password" class="tip"></label>
            </div>
        </div>
        <div>
            <input type="submit" value="登 录" id="button">
        </div>
    </form>
    <input id="hidden" type="hidden" value="${msg }">
</div>
<script type="text/javascript">
    window.onload = function () {
        var tip = document.getElementById("hidden").value;
        if (tip == "用户名不存在") {
            document.getElementById("username").style.border = "1px solid red";
            document.getElementById("password").style.border = "1px solid #000";
            document.getElementById("tip_account").innerHTML = "用户名不存在!";
            document.getElementById("tip_password").innerHTML = "";
        } else if (tip == "密码错误") {
            document.getElementById("username").style.border = "1px solid #000";
            document.getElementById("password").style.border = "1px solid red";
            document.getElementById("tip_account").innerHTML = "";
            document.getElementById("tip_password").innerHTML = "密码错误!";
        }
    }

    function myfocus(x) {
        document.getElementById(x).style.border = "1px solid rgb(32, 117, 228)";
        if (x == "username") {
            document.getElementById("tip_account").innerHTML = "";
        } else if (x == "password") {
            document.getElementById("tip_password").innerHTML = "";
        }
    }

    function myblur(x) {
        document.getElementById(x).style.border = "1px solid #000";
    }

    function checkForm() {
        var a = document.form["username"].value;
        var b = document.form["password"].value;
        if (a == null || a == "") {
            document.getElementById("username").style.border = "1px solid red";
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
</script>
</body>

</html>
