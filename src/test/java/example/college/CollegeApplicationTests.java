package example.college;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import example.college.dto.NewRegister;
import example.college.entitys.Course;
import example.college.entitys.Student;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CollegeApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldReturnAStudentsWhenDataIsSaved() {
		ResponseEntity<String> response = restTemplate
				.getForEntity("/college/4?option=course", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void shouldReturnANullWhenDataIsNotSaved() {
		ResponseEntity<String> response = restTemplate
				.getForEntity("/college/0?option=course", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}

	/*
	Descomentar para realizar pruebas de registro
	 * @Test
	 * 
	 * @DirtiesContext
	 * void shouldCreateANewCursoAndRegister() {
	 * NewRegister newRegister = new NewRegister();
	 * newRegister.setStudentId(6L);
	 * newRegister.setName("Fisica");
	 * 
	 * ResponseEntity<Void> createResponse = restTemplate
	 * .postForEntity("/college/registration", newRegister, Void.class);
	 * assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	 * }
	 */

	@Test
	@DirtiesContext
	void shouldNotCreateANewCursoAndRegisterWhenIsEmpyOrNull() {
		NewRegister newRegister = new NewRegister();
		newRegister.setStudentId(null);
		newRegister.setName("");

		ResponseEntity<Void> createResponse = restTemplate
				.postForEntity("/college/registration", newRegister, Void.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
}
