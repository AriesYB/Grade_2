<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>登录</title>
	</head>
	<br><br><br><br><br><br><br><br>
	<body style="font-size: 20px;">
		<div align="center">
			<form action="/customer/login?action=login" method="post" name="form" onsubmit="return checkForm()">	<%--post方法传输更安全--%>	
				<div>
					<table>
					<tr>					
						<td>账号</td><td><input type="text" id="user" name="user" onchange="clear()" style="width:150px;height:18px;font-size:20px"></td>
					</tr>
					<tr>					
						<td>密码</td><td><input type="password" id="password" name="password" onchange="clear()" style="width:150px;height:18px;font-size:20px"></td>
					</tr>					
					</table>
				</div>
				<div style="position:relative;left:20px">
					<input type="submit" value="登录" style="width:60px;height:28px;font-size: 15px"/>&nbsp;&nbsp;&nbsp;
					<input type="button" value="注册" style="width:60px;height:28px;font-size: 15px" onclick='show()'>
				</div>
			</form><br>
			
			<font color="#FF0000"><label id="tip"></label></font>
		</div>
		
		<div id='register' align='center' style="display:none;position:relative;bottom:140px;background:#FFFFFF;padding:50px;">
			<form action="/customer/login?action=register" method='post' name="form1" onsubmit="return checkForm1()">
				<table>
					<tr>
						<td style="color:#aaa;" align="right">账号</td><td><input type="text" id='account1' name='account1'></td>
					</tr>
					<tr>
						<td style="color:#aaa;" align="right">密码</td><td><input type="password" id='password1' name='password1'></td>
					</tr>
				</table>
				<div style="position:relative;left:20px">
					<input type="submit" value="注册" style="width:60px;height:28px;font-size: 15px"/>&nbsp;&nbsp;&nbsp;	
					<input type="button" value="返回" style="width:60px;height:28px;font-size: 15px" onclick='show()'>
				</div>
			</form>
		</div>
	</body>
	
	<script>
		function checkForm()
		{
			var a=document.forms["form"]["user"].value;
			var b=document.forms["form"]["password"].value;
			if(a==null||a==""||b==null||b=="")
			{
				alert("账号或密码为空!");
				return false;
			}
		}
		function checkForm1()
		{
			var a=document.forms["form1"]["account"].value;
			var b=document.forms["form1"]["password"].value;
			if(a==null||a==""||b==null||b=="")
			{
				alert("账号或密码为空!");
				return false;
			}
		}
		function show()
		{
			if(document.getElementById('register').style.display=="none")
			{
				document.getElementById('register').style.display="block";
			}else
			{
				document.getElementById('register').style.display="none";
			}
		}
	<%
		String tip =(String)request.getParameter("errorTip");
		if(tip==null)
		{
			tip=" ";
		}
	%>
		document.getElementById("tip").innerHTML="<%=tip%>";
		function clear()
		{
			document.getElementById("tip").innerHTML="";
		}
	</script>
</html>