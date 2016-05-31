package com.iyihua.itimes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.iyihua.itimes.model.Category;
import com.iyihua.itimes.repository.CategoryRepository;
import com.iyihua.model.base.CategoryDTO;
import com.iyihua.remote.base.CategoryRemote;

@Service
public class CategoryService implements CategoryRemote {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<CategoryDTO> findCategorysByUserId(Long userId) {
		Assert.notNull(userId, "userId can not be null!");
		List<CategoryDTO> result = null;
		List<Category> categories = categoryRepository.findByUserId(userId);
		if (categories != null && categories.size() > 0) {
			result = new ArrayList<CategoryDTO>();
			for (Category category : categories) {
				CategoryDTO c = new CategoryDTO();
				BeanUtils.copyProperties(category, c);
				result.add(c);
			}
		}
		return result;
	}
	
	@Override
	public CategoryDTO saveCategory(CategoryDTO category) {
		Assert.notNull(category, "category not be empty");
		Category save = new Category();
		BeanUtils.copyProperties(category, save);
		Category result = categoryRepository.save(save);
		if (result != null) {
			BeanUtils.copyProperties(result, category);
		}
		return category;
	}
	
	/**
	 * 删除类别
	 * @param categoryId
	 */
	@Override
	public void deleteCategory(Long categoryId) {
		Assert.notNull(categoryId, "categoryId not be empty");
		categoryRepository.delete(categoryId);
	}
}
