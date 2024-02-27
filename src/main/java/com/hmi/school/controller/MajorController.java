package com.hmi.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MajorController {

	@GetMapping("/all")
	public String all() {
		return "major-list";
	}

}
