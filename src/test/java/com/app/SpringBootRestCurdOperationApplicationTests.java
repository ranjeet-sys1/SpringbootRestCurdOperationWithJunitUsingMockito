package com.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment= WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
@DisplayName("STUDENTS TEST SERVICE")
@TestMethodOrder(OrderAnnotation.class)
class SpringBootRestCurdOperationApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}
	@Test
	@Order(1)
	@DisplayName("Fetch All Student")
	public void testGetAll()throws Exception
	{
		//1.creating request
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAll");
		//2.Execution of request
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		//3.get Result and Response
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
		System.out.println(response.getContentAsString());
		
		
	}
	@Test
	@Order(2)
	@DisplayName("Fetch One Student By ID")
	public void testGetStudent() throws Exception
	{
		//1.creating request
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/get?id=2");
		//2.execution of request
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		//3 to get response using result
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
		System.out.println(response.getContentAsString());
		
	}
	@Test
	@Order(3)
	@DisplayName("Save One Student")
	public void  testsaveStudent() throws Exception
	{
		//1.
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"stdName\":\"Abhishek\",\"stdFee\":2000.51,\"stdCourse\":\"Springboot\"}")
				;
		//2.
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		//3.
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		//assertNotNull(response.getContentAsString());
		if(!response.getContentAsString().contains("Student  is saved")) {
			fail("Student not saved properly");
		}
		
	}
	@Test
	@Order(4)
	@DisplayName("Student Update")
	public void testupdateStudent() throws Exception
	{
		//1.
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"stdId\":10,\"stdName\":\"Kumar Abhishek\",\"stdFee\":5000.21,\"stdCourse\":\"springboot & MicroServices\"}")
				;
		//2.
				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
				//3.
				MockHttpServletResponse response = result.getResponse();
				assertEquals(HttpStatus.RESET_CONTENT.value(), response.getStatus());
				assertNotNull(response.getContentAsString());
				if(!response.getContentAsString().contains("Student  is updated"))
				{
					fail("student not updated properly");
				}
		
	}
	@Test
	@Order(5)
	@DisplayName("Student Delete by id")
	public void testdelete() throws Exception
	{
		//1.
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/delete/10");
		//2.
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		//3.
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
		if(!response.getContentAsString().contains("Student  is deleted"))
		{
			fail("student not deleted");
		}
		
	}

}
