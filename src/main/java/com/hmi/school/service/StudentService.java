package com.hmi.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hmi.school.dao.StudentDAO;
import com.hmi.school.entity.Student;

@Service
public class StudentService {
	private final StudentDAO studentDAO;

	public StudentService(StudentDAO studentDAO) {
		super();
		this.studentDAO = studentDAO;
	}

	public List<Student> getAllStudents() {
		return (List<Student>) studentDAO.findAll();
	}

	// retrieve
	public Student getStudentById(Long studentId) {
		Optional<Student> studentOptional = studentDAO.findById(studentId);
		if (studentOptional.isEmpty()) {
			System.out.println("Student with id : " + studentId + " is not found.");
			throw new RuntimeException();
		}
		return studentOptional.get();
	}

	// create or update
	public Student saveStudent(Student student) {
		return studentDAO.save(student);
	}

	// delete
	public void deleteStudentById(Long studentId) {
		studentDAO.deleteById(studentId);
	}

}
