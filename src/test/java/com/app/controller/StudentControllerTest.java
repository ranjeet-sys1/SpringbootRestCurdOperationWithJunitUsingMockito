package com.app.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.app.service.StudentService;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {
	@Mock
	private StudentService service;
	@Test
	public void testgetAll() {
		StudentController studentController=new StudentController();
		ResponseEntity<?> all = studentController.getAll();
		//assertEquals(expected, all.getBody().);
	}
}
