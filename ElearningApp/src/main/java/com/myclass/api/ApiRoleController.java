package com.myclass.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.RoleDto;
import com.myclass.service.RoleService;

@RestController
@RequestMapping("api/role")
public class ApiRoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping("")
	public Object get() {
		List<RoleDto> roles = roleService.findAll();
		if (roles.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public Object get(@PathVariable("id") int id) {
		try {
			RoleDto role = roleService.findById(id);
			return new ResponseEntity<>(role, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>("Not found", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	public Object add(@RequestBody RoleDto dto) {
		try {
			roleService.add(dto);
			return new ResponseEntity<String>("Success", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Fail", HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("")
	public Object update(@RequestBody RoleDto dto) {
		try {
			roleService.update(dto);
			return new ResponseEntity<String>("Update Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Update Fail", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public Object delete(@PathVariable("id") int id) {
		try {
			roleService.delete(id);
			return new ResponseEntity<String>("Delete Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Delete Fail", HttpStatus.BAD_REQUEST);
		}
	}

}
