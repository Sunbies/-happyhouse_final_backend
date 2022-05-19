package com.ssafy.sunbis.model.dto;

public class MemberDto {
	private String id;
	private String password;
	private String name;
	private int isadmin;
	private String phone;
	public MemberDto() {
		super();
	}
	public MemberDto(String id, String password, String name, int isadmin, String phone) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.isadmin = isadmin;
		this.phone = phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(int isadmin) {
		this.isadmin = isadmin;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", password=" + password + ", name=" + name + ", isadmin=" + isadmin + ", phone="
				+ phone + "]";
	}
}
