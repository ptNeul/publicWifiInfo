<%@page import="DTO.HistoryDTO"%>
<%@page import="DAO.HistoryDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.MainDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
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
	margin: 0 auto;
	padding: 15px 20px;
}

.header {
	padding: 15px 20px;
	text-align: left;
}

.nav {
	padding: 15px 20px;
	text-align: left;
}

.nav a {
	text-decoration: none;
}

.nav a::after {
	white-space: pre;
	content: ' |';
}

.nav a:last-child::after {
	content: '';
}

table {
	width: 100%;
	border-collapse: collapse;
	margin: 20px 0;
}

table, th, td {
	border: 1px solid #ccc;
}

th, td {
	padding: 10px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

.message {
	text-align: center;
}
</style>
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<%
	HistoryDAO dao = new HistoryDAO();
	ArrayList<HistoryDTO> list = dao.dbSelectHistory();
	%>

	<div class="header">
		<h1>위치 히스토리 목록</h1>
	</div>
	<div class="nav">
		<a href="index.jsp">홈</a> <a href="#">위치 히스토리 목록</a> <a
			href="open_wifi_info_get.jsp">Open API 와이파이 정보 가져오기</a>
	</div>
	<div class="container">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>X좌표</th>
					<th>Y좌표</th>
					<th>조회일자</th>
					<th>비고</th>
				</tr>
			</thead>
			<tbody id="historyTableBody">
				<%
				for (HistoryDTO dto : list) {
					out.write("<tr>");
					out.write("<td>" + dto.getId() + "</td>");
					out.write("<td>" + dto.getLat() + "</td>");
					out.write("<td>" + dto.getLnt() + "</td>");
					out.write("<td>" + dto.getDate() + "</td>");
					out.write("<td><button type=\"button\" onclick=\"deleteRow(this, " + dto.getId() + ")\">삭제</button></td>");
					out.write("</tr>");
				}
				%>
			</tbody>
		</table>
	</div>

	<script>
		function deleteRow(button, id) {
			var row = button.parentNode.parentNode;

			$.ajax({
				url : 'history_delete.jsp',
				type : 'POST',
				data : {
					id : id
				},
				success : function(response) {
					if (response.success) {
						row.parentNode.removeChild(row);
					} else {
						alert('삭제 실패');
					}
				},
				error : function(xhr, status, error) {
					console.error('AJAX 오류:', error);
				}
			});
		}
	</script>
</body>
</html>
