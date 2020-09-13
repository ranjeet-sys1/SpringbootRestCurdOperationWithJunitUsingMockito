package com.app.service;

import java.util.List;

import com.app.model.Student;

public interface StudentService {
	public List<Student> getAll();
	public Student getStudent(Integer Id);
	public Integer saveStudent(Student student);
	public void delete(Integer id);
	public boolean isExit(Integer id);
	public void updateStudent(Student student);

}
