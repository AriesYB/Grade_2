<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>商品列表</title>
	<link href="https://cdn.bootcss.com/normalize/8.0.0/normalize.min.css" rel="stylesheet">
	<link rel="stylesheet" href="/goods/css/list.css">
</head>

<body>
	<div class="nav">
		<div class="container clear-float">
			<div class="fl">
				<span>商品列表</span>
			</div>
			<div class="fr">
				<span>${sessionScope.id },你好！</span>
				<input id="exit_button" type="button" value="退出" onclick="exit()">
			</div>
		</div>
	</div>
	<div class="main">
		<div class="container">
			<form action="${pageContext.request.contextPath}/upload" name="form" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<th class="table-cell">图片</th>
						<th class="table-cell">商品ID</th>
						<th class="table-cell">商品名</th>
						<th class="table-cell">生产商</th>
						<th class="table-cell">类型</th>
						<th class="table-cell">型号</th>
						<th class="table-cell">描述</th>
						<th class="table-cell">操作</th>
					</tr>
					<c:forEach items="${requestScope.list}" var="good">
						<tr>
							<td class="table-cell"><img src="${pageContext.request.contextPath}/jsp/image/${good.imgUrl}" alt="${good.name}"
								 height="60px" width="80px"></td>
							<td class="table-cell">${good.id}</td>
							<td class="table-cell">${good.name}</td>
							<td class="table-cell">${good.producer}</td>
							<td class="table-cell">${good.type}</td>
							<td class="table-cell">${good.model}</td>
							<td class="table-cell">${good.description}</td>
							<td class="table-cell">
								<div class="operating clear-float">
									<div class="fl">
										<input type="hidden" id="${good.id}" name="" value="">
										<input class="file-button" id="file_btn_${good.id}" type="button" value="浏览">
										<input class="file-content" id="file_cont_${good.id}" type="file" accept="image/*" name="${good.id}" onchange='change_button(this.getAttribute("name"))' onmouseover='btn_color(this.getAttribute("name"))' onmouseout='re_btn_color(this.getAttribute("name"))'>
									</div>
									<div class="fr">
										<input class="button-submit" id="submit_btn_${good.id}" type="button" name="${good.id}" value="保存">
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</div>
	</div>
	<div class="footer">
		<div class="container">
			当前在线人数: ${applicationScope.userNum }
		</div>
	</div>
	<script type="text/javascript" src="/goods/js/list.js"></script>
</body>

</html>