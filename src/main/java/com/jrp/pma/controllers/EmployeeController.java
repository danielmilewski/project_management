package com.jrp.pma.controllers;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService empService;

    @GetMapping
    public String displayEmployees(Model model){
        List<Employee> employees = empService.getAll();
        model.addAttribute("employees" , employees);
        return "employees/list-employee";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model){

        Employee anEmployee = new Employee();
        model.addAttribute("employee", anEmployee);

        return "employees/new-employee";

    }

    @PostMapping("/save")
    public String createEmployee(Employee employee, Model model){
       // save to the database uising employee crud repository
        empService.save(employee);

        return "redirect:/employees/new";
    }
}
