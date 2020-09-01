package com.jrp.pma.services;

import com.jrp.pma.dao.EmployeeReposiotry;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeReposiotry empRepo;

    public Employee save(Employee employee){
        return empRepo.save(employee);
    }

    public Iterable<Employee> getAll() {
        return empRepo.findAll();
    }

    public List<EmployeeProject> employeeProjects(){
        return empRepo.employeeProjects();
    }

    public Employee findByEmployeeId(long theId) {
        return empRepo.findByEmployeeId(theId);
    }

    public  void delete(Employee theEmp) {
        empRepo.delete(theEmp);
    }
}
