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
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/major")
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
		return "redirect:/major/all";
	}
	
	@GetMapping("/update/{majorId}")
	public String showUpdateForm(@PathVariable Long majorId, Model model) {
		Major major = majorService.getMajorById(majorId);
		model.addAttribute("major", major);
		return "add-major";
	}
	
	@GetMapping("/delete/{majorId}")
	public String delete(@PathVariable Long majorId) {
		majorService.deleteMajorById(majorId);
		return "redirect:/major/all";
	}
	
	@GetMapping("/{majorId}")
	public String view(@PathVariable Long majorId, Model model) {
		Major major = majorService.getMajorById(majorId);
		model.addAttribute("major",major);
		return "major-details";
	}
	

}
