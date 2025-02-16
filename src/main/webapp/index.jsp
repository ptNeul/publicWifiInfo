<%@page import="DTO.MainDTO"%>
<%@page import="Etc.CalDistance"%>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<div class="header">
		<h1>와이파이 정보 구하기</h1>
	</div>
	<div class="nav">
		<a href="#">홈</a> <a href="history.jsp">위치 히스토리 목록</a> <a
			href="open_wifi_info_get.jsp">Open API 와이파이 정보 가져오기</a>
	</div>
	<div class="container">
		<form action="your-servlet-url" method="post">
			<div>
				<label for="lat">LAT:</label> <input type="text" id="lat" name="lat"
					value="0.0"> <label for="lnt">,&nbsp;LNT:</label> <input
					type="text" id="lnt" name="lnt" value="0.0">
				<button type="button" onclick="getLocation()">내 위치 가져오기</button>
				<button type="button" onclick="getNearWifiInfo()">근처 WIFI
					정보 보기</button>
			</div>
		</form>
		<table>
			<thead>
				<tr>
					<th>거리(Km)</th>
					<th>관리번호</th>
					<th>자치구</th>
					<th>와이파이명</th>
					<th>도로명주소</th>
					<th>상세주소</th>
					<th>설치위치(층)</th>
					<th>설치유형</th>
					<th>설치기관</th>
					<th>서비스구분</th>
					<th>망종류</th>
					<th>설치년도</th>
					<th>실내외구분</th>
					<th>WIFI접속환경</th>
					<th>X좌표</th>
					<th>Y좌표</th>
					<th>작업일자</th>
				</tr>
			</thead>
			<tbody id="wifiTableBody">
				<!-- 여기에 데이터가 추가될 것입니다 -->
			</tbody>
		</table>
		<div class="message">위치 정보를 입력한 후에 조회해 주세요.</div>
	</div>

	<script>
		function getLocation() {
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(showPosition);
			} else {
				alert("Geolocation is not supported by this browser.");
			}
		}

		function showPosition(position) {
			document.getElementById("lat").value = position.coords.latitude;
			document.getElementById("lnt").value = position.coords.longitude;
		}

		function getNearWifiInfo() {
			var lat = document.getElementById("lat").value;
			var lnt = document.getElementById("lnt").value;

			if (lat === "0.0" && lnt === "0.0") {
				alert("위치를 먼저 가져오세요.");
				return;
			}

			$.ajax({
				url : "near_wifi_info_get.jsp",
				type : "GET",
				data : {
					lat : lat,
					lnt : lnt
				},
				dataType : 'json',
				success : function(response) {
					console.log(response);

					$("#wifiTableBody").empty();

					response.forEach(function(wifi) {
						var row = "<tr>";
						row += "<td>" + wifi.distance + "</td>";
						row += "<td>" + wifi.X_SWIFI_MGR_NO + "</td>";
						row += "<td>" + wifi.X_SWIFI_WRDOFC + "</td>";
						row += "<td>" + wifi.X_SWIFI_MAIN_NM + "</td>";
						row += "<td>" + wifi.X_SWIFI_ADRES1 + "</td>";
						row += "<td>" + wifi.X_SWIFI_ADRES2 + "</td>";
						row += "<td>" + wifi.X_SWIFI_INSTL_FLOOR + "</td>";
						row += "<td>" + wifi.X_SWIFI_INSTL_TY + "</td>";
						row += "<td>" + wifi.X_SWIFI_INSTL_MBY + "</td>";
						row += "<td>" + wifi.X_SWIFI_SVC_SE + "</td>";
						row += "<td>" + wifi.X_SWIFI_CMCWR + "</td>";
						row += "<td>" + wifi.X_SWIFI_CNSTC_YEAR + "</td>";
						row += "<td>" + wifi.X_SWIFI_INOUT_DOOR + "</td>";
						row += "<td>" + wifi.X_SWIFI_REMARS3 + "</td>";
						row += "<td>" + wifi.LNT + "</td>";
						row += "<td>" + wifi.LAT + "</td>";
						row += "<td>" + wifi.WORK_DTTM + "</td>";
						row += "</tr>";

						$("#wifiTableBody").append(row);
					});
				},
				error : function() {
					alert("데이터를 불러오는 데 실패했습니다.");
				}
			});
		}
	</script>
</body>
</html>
