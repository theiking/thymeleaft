package com.myclass.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap model) {
		// LẤY DANH SÁCH ROLE TỪ DATABASE
		List<Role> list = roleRepository.findAll();
		// CHUYỂN TIẾP DS ROLE QUA CHO THYMELEAF (index.html)
		model.addAttribute("roles", list);
		return "role/index";
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search(@RequestParam("keyword") String keyword, ModelMap model) {
		// Gọi phương thức tìm kiếm của tầng repository
		List<Role> list = roleRepository.search(keyword);
		model.addAttribute("roles", list);
		return "role/index";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("role", new Role());
		return "role/add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String post(ModelMap model, @Validated @ModelAttribute("role") Role role, 
			BindingResult errors) {
		
		// NẾU CÓ LỖI XẢY RA, CHUYỂN TIẾP LẠI VỀ TRANG HIỆN TẠI 
		// ĐỂ SHOW LỖI LÊN CHO NGƯỜI DÙNG THẤY
		if(errors.hasErrors()) {
			return "role/add";
		}
		
		try {
			roleRepository.saveOrUpdate(role);
			return "redirect:/role";
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		// Nếu thất bại => chuyển tiếp về lại trang thêm mới
		model.addAttribute("message", "Thêm mới thất bại!");
		return "role/add";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") int id, ModelMap model) {

		model.addAttribute("role", roleRepository.findById(id));
		return "role/edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(ModelMap model,
			@Valid @ModelAttribute("role") Role role,
			BindingResult errors) {
		// KIỂM TRA NHẬP LIỆU
		if(errors.hasErrors()) {
			return "role/edit";
		}
		
		try {
			System.out.println(role.getId());
			roleRepository.saveOrUpdate(role);
			return "redirect:/role";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "Cập nhật thất bại!");
		return "role/edit";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id) {
		roleRepository.delete(id);
		return "redirect:/role";
	}
}
