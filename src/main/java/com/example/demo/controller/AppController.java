package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	
	@GetMapping({"/", "/login"})
	public String index() {
		return "index";
	}

	@GetMapping("/centrog")
	public String centrog() {
		return "centrog";
	}
}
