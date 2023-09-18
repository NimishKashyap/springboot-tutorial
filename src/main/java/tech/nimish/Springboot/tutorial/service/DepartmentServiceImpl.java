package tech.nimish.Springboot.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.nimish.Springboot.tutorial.entity.Department;
import tech.nimish.Springboot.tutorial.error.DepartmentNotFoundException;
import tech.nimish.Springboot.tutorial.repository.DepartmentRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long id) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(id);

        if(!department.isPresent()) {
            throw new DepartmentNotFoundException("Department Not Found");
        }

        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        Department existing = departmentRepository.findById(id).get();
        System.out.println(existing);

        if(Objects.nonNull(department.getName()) && !"".equalsIgnoreCase(department.getName())) {
            existing.setName(department.getName());
        }
        if(Objects.nonNull(department.getCode()) && !"".equalsIgnoreCase(department.getCode())) {
            existing.setCode(department.getCode());
        }
        if(Objects.nonNull(department.getAddress()) && !"".equalsIgnoreCase(department.getAddress())) {
            existing.setAddress(department.getAddress());
        }


        return departmentRepository.save(existing);
    }

    @Override
    public Department fetchDepartmentByName(String name) {
        return departmentRepository.findByNameIgnoreCase(name);
    }


}
