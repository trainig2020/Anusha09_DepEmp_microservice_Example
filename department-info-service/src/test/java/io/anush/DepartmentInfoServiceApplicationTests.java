package io.anush;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import io.anush.model.Department;
import io.anush.repository.DepartmentRepository;
import io.anush.service.DepartmentService;
import io.anush.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentInfoServiceApplicationTests {

	@Autowired
	private DepartmentService departmentService;

	@MockBean
	private DepartmentRepository departmentRepo;

	@Autowired
	private RestTemplate restTemplate;

	@MockBean
	private EmployeeService employeeService;

	@Test
	public void getAllDepartmentsTests() {
		when(departmentRepo.findAll()).thenReturn(
				Stream.of(new Department(12, "ECE"), new Department(2, "CSE")).collect(Collectors.toList()));
		assertEquals(2, departmentService.getAllDepartments().size());
	}

	@Test
	public void addDepartmentTests() {
		Department department = new Department(1, "ECE");
		when(departmentRepo.save(department)).thenReturn(department);
		assertEquals(department, departmentService.addDepartment(department));
	}

	@Test
	public void updateDepartment() {
		Department department = new Department(1, "ECE");
		department.setDeptName("updated ECE");
		when(departmentRepo.save(department)).thenReturn(department);
		assertEquals(department, departmentService.updateDepartment(department));
	}

	@Test
	public void deleteDepartment() {
		Department department = new Department(1, "ECE");
		verify(departmentRepo, times(0)).deleteById(department.getDeptId());
	}

}
