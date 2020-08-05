package com.myclass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclass.entity.User;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("users", userRepository.findAll());
		model.addAttribute("roles", roleRepository.findAll());
		return "user/index";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", roleRepository.findAll());
		return "user/add";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String post(ModelMap model, @Validated @ModelAttribute ("user") User user, BindingResult errors ) {
		
		if(errors.hasErrors()) {
			return "user/add";
		}
		try {
			userRepository.saveOrUpdate(user);
			
			return "redirect:/user";
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("message", "Thêm mới thất bại!");
		return "user/add";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam ("id") int id, ModelMap model) {
		model.addAttribute("user", userRepository.findById(id));
		model.addAttribute("roles", roleRepository.findAll());
		return "user/edit";
	}
	
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String editpost(ModelMap model, @Validated @ModelAttribute ("user") User user, BindingResult errors ) {
		
		if(errors.hasErrors()) {
			return "redirect:/user/edit?id="+user.getId();
		}
		try {
			userRepository.saveOrUpdate(user);
			
			return "redirect:/user";
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("message", "Chỉnh sửa thất bại!");
		return "redirect:/user/edit?id="+user.getId();
	}
	
	

	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		userRepository.delete(id);
		return "redirect:/user";
	}
}
