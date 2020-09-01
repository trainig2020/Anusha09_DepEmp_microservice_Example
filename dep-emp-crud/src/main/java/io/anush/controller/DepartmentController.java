package io.anush.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class DepartmentController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/DeptList")
	public ModelAndView getAllDepartments(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("In Controller");
		DepartmentList deptlist = restTemplate.getForObject("http://gateway-service/Department/GetAll",
				DepartmentList.class);
		System.out.println(deptlist.getDeptList().get(0));
		List<Department> lstdept = new ArrayList<>();

		for (int i = 0; i < deptlist.getDeptList().size(); i++) {
			lstdept.add(deptlist.getDeptList().get(i));
		}
		for (Department department : lstdept) {
			System.out.println(department.getDeptid());
		}
		HttpSession session = request.getSession();
		session.setAttribute("DeptList", lstdept);
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("DeptList", lstdept);
		modelAndView.addObject("homepage", "main");
		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/NewDepartment", method = RequestMethod.GET)
	public ModelAndView newDepartment(HttpServletRequest request) {
		String Register = "newform";
		HttpSession session1 = request.getSession();
		List<Department> lst = (List<Department>) session1.getAttribute("DeptList");
		session1.setAttribute("DeptList", lst);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("Register", Register);
		modelAndView.addObject("createdept", "newdept");
		modelAndView.setViewName("home");
		Department department = new Department();
		modelAndView.addObject("department", department);
		return modelAndView;
	}

	@RequestMapping(value = "/CreateDepartment", method = RequestMethod.POST)
	public ModelAndView insertDepartment(@ModelAttribute Department department) {
		restTemplate.postForObject("http://gateway-service/Department/AddDepartment", department, Department.class);
		return new ModelAndView("redirect:/DeptList");
	}

	@RequestMapping(value = "/UpdateDepartment", method = RequestMethod.POST)
	public ModelAndView updateDepartment(@ModelAttribute Department department, HttpServletRequest request) {
		int deptid = Integer.parseInt(request.getParameter("deptid"));
		restTemplate.put("http://gateway-service/Department/updateDepartment/" + deptid, department);
		return new ModelAndView("redirect:/DeptList");
	}

	@RequestMapping(value = "/DeleteDepartment", method = RequestMethod.GET)
	public ModelAndView deleteDepartment(HttpServletRequest request) {
		int deptid = Integer.parseInt(request.getParameter("deptid"));
		restTemplate.delete("http://gateway-service/Department/DeleteDepartment/" + deptid);
		return new ModelAndView("redirect:/DeptList");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/GetDepartment", method = RequestMethod.GET)
	public ModelAndView getDepartmentId(HttpServletRequest request) {
		int deptid = Integer.parseInt(request.getParameter("deptid"));
		HttpSession session2 = request.getSession();
		List<Department> lst = (List<Department>) session2.getAttribute("DeptList");
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("DeptList", lst);
		modelAndView.addObject("departmentid", deptid);
		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/showdepartments", method = RequestMethod.GET)
	public ModelAndView showDepartments(HttpServletRequest request) {
		HttpSession session3 = request.getSession();
		List<Department> lstdept1 = (List<Department>) session3.getAttribute("DeptList");
		session3.setAttribute("DeptListemp", lstdept1);
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("DeptListemp", lstdept1);
		int deptid = lstdept1.get(0).getDeptid();
		modelAndView.addObject("name", "names");
		return new ModelAndView("redirect:/emplist?deptid=" + deptid);
	}



}