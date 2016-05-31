
package com.iyihua.itimes.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iyihua.itimes.model.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {

	List<Tag> findByUserId(Long userId);

}
