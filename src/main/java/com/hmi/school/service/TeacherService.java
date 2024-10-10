package com.hmi.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hmi.school.dao.TeacherDAO;
import com.hmi.school.entity.Teacher;

@Service
public class TeacherService {
	private final TeacherDAO teacherDAO;

	public TeacherService(TeacherDAO teacherDAO) {
		super();
		this.teacherDAO = teacherDAO;
	}
	
	public List<Teacher> getAllTeachers(){
		return (List<Teacher>) teacherDAO.findAll();
	}
	
	public Teacher saveTeacher(Teacher teacher) {
		return teacherDAO.save(teacher);
	}
	
	public Teacher getTeacherById(Long teacherId) {
		Optional<Teacher> teacherOptional = teacherDAO.findById(teacherId);
		if(teacherOptional.isEmpty()) {
			throw new RuntimeException("Teacher with id="+teacherId+" is not found.");
		}
		return teacherOptional.get();
	}
}
