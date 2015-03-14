package com.itwill.intent.result;

import java.io.Serializable;

public class Photo implements Serializable{
	public int id;
	public String name;
	public Photo() {
		// TODO Auto-generated constructor stub
	}
	public Photo(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
}
