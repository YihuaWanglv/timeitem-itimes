
package com.iyihua.itimes.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.iyihua.itimes.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Page<User> findAll(Pageable pageable);

	Page<User> findByNameContainingAndTypeContainingAllIgnoringCase(String name,
			Integer type, Pageable pageable);

	User findByNameAndTypeAllIgnoringCase(String name, Integer type);

	User findByName(String name);
}
