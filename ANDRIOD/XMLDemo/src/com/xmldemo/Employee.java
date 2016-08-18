package com.xmldemo;

import java.io.Serializable;

public class Employee implements Serializable {
	String name;
	int age;
	float sal;
	public Employee(String name, int age, float sal) {
		super();
		this.name = name;
		this.age = age;
		this.sal = sal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public float getSal() {
		return sal;
	}
	public void setSal(float sal) {
		this.sal = sal;
	}
	@Override
	public String toString() {
		return name+ ":"+age+":" + sal;
	}
	
	
}
