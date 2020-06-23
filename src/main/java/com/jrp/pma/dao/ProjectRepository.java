package com.jrp.pma.dao;

import com.jrp.pma.dto.ChartData;
import com.jrp.pma.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    @Override
    public List<Project> findAll();

    @Query(nativeQuery = true, value="Select stage as label, COUNT(*) as value From project Group By stage")
    public List<ChartData> getProjectStatus();
}
