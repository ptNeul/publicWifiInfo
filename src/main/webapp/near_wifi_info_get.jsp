<%@page import="DTO.MainDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Etc.CalDistance"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
String latParam = request.getParameter("lat");
String lntParam = request.getParameter("lnt");

System.out.println("LAT: " + latParam);
System.out.println("LNT: " + lntParam);

if (latParam != null && !latParam.trim().isEmpty() && lntParam != null && !lntParam.trim().isEmpty()) {
	double lat = Double.parseDouble(latParam);
	double lnt = Double.parseDouble(lntParam);

	CalDistance cal = new CalDistance();
	ArrayList<MainDTO> list = cal.sortMainDTO(lat, lnt);

	System.out.println("Calculated Wifi List: " + list.size() + " entries");

	Gson gson = new Gson();
	String jsonResponse = gson.toJson(list);

	response.setContentType("application/json");
	response.getWriter().write(jsonResponse);
} else {
	System.out.println("LAT or LNT parameter is missing or invalid.");
}
%>
