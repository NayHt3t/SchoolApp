package com.hmi.school.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hmi.school.entity.Major;
import com.hmi.school.entity.Student;
import com.hmi.school.service.MajorService;
import com.hmi.school.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/student")
public class StudentController {
	private final StudentService studentService;
	private final MajorService majorService;

	public StudentController(StudentService studentService, MajorService majorService) {
		super();
		this.studentService = studentService;
		this.majorService = majorService;
	}
	
	@GetMapping("/all")
	public String all(Model model) {
		List<Student> students = studentService.getAllStudents();
		model.addAttribute("students",students);
		return "student-list";
	}
	
	@GetMapping("/{studentId}")
	public String getById(@PathVariable Long studentId, Model model) {
		Student student = studentService.getStudentById(studentId);
		model.addAttribute("student",student);
		return "student-details";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("student", new Student());
		model.addAttribute("majors", majorService.getAllMajors());
		return "add-student";
	}
	
	@PostMapping("/create")
	public String newStudent(@ModelAttribute Student student, @RequestParam Long major_id) {
		Major major = majorService.getMajorById(major_id);
		
		//associate major student
		major.getStudents().add(student);
		student.setMajor(major);
		
		studentService.saveStudent(student);
		majorService.saveMajor(major);
		
		return "redirect:/student/all";
	}
	
}
