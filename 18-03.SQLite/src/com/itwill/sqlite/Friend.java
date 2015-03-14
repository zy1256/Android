package com.itwill.sqlite;

public class Friend {
	private int _id;
	private String name;
	private String phone;
	private String address;
	public Friend() {
		// TODO Auto-generated constructor stub
	}
	public Friend(int _id, String name, String phone, String address) {
		super();
		this._id = _id;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
