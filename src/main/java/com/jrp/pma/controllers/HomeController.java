package com.jrp.pma.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeReposiotry;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Value("${version}")
    private String ver;

    @Autowired
    ProjectRepository proRepo;

    @Autowired
    EmployeeReposiotry empRepo;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {

        model.addAttribute("versionNumber", ver);

        Map<String, Object> map = new HashMap<>();

        //we are querying the database for projects
        List<Project> projects = proRepo.findAll();
        model.addAttribute("projectsList",projects);

        List<ChartData> projectData = proRepo.getProjectStatus();

        //Lets convert projectData object into a json structure for use in javascript
        ObjectMapper objectMapper = new ObjectMapper();
       String jsonString =  objectMapper.writeValueAsString(projectData);
       model.addAttribute("projectStatusCnt", jsonString);




        List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
        model.addAttribute("employeesListProjectCnt",employeesProjectCnt);

        return "main/home";
    }


}
