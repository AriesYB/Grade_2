<%@page import="customer.Customer"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.StringTokenizer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>查询客户</title>
		<style type="text/css">
		#shade_add{position:absolute;z-index:998; top:0px;bottom:0px;left:0px;right:0px;margin:auto;width:100vw;height:vh;display:none;}
		#shade_modify{position:absolute;z-index:998; top:0px;bottom:0px;left:0px;right:0px;margin:auto;width:100vw;height:vh;display:none;}
		#addPage{position:absolute;top:140px;left:550px;background:#FFFFFF;padding:100px;border:1px solid #000;text-align:center;font-size: 18px;}
		#modifyPage{position:absolute;top:140px;left:550px;background:#FFFFFF;padding:100px;border:1px solid #000;text-align:center;font-size: 18px;}
		.some {width:150px;height:18px;font-size:16px;}
		.label {color:#aaa;}
		</style>
	</head>
	
	<body>
		<%--输入查询框************************************************--%>
		<div style="margn:50px"> 
			<div align="center"> 
				<h1>客户列表</h1>
			</div>	
			<div>
				<form action="list" method="post" name="form" onsubmit="return checkForm()">
					<table><%--查询输入框 按钮 选择放入table的一行--%>
						<tr>
							<th>查询客户</th>
						</tr>
						<tr>
							<td><input type="text" name="word" id="word" value="" style="width:120px;height:20px;font-size:20px"></td>
							<td><input type="submit" value="查询" style="width:60px;height:28px;font-size: 15px" onclick="display();"/></td>
							<td>
							<select name="way" id="way" style="width:60px;height:25px;font-size:15px;">
								<option value="way_id">ID</option>
								<option value="way_name">姓名</option>
							</select>
							</td>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td><button type="button" style="width:60px;height:28px;font-size: 15px" onclick="show_add()">添加</button></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%--客户信息表格************************************************--%>
		<div>
			<div align="center">
				<table class="table_customer" id="table" border="1" width="800" style="text-align: center">
					<tr>
						<th>客户ID</th>
						<th>客户姓名</th>
						<th>性别</th>
						<th>职业</th>
						<th>文化程度</th>
						<th>住址</th>
						<th>管理</th>
					</tr>
				<%
				ArrayList<?> list=(ArrayList<?>) request.getAttribute("list");
				for(int i=0;i<list.size();i++)
				{
					Customer cust =(Customer)list.get(i);%>
					<tr id='x<%=i%>'>
					<td><%=cust.getId()%></td>
					<td><%=cust.getName()%></td>
					<td><%=cust.getSex()%></td>
					<td><%=cust.getJob()%></td>
					<td><%=cust.getDegree()%></td>
					<td><%=cust.getAddress()%></td>
					<td><button name='x<%=i%>' onclick='show_modify(this.getAttribute("name"))'>修改</button>&nbsp;<button name='x<%=i%>' style="background: red;color: white" onclick='deleteIt(this.getAttribute("name"))'>删除</button></td>
					</tr>
				<%}
				%>
				</table>
			</div>
		</div>
		<%--添加悬浮窗口************************************************--%>
		<div id='shade_add'>
			<div id='addPage'>
				<form action="add" method="post" name="add_form">
					<table style="text-align: center">
						<tr>
							<td class="label" align="right">ID<td><input type="text" id="id"  name="id" class="some"></td>
						</tr>
						<tr>
							<td class="label" align="right">姓名</td><td><input type="text" id="name"  name="name"class="some"></td>
						</tr>
						<tr>
							<td class="label" align="right">性别</td><td>
							<div style="text-align: left">
								<select id="sex" name="sex" style="font-size:16px;width:60px;height:28px;">
								<option value="男">男</option>
								<option value="女">女</option>
								</select>
							</div></td>
						</tr>
						<tr>
							<td class="label" align="right">职业</td><td><input type="text" id="job" name="job" class="some"></td>
						</tr>
						<tr>
							<td class="label" align="right">文化程度</td><td>
							<div style="text-align: left">
								<select id="degree" name="degree" style="font-size:16px;width:60px;height:28px;">
									<option value="未知">未知</option>
									<option value="小学">小学</option>
									<option value="初中">初中</option>
									<option value="高中">高中</option>
									<option value="大学">大学</option>
								</select>
							</div></td>
						</tr>
						<tr>
							<td class="label"align="right">地址</td><td><input type="text" id="address" name="address" class="some"></td>
						</tr>
					</table>
					
					<div style="position:relative;left:40px">
						<input type="submit" value="添加" style="width:60px;height:28px;font-size: 15px" onclick="add_tip()"/>&nbsp;&nbsp;&nbsp;		
						<button type="button" style="width:60px;height:28px;font-size: 15px" onclick="show_add()">返回</button>
					</div>
				</form>
			</div>
		</div>
		<%--修改悬浮窗口************************************************--%>
		<div id='shade_modify'>
			<div id='modifyPage'>
				<form action="modify" method="post" name="modify_form">
					<table style="text-align: center">
						<tr>
							<td class="label" align="right">ID<td><input type="text" id="id1"  name="id1" class="some"></td><!-- *************待修改************* -->
						</tr>
						<tr>
							<td class="label" align="right">姓名</td><td><input type="text" id="name1"  name="name1" class="some"></td>
						</tr>
						<tr>
							<td class="label" align="right">性别</td><td>
							<div style="text-align: left">
								<select id="sex1" name="sex1" style="font-size:15px;width:60px;height:28px;">
								<option value="男">男</option>
								<option value="女">女</option>
								</select>
							</div></td>
						</tr>
						<tr>
							<td class="label" align="right">职业</td><td><input type="text" id="job1" name="job1" class="some"></td>
						</tr>
						<tr>
							<td class="label" align="right">文化程度</td><td>
							<div style="text-align: left">
								<select id="degree1" name="degree1" style="font-size:15px;width:60px;height:28px;">
									<option value="未知">未知</option>
									<option value="小学">小学</option>
									<option value="初中">初中</option>
									<option value="高中">高中</option>
									<option value="大学">大学</option>
								</select>
							</div></td>
						</tr>
						<tr>
							<td class="label"align="right">地址</td><td><input type="text" id="address1" name="address1" class="some"></td>
						</tr>
					</table>
					
					<div style="position:relative;left:40px">
						<input type="submit" value="修改" style="width:60px;height:28px;font-size: 15px" onclick="modify_tip()"/>&nbsp;&nbsp;&nbsp;		
						<button type="button" style="width:60px;height:28px;font-size: 15px" onclick="show_modify()">取消</button>
					</div>
				</form>
			</div>
		</div>
		<script>
		function checkForm()
		{
			var select=document.getElementById('way');
			var index=select.selectedIndex;
			var selected=select.options[index].value;
			var a=document.forms["form"]["word"].value;
			if(selected=="way_id" && !/^[+]{0,1}(\d+)$/.test(a) && a!="")
			{
				alert("请输入数字!");
				return false;
			}else
			{				
				return true;
			}	
		}			
		function show_add()
		{
			if(document.getElementById('shade_add').style.display=="block")
			{
				document.getElementById('shade_add').style.display="none";
			}else
			{
				document.getElementById('shade_add').style.display="block";
			}
		}
		function show_modify(id)
		{
			if(document.getElementById('shade_modify').style.display=="block")
			{
				document.getElementById('shade_modify').style.display="none";
			}else
			{
				document.getElementById('shade_modify').style.display="block";
			}
			modify(id);
		}
		function modify(id)
		{
			var elem=document.getElementById(id);//点击的行的元素
			var table_info = new Array()
			for (var i = 0; i < 6; i++)
			{
				table_info[i]=elem.cells[i].innerHTML;
			}
			document.getElementById('id1').value=table_info[0];
			document.getElementById('name1').value=table_info[1];
			for(var j=0;j<2;j++)
			{
				if (document.getElementById('sex1').options[j].text==table_info[2])
				{
					document.getElementById('sex1')[j].selected=true; 
				}
			}
			document.getElementById('job1').value=table_info[3];
			for(var k=0;k<5;k++)
			{
				if (document.getElementById('degree1').options[k].value==table_info[4])
				{
					document.getElementById('degree1')[k].selected=true; 
				}
			}
			document.getElementById('address1').value=table_info[5];
		}
		function add_tip()
		{
			
			alert("添加成功!");
		}
		function modify_tip()
		{
			
			alert("修改成功!");
		}
		function deleteIt(id)
		{
			var elem=document.getElementById(id);//传入id
			var r=confirm("确认删除"+"["+elem.cells[1].innerHTML+"]"+"?");//获取该行的2列  不是value二是innerHTML
			if(r==true)
			{
			elem.parentNode.removeChild(elem);//父节点删除该子节点	
			var url="/customer/list?id="+elem.cells[0].innerHTML;//把ID通过url传给servlet
			window.location.href=url;
			alert('删除成功!');
			}
		}
		</script>
	</body>
</html>