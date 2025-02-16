<%@page import="Api.ApiExplorer"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

.container {
	width: 80%;
	margin: 0 auto;
	padding: 20px;
	text-align: center;
}

.message {
	font-size: 1.2em;
	font-weight: bold;
	color: #333;
	margin: 20px 0;
}

.nav a {
	display: inline-block;
	margin: 0 10px;
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="container">
		<div class="message">
			<%
			ApiExplorer exp = new ApiExplorer();
			int size = exp.getOpenWifiInfo();
			%>
			<%=size%>개의 WIFI 정보를 정상적으로 저장하였습니다.
		</div>
		<div class="nav">
			<a href="index.jsp">홈으로 가기</a>
		</div>
	</div>
</body>
</html>
