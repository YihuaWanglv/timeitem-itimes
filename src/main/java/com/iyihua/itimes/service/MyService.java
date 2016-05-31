package com.iyihua.itimes.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.iyihua.itimes.model.Category;
import com.iyihua.itimes.model.User;
import com.iyihua.itimes.repository.CategoryRepository;
import com.iyihua.itimes.repository.UserRepository;

@Service
public class MyService {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    
    public List<Map<String, Object>> getDemo() {
    	List<Map<String, Object>> r = jdbcTemplate.queryForList("select * from user;");
    	return r;
    }
    
    public User getUser(String name, Integer type) {
		Assert.notNull(name, "Name must not be null");
		return this.userRepository.findByNameAndTypeAllIgnoringCase(name, type);
	}
    
    public List<Category> listCategory() {
    	return (List<Category>) categoryRepository.findAll();
    }
}
