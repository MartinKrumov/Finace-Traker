package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

	@RequestMapping(value="/tindex" , method = RequestMethod.GET)
	public String sayHello(Model model) {
		model.addAttribute("kekst", "Hi !");
		return "test";
	}


	@RequestMapping(value="/mindex" , method = RequestMethod.GET)
	public String saygodbuy(Model model) {
		model.addAttribute("kekst", "buyy !");
		return "test";
	}

}
