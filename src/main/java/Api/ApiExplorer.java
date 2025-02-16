package Api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import DAO.MainDAO;
import DTO.MainDTO;

public class ApiExplorer {

	public int getOpenWifiInfo() throws Exception {

		String apiKey = "53596f496773696131333271666d7149";
		String start = "1";
		String end = "100";

		// 초기 row 갯수를 가져오기 위한 셋팅합니다.
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /* URL */
		urlBuilder.append("/" + URLEncoder.encode(apiKey, "UTF-8")); /* 인증키 (sample사용시에는 호출시 제한됩니다.) */
		urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /* 요청파일타입 (xml,xmlf,xls,json) */
		urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); /* 서비스명 (대소문자 구분 필수입니다.) */
		urlBuilder.append("/" + URLEncoder.encode(start, "UTF-8")); /* 요청시작위치 (sample인증키 사용시 5이내 숫자를 사용해야 합니다.) */
		urlBuilder.append("/" + URLEncoder.encode(end, "UTF-8")); /* 요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됩니다.) */
		// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

		System.out.println(urlBuilder.toString());

		// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 api탭 - '요청인자'부분에 자세히 나와 있습니다.
		// 요청 인자가 여러개일 경우, 필수 여부를 확인해 필수인 값은 무조건 넣어서 조회해야 합니다.
		// 필수가 아닐 경우, 그리고 공백으로 처리해야할 경우 공백 대신 %20을 붙여 조회하시면 됩니다.
		// ex)
		// http://openapi.seoul.go.kr:8088/sample/xml/SeoulAdminMesure/1/5/%20/%20/%20/%20/향진
		urlBuilder.append("/" + URLEncoder.encode("%20", "UTF-8")); /* 서비스별 추가 요청인자들 */
		urlBuilder.append("/" + URLEncoder.encode("%20", "UTF-8")); /* 서비스별 추가 요청인자들 */

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		// System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한
		// 확인이 필요하므로 추가합니다.*/

		BufferedReader rd;
		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		// System.out.println(sb.toString());

		// JSON 응답 출력
		System.out.println("JSON 응답: ");
		System.out.println(sb.toString());
		System.out.println();

		// JSON 파싱
		try {
			Gson gson = new Gson();
			WifiInfoResponse response = gson.fromJson(sb.toString(), WifiInfoResponse.class);			
			System.out.println(response);

			List<MainDTO> list = response.getTbPublicWifiInfo().getRow();

			MainDAO dao = new MainDAO();
			
			Iterator<MainDTO> iterator = list.iterator();
			while (iterator.hasNext()) {
			    MainDTO info = iterator.next();
			    // 순회 중에 데이터를 처리하고
			    dao.dbInsertMain(info); // 데이터베이스에 삽입
			}

			return list.size();

		} catch (JsonSyntaxException e) {
			System.err.println("JSON 파싱 오류: " + e.getMessage());
			e.printStackTrace();
		}

		return -1;
	}
}
