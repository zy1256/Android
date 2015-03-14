package com.itwill.intrentlocalappcall;

import java.io.Serializable;

public class Student implements Serializable {
	public int no;
	
	public String name;
	public String address;
	public Student() {
		// TODO Auto-generated constructor stub
	}
	public Student(int no, String name, String address) {
		super();
		this.no = no;
		this.name = name;
		this.address = address;
	}
	
}
