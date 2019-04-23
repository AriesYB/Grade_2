<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>注销</title>
	<link href="https://cdn.bootcss.com/normalize/8.0.0/normalize.min.css" rel="stylesheet">
	<link rel="stylesheet" href="/goods/css/logout.css">
</head>

<body>
	<div class="main">
		<h2>${sessionScope.id },您注销成功！</h2>
		<label>即将返回至登录页面</label>
		<form name="form" action="${pageContext.request.contextPath}/logout" method="get">
		</form>
	</div>
	<script type="text/javascript" src="/goods/js/logout.js"></script>
</body>

</html>