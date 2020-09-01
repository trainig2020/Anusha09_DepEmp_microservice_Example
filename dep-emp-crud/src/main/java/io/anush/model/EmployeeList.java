package io.anush.model;

import java.util.List;

public class EmployeeList {
	private List<Employee> employeeList;

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public EmployeeList(List<Employee> employeeList) {
		super();
		this.employeeList = employeeList;
	}
	public EmployeeList() {
		super();
	}

}

