
package com.iyihua.itimes.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iyihua.itimes.model.Location;

public interface LocationRepository extends CrudRepository<Location, Long> {

	List<Location> findByUserId(Long userId);

}
