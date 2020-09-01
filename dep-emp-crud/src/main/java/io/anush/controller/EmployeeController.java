package io.anush.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import io.anush.model.Department;
import io.anush.model.DepartmentList;
import io.anush.model.Employee;
import io.anush.model.EmployeeList;

@RestController
public class EmployeeController {

	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/emplist")
	public ModelAndView getAllEmployees(HttpServletRequest request) {
		int deptid = Integer.parseInt(request.getParameter("deptid"));
		List<Employee> lstemp = new ArrayList<>();
		EmployeeList lst = restTemplate.getForObject("http://gateway-service/Department/" + deptid + "/employees",
				EmployeeList.class);
		for (int i = 0; i < lst.getEmployeeList().size(); i++) {
			lstemp.add(lst.getEmployeeList().get(i));
		}
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("EmpList", lstemp);
		List<Department> lstdept1 = (List<Department>) httpSession.getAttribute("DeptList");
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("DeptListemp", lstdept1);
		modelAndView.addObject("EmpList", lstemp);
		modelAndView.addObject("homepage", "emppage");
		modelAndView.addObject("name", "names");
		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
	public ModelAndView newContact(HttpServletRequest request) {
		String Register = "NewForm";
		HttpSession session1 = request.getSession();
		List<Employee> lst = (List<Employee>) session1.getAttribute("EmpList");
		ModelAndView model = new ModelAndView("home");
		model.addObject("EmpList", lst);
		model.addObject("Register", Register);
		model.addObject("insertEmployee", "newemployee");
		model.addObject("homepage", "emppage");
		return model;
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute Employee employee, HttpServletRequest request) {
		int edid = Integer.parseInt(request.getParameter("edid"));
		restTemplate.postForObject("http://gateway-service/Department/employees/" + edid + "/addEmployee", employee,
				Employee.class);
		return new ModelAndView("redirect:/emplist?deptid=" + edid);
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		int edid = Integer.parseInt(request.getParameter("did"));
		restTemplate.delete("http://gateway-service/Department/employees/" + edid + "/deleteEmployee/" + employeeId);
		return new ModelAndView("redirect:/emplist?deptid=" + edid);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getEmployee", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		int did = Integer.parseInt(request.getParameter("did"));
		HttpSession session2 = request.getSession();
		List<Employee> lst = (List<Employee>) session2.getAttribute("EmpList");
		session2.setAttribute("EmpList", lst);
		ModelAndView model = new ModelAndView("home");
		model.addObject("homepage", "emppage");
		model.addObject("EmpList", lst);
		model.addObject("employeeid", employeeId);
		model.addObject("Did", did);
		return model;
	}

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	public ModelAndView updateEmployee(@ModelAttribute Employee employee, HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("empid"));
		int did = Integer.parseInt(request.getParameter("edid"));

		restTemplate.put("http://gateway-service/Department/employees/" + did + "/updateEmployee/" + employeeId,
				employee);
		return new ModelAndView("redirect:/emplist?deptid=" + did);
	}

}