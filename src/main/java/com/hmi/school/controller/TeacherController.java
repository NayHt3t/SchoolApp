package com.hmi.school.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hmi.school.entity.Student;
import com.hmi.school.entity.Teacher;
import com.hmi.school.service.StudentService;
import com.hmi.school.service.TeacherService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	private final TeacherService teacherService;
	private final StudentService studentService;

	public TeacherController(TeacherService teacherService, StudentService studentService) {
		super();
		this.teacherService = teacherService;
		this.studentService = studentService;
	}
	
	@GetMapping("/all")
	public String all(Model model) {
		List<Teacher> teachers = teacherService.getAllTeachers();
		model.addAttribute("teachers",teachers);
		return "teacher-list";
	}
	
	@GetMapping("/create")
	public String form(Model model) {
		model.addAttribute("teacher", new Teacher());
		return "add-teacher";
	}
	
	@PostMapping("/create")
	public String postNew(@ModelAttribute Teacher teacher) {
		teacherService.saveTeacher(teacher);
		return "redirect:/teacher/all";
	}
	
	@GetMapping("/{teacherId}/assign")
	public String assignForm(@PathVariable Long teacherId, Model model) {
		List<Student> students = studentService.getAllStudents();
		model.addAttribute("students", students);
		Teacher teacher = teacherService.getTeacherById(teacherId);
		model.addAttribute("teacher", teacher);
		return "assign-form";
	}
	
	@PostMapping("/assign")
	public String assign(@RequestParam Long teacherId, @RequestParam Long studentId) {
		Teacher teacher = teacherService.getTeacherById(teacherId);
		Student student = studentService.getStudentById(studentId);
		teacher.getStudents().add(student);
		student.getTeachers().add(teacher);
		teacherService.saveTeacher(teacher);
		studentService.saveStudent(student);
		
		return "redirect:/teacher/all";
	}
	
	@GetMapping("/{teacherId}")
	public String details(@PathVariable Long teacherId, Model model) {
		Teacher teacher = teacherService.getTeacherById(teacherId);
		model.addAttribute("teacher",teacher);
		return "teacher-details";
	}
	
	
	
}
