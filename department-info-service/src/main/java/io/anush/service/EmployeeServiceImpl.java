package io.anush.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.anush.model.Employee;
import io.anush.model.EmployeeList;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public EmployeeList getAllEmployees(int edid) {
		EmployeeList lstemp = restTemplate.getForObject("http://employee-info-Service:8082/employees/GetAll/" + edid,
				EmployeeList.class);
		return lstemp;
	}

	@Override
	public Employee getEmployee(int empid) {
		Employee employee = restTemplate.getForObject("http://employee-info-Service:8082/employees/" + empid,
				Employee.class);
		return employee;
	}

	@Override
	public Employee addEmployee(Employee employee, int edid) {
		return restTemplate.postForObject("http://employee-info-Service:8082/employees/" + edid + "/addEmployee",
				employee, Employee.class);

	}

	@Override
	public void updateEmployee(Employee employee, int edid, int empid) {
		restTemplate.put("http://employee-info-Service:8082/employees/" + edid + "/updateEmployee/" + empid, employee);
	}

	@Override
	public void deleteEmployee(int edid) {
		restTemplate.delete("http://employee-info-Service:8082/employees/deleteEmployee/" + edid, edid);

	}

	@Override
	public void deleteSingleEmployee(int edid, int empid) {
		restTemplate.delete("http://employee-info-Service:8082/employees/deleteAll/" + edid + "/" + empid);

	}

}
