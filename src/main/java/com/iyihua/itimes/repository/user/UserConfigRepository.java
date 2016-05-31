
package com.iyihua.itimes.repository.user;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.iyihua.itimes.model.user.UserConfig;


public interface UserConfigRepository extends CrudRepository<UserConfig, Long> {

	Page<UserConfig> findAll(Pageable pageable);

}
