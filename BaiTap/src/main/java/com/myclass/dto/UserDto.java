package com.myclass.dto;

public class UserDto {
	private int id;
	private String fullname;
	private String email;
	private String roleName;
	private String password;
	private String avatar;
	private int roleId;
	public UserDto(int id, String fullname, String email, String roleName, String password, String avatar, int roleId) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.roleName = roleName;
		this.password = password;
		this.avatar = avatar;
		this.roleId = roleId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public UserDto() {
		super();
	}
	public UserDto(int id, String fullname, String email, String roleName) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.roleName = roleName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
	
}
