package task1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.models.UserDTO;

public class TestMethods {
	private RestTemplate restTemplate = new RestTemplate();
	private String testText = "test";

	private static int getLastId(String db, String table) {
		String URL = "localhost:3306";
		String USERNAME = "root";
		String PASSWORD = "2491";
		String DATABASE = db;

		int id = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://" + URL + "/" + DATABASE, USERNAME, PASSWORD);

			java.sql.Statement ps = con.createStatement();

			ResultSet rs = ps.executeQuery("SELECT MAX(id) AS id FROM " + table);

			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return id;
	}

	private ResponseEntity<UserDTO> get(Long id, String RequestUrl) {
		ResponseEntity<UserDTO> responseEntity = restTemplate.exchange(RequestUrl + "/" + id.toString(), HttpMethod.GET,
				null, new ParameterizedTypeReference<UserDTO>() {
				});
		return responseEntity;
	}

	private ResponseEntity<UserDTO> post(String RequestUrl) {
		HttpEntity<UserDTO> request = new HttpEntity<UserDTO>(new UserDTO(testText));
		ResponseEntity<UserDTO> response = restTemplate.exchange(RequestUrl, HttpMethod.POST, request, UserDTO.class);
		return response;
	}

	public ResponseEntity<UserDTO> testPOST(String RequestUrl) throws Exception {
		ResponseEntity<UserDTO> response = post(RequestUrl);
		return response;
	}

	public ResponseEntity<UserDTO> testGET(String RequestUrl, String db, String table) throws Exception {
		ResponseEntity<UserDTO> responseEntity = get(Long.valueOf(getLastId(db, table)), RequestUrl);
		return responseEntity;
	}

	public void testUPDATE(String RequestUrl, String db, String table) throws Exception {
		UserDTO updatedInstance = new UserDTO("UPDATE");
		updatedInstance.setId(getLastId(db, table));
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<UserDTO> requestUpdate = new HttpEntity<>(updatedInstance, httpHeaders);
		restTemplate.exchange(RequestUrl, HttpMethod.PUT, requestUpdate, Void.class);
	}

	public void testDELETE(String RequestUrl, String db, String table) throws Exception {
		restTemplate.delete(RequestUrl + "/" + String.valueOf(getLastId(db, table)));
	}

}
