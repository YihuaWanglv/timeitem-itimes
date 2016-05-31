
package com.iyihua.itimes.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iyihua.itimes.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	public List<Category> findByUserId(Long userId);

}
