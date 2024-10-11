package com.schoolmanagement.bean;

public class Student {
	
	private String sid;
	private String name;
	private int age;
	private String password;
	
	public Student(String sid, String name, int age, String password) {
		super();
		this.sid = sid;
		this.name = name;
		this.age = age;
		this.password = password;
	}

	public String getSid() {
		return sid;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getPassword() {
		return password;
	}
	
	
	
	

}
