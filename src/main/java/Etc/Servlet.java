package Etc;

import DTO.MainDTO;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/publicWiFiInfo/src/main/webapp/near_wifi_info_get.jsp") // 요청 경로 설정
public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 2916377632777226862L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 파라미터 받기
		String latParam = request.getParameter("lat");
		String lntParam = request.getParameter("lnt");

		if (latParam != null && !latParam.trim().isEmpty() && lntParam != null && !lntParam.trim().isEmpty()) {
			double lat = Double.parseDouble(latParam);
			double lnt = Double.parseDouble(lntParam);

			CalDistance cal = new CalDistance();
			ArrayList<MainDTO> list = cal.sortMainDTO(lat, lnt); // 거리 계산 및 정렬

			Gson gson = new Gson();
			String jsonResponse = gson.toJson(list);

			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.write(jsonResponse);
			out.close();
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "위치 정보를 제공하세요.");
		}
	}
}