package io.anush.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.anush.model.Department;
import io.anush.model.DepartmentList;
import io.anush.model.Employee;
import io.anush.model.EmployeeList;
import io.anush.service.DepartmentService;
import io.anush.service.EmployeeService;



@RestController
@RequestMapping("/Department")
public class DepartmentController {
    
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/GetAll")
	public DepartmentList getAllDepartments(){
		List<Department> lst = departmentService.getAllDepartments();
		DepartmentList departmentList = new DepartmentList();
		departmentList.setDeptList(lst);
	  return departmentList;	
	}
	
	@RequestMapping("/{deptid}")
	public Optional<Department> getDepartment(@PathVariable("deptid") int deptid) {
		return departmentService.getDepartment(deptid);
	}
	
	@PostMapping("/addDept")
	public void addDepartment(@RequestBody Department department) {
		departmentService.addDepartment(department);
	}
	
	@PutMapping("/updateDepartment/{deptid}")
	public void updateDepartment(@RequestBody Department department, @PathVariable int deptid) {
		department.setDeptId(deptid);
		departmentService.updateDepartment(department);
	}
	
	@DeleteMapping("/DeleteDepartment/{deptid}")
	public void deleteDepartment(@PathVariable int deptid) {
		departmentService.deleteDepartment(deptid);
		employeeService.deleteEmployee(deptid);
	}
	
	@GetMapping("{edid}/employees")
	public EmployeeList getAllEmployees(@PathVariable int edid){
		EmployeeList lst =employeeService.getAllEmployees(edid);
	   System.out.println(lst.getEmployeeList().size());
	   return lst;
	}
	
	@GetMapping("/employess/{empid}")
	public Employee getEmployee(@PathVariable int empid) {
		return employeeService.getEmployee(empid);
	}
	
	@PostMapping("/employees/{edid}/addEmployee")
	public void addEmployee(@RequestBody Employee employee , @PathVariable int edid) {
		employee.setEdid(edid);
		employeeService.addEmployee(employee,edid);
	}
	
	@PutMapping("/employees/{edid}/updateEmployee/{empid}")
	public void updateEmployee(@RequestBody Employee employee,@PathVariable int edid, @PathVariable int empid) {
		employee.setEdid(edid);
		employee.setEmpid(empid);
		employeeService.updateEmployee(employee,edid,empid);
	}
	@DeleteMapping("/employees/deleteEmployee/{edid}")
	public void deleteEmployee(@PathVariable int edid) {
		employeeService.deleteEmployee(edid);
	}
	@DeleteMapping("/employees/{edid}/deleteEmployee/{empid}")
	public void deleteSingleEmployee(@PathVariable int edid,@PathVariable int empid) {
		employeeService.deleteSingleEmployee(edid, empid);
	}
	
}

/*
 * import java.util.List; import java.util.Optional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.web.bind.annotation.DeleteMapping; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.PutMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import io.anush.model.Department; import io.anush.model.DepartmentList;
 * import io.anush.model.Employee; import io.anush.service.DepartmentService;
 * import io.anush.service.EmployeeService;
 * 
 * @RestController
 * 
 * @RequestMapping("/Department") public class DepartmentController {
 * 
 * @Autowired private DepartmentService departmentService;
 * 
 * @Autowired private EmployeeService employeeService;
 * 
 * @GetMapping("/dept") public DepartmentList getDepartments() {
 * List<Department> dept = departmentService.getAllDepartments(); DepartmentList
 * list = new DepartmentList(); list.setDepartments(dept); return list;
 * 
 * }
 * 
 * @GetMapping("/listDept") public List<Department> getAllDepartments() { return
 * departmentService.getAllDepartments(); }
 * 
 * @RequestMapping("/{deptId}") public Optional<Department>
 * getDepartment(@PathVariable("deptId") int deptId) { return
 * departmentService.getDepartment(deptId); }
 * 
 * @PostMapping("/addDepartment") public Department addDepartment(@RequestBody
 * Department department) { return departmentService.addDepartment(department);
 * }
 * 
 * @PutMapping("/updateDepartment/{deptId}") public void
 * updateDepartment(@RequestBody Department department, @PathVariable int
 * deptId) { department.setDeptId(deptId);
 * departmentService.updateDepartment(department); }
 * 
 * @DeleteMapping("/deleteDepartment/{deptId}") public void
 * deleteDepartment(@PathVariable int deptId) {
 * departmentService.deleteDepartment(deptId);
 * employeeService.deleteEmployee(deptId); }
 * 
 * @GetMapping("/listEmp") public Employee[] getAllEmployees() { return
 * employeeService.getAllEmployees(); }
 * 
 * @GetMapping("/employees/{empId}") public Employee getEmployee(@PathVariable
 * int empid) { return employeeService.getEmployee(empid); }
 * 
 * @PostMapping("/employees/addEmployee") public void addEmployee(@RequestBody
 * Employee employee) { employeeService.addEmployee(employee); }
 * 
 * @PutMapping("/employees/updateEmployee/{empId}") public void
 * updateEmployee(@RequestBody Employee employee, @PathVariable int empId) {
 * employee.setEmpId(empId); employeeService.updateEmployee(empId, employee); }
 * 
 * @DeleteMapping("/employees/deleteEmployee/{eDid}") public void
 * deleteEmployee(@PathVariable int eDid) {
 * employeeService.deleteEmployee(eDid); }
 * 
 * }
 */