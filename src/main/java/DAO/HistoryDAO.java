package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.HistoryDTO;

public class HistoryDAO {
	public void dbInsertHistory(String _y, String _x) {
		String url = "jdbc:mariadb://192.168.35.50:3306/public_wifi_info";
		String id = "testuser1";
		String pwd = "zerobase";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(url, id, pwd);

			String sql = " INSERT INTO public_wifi_info.history " + " (LNT, LAT, `DATE`) "
					+ " VALUES(?, ?, now()) ";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, _y);
			preparedStatement.setString(2, _x);

			int affected = preparedStatement.executeUpdate();
			if (affected > 0) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<HistoryDTO> dbSelectHistory() {
		String url = "jdbc:mariadb://192.168.35.50:3306/public_wifi_info";
		String id = "testuser1";
		String pwd = "zerobase";

		// 1. 드라이버 로드
		// 2. 커넥션 객체 생성
		// 3. 스테이트먼트 객체 생성
		// 4. 쿼리 실행
		// 5. 결과 실행
		// 6. 객체 연결 해제 (close)

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		ArrayList<HistoryDTO> list = new ArrayList<HistoryDTO>();

		try {
			connection = DriverManager.getConnection(url, id, pwd);

			String sql = " select * from history ";

			preparedStatement = connection.prepareStatement(sql);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				HistoryDTO info = new HistoryDTO();
				info.setId(rs.getString(1));
				info.setLnt(rs.getString(2));
				info.setLat(rs.getString(3));
				info.setDate(rs.getString(4));

				list.add(info);
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public void dbDeleteHistory(String _id) {
		String url = "jdbc:mariadb://192.168.35.50:3306/public_wifi_info";
		String id = "testuser1";
		String pwd = "zerobase";

		// 1. 드라이버 로드
		// 2. 커넥션 객체 생성
		// 3. 스테이트먼트 객체 생성
		// 4. 쿼리 실행
		// 5. 결과 실행
		// 6. 객체 연결 해제 (close)

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		String memberTypeValue = "email";
		String userIdValue = "zerobase@naver.com";

		try {
			connection = DriverManager.getConnection(url, id, pwd);

			String sql = " delete from history where ID = ?; ";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, _id);

			int affected = preparedStatement.executeUpdate();
			if (affected > 0) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
