package com.hmi.school.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.hmi.school.entity.Major;
import com.hmi.school.service.MajorService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MajorController {
	
	private final MajorService majorService;

	public MajorController(MajorService majorService) {
		super();
		this.majorService = majorService;
	}

	@GetMapping("/all")
	public String all(Model model) {
		List<Major> majorList = majorService.getAllMajors();
		model.addAttribute("majorlist", majorList);
		return "major-list";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("major", new Major());
		return "add-major";
	}
	
	@PostMapping("/create")
	public String postMajor(@ModelAttribute Major major) {
		Major createdMajor = majorService.saveMajor(major);
		System.out.println("created major id = " + createdMajor.getId());
		return "redirect:/all";
	}
	
	@GetMapping("/update/{majorId}")
	public String showUpdateForm(@PathVariable Long majorId, Model model) {
		Major major = majorService.getMajorById(majorId);
		model.addAttribute("major", major);
		return "add-major";
	}
	
	

}
