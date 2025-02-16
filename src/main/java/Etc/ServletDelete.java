package Etc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.HistoryDAO;

@WebServlet("/publicWiFiInfo/src/main/webapp/history_delete.jsp") // 요청 경로 설정
public class ServletDelete {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 요청 파라미터에서 'id' 값 추출
		String id = request.getParameter("id");

		if (id == null || id.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
			response.getWriter().write("{\"success\": false, \"message\": \"아이디가 필요합니다.\"}");
			return;
		}

		HistoryDAO dao = new HistoryDAO();

		dao.dbDeleteHistory(id);
		boolean success = true;

		String jsonResponse = "{\"success\": " + success + "}";
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(jsonResponse);
	}
}
