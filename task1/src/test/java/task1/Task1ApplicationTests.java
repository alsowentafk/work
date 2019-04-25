package task1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.models.UserDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = com.Task1Application.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:applicationTest.properties")
public class Task1ApplicationTests {
	String RequestUrl = "http://localhost:8080/api/v1";
	String RequestUrlJdbc = "http://localhost:8080/api/v2";
	String[] param = { "dbTest", "User" };
	String[] paramJdbc = { "dbTest2", "user" };
	TestMethods testMethods = new TestMethods();

	@Test
	public void testPOST() throws Exception {
		ResponseEntity<UserDTO> response = testMethods.testPOST(RequestUrl);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));

	}

	@Test
	public void testGET() throws Exception {
		ResponseEntity<UserDTO> responseEntity = testMethods.testGET(RequestUrl, param[0], param[1]);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testUPDATE() throws Exception {
		testMethods.testUPDATE(RequestUrl, param[0], param[1]);
	}

	@Test
	public void testDELETE() throws Exception {
		testMethods.testDELETE(RequestUrl, param[0], param[1]);
	}

	@Test
	public void testPOSTJdbc() throws Exception {
		ResponseEntity<UserDTO> response = testMethods.testPOST(RequestUrlJdbc);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
	}

	@Test
	public void testGETJdbc() throws Exception {
		ResponseEntity<UserDTO> responseEntity = testMethods.testGET(RequestUrlJdbc, paramJdbc[0], paramJdbc[1]);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testUPDATEJdbc() throws Exception {
		testMethods.testUPDATE(RequestUrlJdbc, paramJdbc[0], paramJdbc[1]);
	}

	@Test
	public void testDELETEJdbc() throws Exception {
		testMethods.testDELETE(RequestUrlJdbc, paramJdbc[0], paramJdbc[1]);
	}

}
