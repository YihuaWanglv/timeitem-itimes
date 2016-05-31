
package com.iyihua.itimes.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iyihua.itimes.model.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {

	List<Project> findByUserId(Long userId);

}
