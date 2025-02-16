package Etc;

import java.util.ArrayList;

import DAO.HistoryDAO;
import DAO.MainDAO;
import DTO.MainDTO;

public class CalDistance {

	// 두 지점 간의 거리를 계산하는 메서드
	public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
		double R = 6371; // 지구 반지름 (km)
		double dLat = toRadians(lat2 - lat1);
		double dLon = toRadians(lon2 - lon1);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(toRadians(lat1)) * Math.cos(toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return R * c; // 거리 (km)
	}

	// 도(degree)를 라디안(radian)으로 변환
	private double toRadians(double degree) {
		return degree * (Math.PI / 180);
	}

	public ArrayList<MainDTO> sortMainDTO(double _lat, double _lnt) {
		MainDAO dao = new MainDAO();
		ArrayList<MainDTO> list = dao.dbSelectMainAll();

		for (MainDTO dto : list) {
			double lat = Double.parseDouble(dto.getLAT());
			double lnt = Double.parseDouble(dto.getLNT());
			dto.distance = calculateDistance(_lat, _lnt, lat, lnt);
		}

		list.sort((a, b) -> Double.compare(a.distance, b.distance));

		HistoryDAO historyDAO = new HistoryDAO();
		historyDAO.dbInsertHistory(Double.toString(_lnt), Double.toString(_lat));

		return list;
	}
}