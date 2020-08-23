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

import com.myclass.dto.UserDto;
import com.myclass.service.UserService;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public Object get() {
		List<UserDto> users = userService.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public Object get(@PathVariable("id") int id) {
		try {
			UserDto user = userService.findById(id);
			return new ResponseEntity<>(user, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>("Not found", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	public Object add(@RequestBody UserDto dto) {
		try {
			userService.add(dto);
			return new ResponseEntity<String>("Success", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Fail", HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("")
	public Object update(@RequestBody UserDto dto) {
		try {
			userService.update(dto);
			return new ResponseEntity<String>("Update Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Update Fail", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public Object delete(@PathVariable("id") int id) {
		try {
			userService.delete(id);
			return new ResponseEntity<String>("Delete Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Delete Fail", HttpStatus.BAD_REQUEST);
		}
	}
}
