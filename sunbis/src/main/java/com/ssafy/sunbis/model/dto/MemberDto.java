package com.ssafy.sunbis.model.dto;

public class MemberDto {
	private String id;
	private String pasword;
	private String name;
	private int isadmin;
	private String phone;
	public MemberDto() {
		super();
	}
	public MemberDto(String id, String pasword, String name, int isadmin, String phone) {
		super();
		this.id = id;
		this.pasword = pasword;
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
	public String getPasword() {
		return pasword;
	}
	public void setPasword(String pasword) {
		this.pasword = pasword;
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
		return "MemberDto [id=" + id + ", pasword=" + pasword + ", name=" + name + ", isadmin=" + isadmin + ", phone="
				+ phone + "]";
	}
}
