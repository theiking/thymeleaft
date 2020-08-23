package com.myclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/target")
public class TargetController {
	
	@RequestMapping(value = "",method = RequestMethod.GET)
	public String index() {
		
		
		return "target/index";
	}
	
	@RequestMapping(value = "/add",method = RequestMethod.GET)
	public String add() {
		
		
		return "target/add";
	}
}
