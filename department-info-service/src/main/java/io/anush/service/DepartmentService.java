package io.anush.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.anush.model.Department;
import io.anush.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	public Optional<Department> getDepartment(int deptId) {
		return departmentRepository.findById(deptId);

	}

	public Department addDepartment(Department department) {
		return departmentRepository.save(department);
	}

	public Department updateDepartment(Department department) {
		return departmentRepository.save(department);
	}

	public void deleteDepartment(int deptId) {
		departmentRepository.deleteById(deptId);
	}

}
