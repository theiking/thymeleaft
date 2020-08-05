package com.myclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@RequestMapping(value = "",method = RequestMethod.GET)
	public String index() {
		
		return "category/index";
	}
}
