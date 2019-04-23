<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>登录</title>
	<link href="https://cdn.bootcss.com/normalize/8.0.0/normalize.min.css" rel="stylesheet">
	<!-- 它在默认的HTML元素样式上提供了跨浏览器的高度一致性 -->
	<link rel="stylesheet" href="css/login.css">
</head>

<body background="img/background.jpg">
	<div class="main">
		<form action="/goods/login" method="post" name="form" onsubmit="return checkForm()">
			<div class="table">
				<div class="account">
					<img id='account-pic' src="img/account.png" alt="">
					<input class="account-content" type="text" name="id" id="id" size="15" placeholder=" 用户名" onfocus="myfocus(this.getAttribute('id'))"
					 onblur="myblur(this.getAttribute('id'))">
				</div>
				<label id="tip_account" class="tip"></label>
				<div class="password">
					<img id='password-pic' src="img/password.png" alt="">
					<input class="password-content" type="password" name="password" id="password" size="15" placeholder=" 请输入密码"
					 onfocus="myfocus(this.getAttribute('id'))" onblur="myblur(this.getAttribute('id'))">
					<label id="tip_password" class="tip"></label>
				</div>
			</div>
			<div>
				<input type="submit" value="登 录" id="button">
			</div>
		</form>
		<input id="hidden" type="hidden" value="${param.tip }">
	</div>
	<script type="text/javascript" src="js/login.js"></script>
</body>

</html>