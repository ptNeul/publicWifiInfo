<%@page import="DAO.HistoryDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
// 클라이언트에서 전달된 'id' 파라미터 받아오기
String id = request.getParameter("id");

if (id != null && !id.isEmpty()) {
	HistoryDAO dao = new HistoryDAO();

	dao.dbDeleteHistory(id);
	boolean success = true;

	response.setContentType("application/json;charset=UTF-8");
	String jsonResponse = "{\"success\": " + success + "}";
	response.getWriter().write(jsonResponse);
}
%>