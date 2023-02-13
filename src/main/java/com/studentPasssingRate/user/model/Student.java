package com.studentPasssingRate.user.model;

public class Student {

	private String name;
	private String studentId;
	private String department;
	private int marks;
	private double passingRate;
	
	public Student(String name, String studentId, String department, int marks) {
		super();
		this.name = name;
		this.studentId = studentId;
		this.department = department;
		this.marks = marks;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public double getPassingRate() {
		return passingRate;
	}
	public void setPassingRate(double passingRate) {
		this.passingRate = passingRate;
	}
	
	
}
