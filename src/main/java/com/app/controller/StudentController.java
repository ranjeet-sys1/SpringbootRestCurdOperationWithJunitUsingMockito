package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Student;
import com.app.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	private StudentService service;
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(){
		ResponseEntity<?> resp=null;
		try {
			List<Student> all = service.getAll();
			resp=new ResponseEntity<List<Student>>(all,HttpStatus.OK);
			return resp;
			
		} catch (Exception e) {
			resp=new ResponseEntity<String>("unable to fetch record",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			
			
		}
		return resp;
		
		
	}
	@GetMapping("/get")
	public ResponseEntity<?> getStudent(@RequestParam("id")int id){
		ResponseEntity<?> resp=null;
		try {
			Student student = service.getStudent(id);
			resp=new ResponseEntity<Student>(student,HttpStatus.OK);
			return resp;
		} catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to fetch data",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
		
	}
	@PostMapping("/save")
	public ResponseEntity<?> saveStudent(@RequestBody Student student){
		ResponseEntity<?> resp=null;
		try {
			Integer saveStudent = service.saveStudent(student);
			resp=new ResponseEntity<String>("Student "+saveStudent+" is saved",HttpStatus.OK);
			return resp;
			
		} catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to save data",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
		
		
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id")Integer id){
		ResponseEntity<?> resp=null;
		try {
			boolean exit = service.isExit(id);
			if(exit) {
				service.delete(id);
				resp=new ResponseEntity<String>("Student  is deleted",HttpStatus.OK);
				return resp;
				
			}
		} catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to delete data",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
		
		
	}
	@PutMapping("/update")
	public ResponseEntity<String> updateStudent(@RequestBody Student student){
		ResponseEntity<String> resp=null;
		try {
			Integer stdId = student.getStdId();
			boolean exit = service.isExit(stdId);
			if(exit) {
				service.updateStudent(student);
				resp=new ResponseEntity<String>("Student  is updated",HttpStatus.RESET_CONTENT);
				
			}else {
				resp=new ResponseEntity<String>("Student "+stdId+" is not existing",HttpStatus.BAD_REQUEST);
			}
			
				
			return resp;
				
		} catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to update data",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
		
		
	}

}
