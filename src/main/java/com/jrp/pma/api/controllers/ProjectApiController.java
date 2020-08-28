package com.jrp.pma.api.controllers;

import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {

    @Autowired
    ProjectRepository proRepo;

    @GetMapping
    public Iterable<Project> getProjects() {

        return proRepo.findAll();
    }

    @GetMapping("{id}")
    public Project getProjectById(@PathVariable("id") Long id) {

        return proRepo.findById(id).get();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@RequestBody Project project) {

        return proRepo.save(project);
    }

    @PutMapping(consumes = "appplication/json")
    @ResponseStatus(HttpStatus.OK)
    public Project update(@RequestBody Project project) {

        return proRepo.save(project);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Project partialUpdate(@PathVariable("id") long id,
                                 @RequestBody Project patchProject) {

        Project pro = proRepo.findById(id).get();

        if (patchProject.getDescription() != null) {
            pro.setDescription(patchProject.getDescription());
        }

        if (patchProject.getEmployees() != null) {
            pro.setEmployees(patchProject.getEmployees());
        }

        if (patchProject.getName() != null) {
            pro.setName(patchProject.getName());
        }

        if (patchProject.getStage() != null) {
            pro.setStage(patchProject.getStage());
        }
        return proRepo.save(pro);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        try {
            proRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

            }
    }

    @GetMapping(params = {"page", "size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Project> findPaginatedProjects(@RequestParam("page") int page,
                                                     @RequestParam("size") int size){
        Pageable pageAndSize = PageRequest.of(page, size);

        return proRepo.findAll(pageAndSize);
    }
}

