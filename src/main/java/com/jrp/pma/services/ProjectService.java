package com.jrp.pma.services;

import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectService {


    @Autowired
    ProjectRepository proRepo;

    public Project save(Project project){
        return proRepo.save(project);
    }

    public List<Project> getAll() {
        return proRepo.findAll();
    }

    public List<ChartData> getProjectStatus(){
        return proRepo.getProjectStatus();
    }


}
