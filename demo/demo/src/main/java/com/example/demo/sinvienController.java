package com.example.demo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller 

public class sinvienController {

	@RequestMapping("/mysinhvien") 
	public String HienThiSV(Model model, HttpSession session) {
		String tb = "Xin chào Spring Boot";
		
		model.addAttribute("tbmodel", tb);
		
		ArrayList<sinhvien> ds= new ArrayList<sinhvien>();
		
		ds.add(new sinhvien("01","Hung"));
		ds.add(new sinhvien("02","Nga"));
		
		model.addAttribute("dsmodel",ds);
		
		session.setAttribute("mysession", ds);
		
		return "HienThiSV";

	}
	
	@GetMapping("/hello")
	public String home(Model model) {
		model.addAttribute("hello", "Hello World");
		return "hello";
	}
	
}
