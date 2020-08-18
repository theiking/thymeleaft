package com.myclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/video")
public class VideoController {
	
	@RequestMapping(value = "",method = RequestMethod.GET)
	public String index() {
	
		return "video/index";
	}
	
	
	@RequestMapping(value = "/add",method = RequestMethod.GET)
	public String add() {
		
		
		return "video/add";
	}
	
	
}
