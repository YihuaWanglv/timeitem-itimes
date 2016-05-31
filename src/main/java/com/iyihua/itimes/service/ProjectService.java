package com.iyihua.itimes.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.iyihua.itimes.model.Project;
import com.iyihua.itimes.repository.ProjectRepository;
import com.iyihua.model.base.ProjectDTO;
import com.iyihua.remote.base.ProjectRemote;

@Service
public class ProjectService implements ProjectRemote {

	private static final Logger log = Logger.getLogger(ProjectService.class);
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Override
//	@Cacheable(value = "findProjectByUserId", cacheManager = "redisCacheManager")
	public List<ProjectDTO> findProjectByUserId(Long userId) {
		Assert.notNull(userId, "userId can not be null!");
		List<ProjectDTO> result = new ArrayList<ProjectDTO>();
		log.info("not in cahe? now find data in db...");
		List<Project> projects = projectRepository.findByUserId(userId);
		if (projects != null && projects.size() > 0) {
			for (Project project : projects) {
				ProjectDTO dto = new ProjectDTO();
				BeanUtils.copyProperties(project, dto);
				result.add(dto);
			}
		}
		return result;
	}

	@Override
//	@CacheEvict(value = "findProjectByUserId", allEntries = true)
	public ProjectDTO saveProject(ProjectDTO project) {
		
		Project save = new Project();
		BeanUtils.copyProperties(project, save);
		save = projectRepository.save(save);
		BeanUtils.copyProperties(save, project);
		return project;
	}

//	@CacheEvict(value = "findProjectByUserId" , allEntries = true)
	@Override
	public void deleteProject(Long projectId) {
		projectRepository.delete(projectId);
	}

}
