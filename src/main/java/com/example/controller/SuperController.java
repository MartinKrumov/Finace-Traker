package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.SuperMan;
import com.example.model.SuperManDAO;

@Controller
public class SuperController {
	
	@RequestMapping(value={"/super", "/duper", "/super/duper/**"}, method=RequestMethod.GET)
	public String showSuperMan(Model viewModel) {
		SuperMan superMan = SuperManDAO.getSuperMan();
		
		viewModel.addAttribute(superMan);
		
		return "superView";
	}
}
