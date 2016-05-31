package com.iyihua.itimes.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iyihua.itimes.component.security.LoginSessionManager;
import com.iyihua.model.base.CategoryDTO;
import com.iyihua.model.base.LocationDTO;
import com.iyihua.model.base.ProjectDTO;
import com.iyihua.model.base.TagDTO;
import com.iyihua.model.base.UserDTO;
import com.iyihua.remote.base.CategoryRemote;
import com.iyihua.remote.base.LocationRemote;
import com.iyihua.remote.base.ProjectRemote;
import com.iyihua.remote.base.TagRemote;

@RestController
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	CategoryRemote categoryService;
	@Autowired
	ProjectRemote projectService;
	@Autowired
	LocationRemote locationService;
	@Autowired
	TagRemote tagService;
	
	@Autowired
	LoginSessionManager loginSessionManager;

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public List<CategoryDTO> listCategorys() {
		Long userId = loginSessionManager.getSessionUserId();
		List<CategoryDTO> result = categoryService.findCategorysByUserId(userId);
		return result;
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public CategoryDTO createCategory(@RequestBody CategoryDTO category) {
		Long userId = loginSessionManager.getSessionUserId();
		category.setUserId(userId);
		return categoryService.saveCategory(category);
	}

	@RequestMapping(value = "/category/{categoryId}", method = RequestMethod.PUT)
	public CategoryDTO updateCategory(@RequestBody CategoryDTO updated, @PathVariable Long categoryId) {
		updated.setCategoryId(categoryId);
		return categoryService.saveCategory(updated);
	}

	@RequestMapping(value = "/category/{categoryId}", method = RequestMethod.DELETE)
	public void deleteCategory(@PathVariable Long categoryId) {
		categoryService.deleteCategory(categoryId);
	}
	
	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public List<ProjectDTO> listProjects() {
		Long userId = null;
		UserDTO user = loginSessionManager.getSessionUser();
		if (user != null) {
			userId = user.getId();
		}
		List<ProjectDTO> projects = projectService.findProjectByUserId(userId);
		return projects;
	}

	@RequestMapping(value = "/project", method = RequestMethod.POST)
	public ProjectDTO createProject(@RequestBody ProjectDTO project) {
		Long userId = loginSessionManager.getSessionUserId();
		project.setUserId(userId);
		return projectService.saveProject(project);
	}
	
	@RequestMapping(value = "/project/{projectId}", method = RequestMethod.PUT)
	public ProjectDTO updateProject(@RequestBody ProjectDTO project, @PathVariable Long projectId) {
		project.setProjectId(projectId);
		return projectService.saveProject(project);
	}

	@RequestMapping(value = "/project/{projectId}", method = RequestMethod.DELETE)
	public void deleteProject(@PathVariable Long projectId) {
		projectService.deleteProject(projectId);
	}
	
	@RequestMapping(value = "/location", method = RequestMethod.GET)
	public List<LocationDTO> listLocations() {
		Long userId = loginSessionManager.getSessionUserId();
		List<LocationDTO> result = locationService.findLoationByUserId(userId);
		return result;
	}

	@RequestMapping(value = "/location", method = RequestMethod.POST)
	public LocationDTO createLocation(@RequestBody LocationDTO location) {
		Long userId = loginSessionManager.getSessionUserId();
		location.setUserId(userId);
		return locationService.saveLocation(location);
	}

	@RequestMapping(value = "/location/{locationId}", method = RequestMethod.PUT)
	public LocationDTO updateLocation(@RequestBody LocationDTO updated, @PathVariable Long locationId) {
		updated.setLocationId(locationId);
		return locationService.saveLocation(updated);
	}

	@RequestMapping(value = "/location/{locationId}", method = RequestMethod.DELETE)
	public void deleteLocation(@PathVariable Long locationId) {
		locationService.deleteLoation(locationId);
	}
	
	@RequestMapping(value = "/tag", method = RequestMethod.GET)
	public List<TagDTO> listTags() {
		Long userId = loginSessionManager.getSessionUserId();
		return tagService.findTagsByUserId(userId);
	}
	
	@RequestMapping(value = "/tag", method = RequestMethod.POST)
	public TagDTO createTag(@RequestBody TagDTO tag) {
		Long userId = loginSessionManager.getSessionUserId();
		tag.setUserId(userId);
		return tagService.saveTags(tag);
	}
	
	@RequestMapping(value = "/tag/{tagId}", method = RequestMethod.PUT)
	public TagDTO updateTag(@RequestBody TagDTO tag, @PathVariable Long tagId) {
		tag.setTagId(tagId);
		return tagService.saveTags(tag);
	}
	
	@RequestMapping(value = "/tag/{tagId}", method = RequestMethod.DELETE)
	public void deleteTag(@PathVariable Long tagId) {
		tagService.deleteTags(tagId);
	}
	
}
