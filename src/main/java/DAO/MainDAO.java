package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.MainDTO;

public class MainDAO {
	public void dbInsertMain(MainDTO info) {
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

			String sql = " INSERT INTO public_wifi_info.main"
					+ "(X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, "
					+ "X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY,"
					+ " X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR,"
					+ " X_SWIFI_REMARS3, LNT, LAT, WORK_DTTM)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, info.getX_SWIFI_MGR_NO());
			preparedStatement.setString(2, info.getX_SWIFI_WRDOFC());
			preparedStatement.setString(3, info.getX_SWIFI_MAIN_NM());
			preparedStatement.setString(4, info.getX_SWIFI_ADRES1());
			preparedStatement.setString(5, info.getX_SWIFI_ADRES2());
			preparedStatement.setString(6, info.getX_SWIFI_INSTL_FLOOR());
			preparedStatement.setString(7, info.getX_SWIFI_INSTL_TY());
			preparedStatement.setString(8, info.getX_SWIFI_INSTL_MBY());
			preparedStatement.setString(9, info.getX_SWIFI_SVC_SE());
			preparedStatement.setString(10, info.getX_SWIFI_CMCWR());
			preparedStatement.setString(11, info.getX_SWIFI_CNSTC_YEAR());
			preparedStatement.setString(12, info.getX_SWIFI_INOUT_DOOR());
			preparedStatement.setString(13, info.getX_SWIFI_REMARS3());
			preparedStatement.setString(14, info.getLNT());
			preparedStatement.setString(15, info.getLAT());
			preparedStatement.setString(16, info.getWORK_DTTM());

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

	public ArrayList<MainDTO> dbSelectMainAll() {
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

		ArrayList<MainDTO> list = new ArrayList<MainDTO>();

		try {
			connection = DriverManager.getConnection(url, id, pwd);

			String sql = " select * from main ";

			preparedStatement = connection.prepareStatement(sql);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				MainDTO info = new MainDTO();
				info.setX_SWIFI_MGR_NO(rs.getString(1));
				info.setX_SWIFI_WRDOFC(rs.getString(2));
				info.setX_SWIFI_MAIN_NM(rs.getString(3));
				info.setX_SWIFI_ADRES1(rs.getString(4));
				info.setX_SWIFI_ADRES2(rs.getString(5));
				info.setX_SWIFI_INSTL_FLOOR(rs.getString(6));
				info.setX_SWIFI_INSTL_TY(rs.getString(7));
				info.setX_SWIFI_INSTL_MBY(rs.getString(8));
				info.setX_SWIFI_SVC_SE(rs.getString(9));
				info.setX_SWIFI_CMCWR(rs.getString(10));
				info.setX_SWIFI_CNSTC_YEAR(rs.getString(11));
				info.setX_SWIFI_INOUT_DOOR(rs.getString(12));
				info.setX_SWIFI_REMARS3(rs.getString(13));
				info.setLNT(rs.getString(14));
				info.setLAT(rs.getString(15));
				info.setWORK_DTTM(rs.getString(16));

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
}
