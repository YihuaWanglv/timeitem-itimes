package com.iyihua.itimes.mapper.user;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper {

//	@Select("select r.role_name from user_role ur left join role r on ur.role_id=r.role_id where user_id = #{userId}")
//	List<String> findByUserId(@Param("userId") Long userId);
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<String> findByUserId(Long userId) {
		return this.sqlSessionTemplate.selectList("findByUserId", userId);
	}
	
}
