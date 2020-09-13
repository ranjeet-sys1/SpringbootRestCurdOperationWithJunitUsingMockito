package com.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Student;
import com.app.repo.StudentRepository;
import com.app.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository repo;

	@Override
	public List<Student> getAll() {
		List<Student> findAll = repo.findAll();
		
		return findAll;
	}

	@Override
	public Student getStudent(Integer id) {
		Student student=null;
		Optional<Student> findById = repo.findById(id);
		if(findById.isPresent()) {
			student = findById.get();
		}
		
		return student;
	}

	@Override
	public Integer saveStudent(Student student) {
		Integer stdId = repo.save(student).getStdId();
		return stdId;
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public boolean isExit(Integer id) {
		
		return repo.existsById(id);
	}

	@Override
	public void updateStudent(Student student) {
		repo.save(student);
		
	}

}
