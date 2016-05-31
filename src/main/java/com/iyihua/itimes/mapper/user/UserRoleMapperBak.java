//package com.iyihua.itimes.mapper.user;
//
//import java.util.List;
//
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;
//
//public interface UserRoleMapperBak {
//
//	@Select("select r.role_name from user_role ur left join role r on ur.role_id=r.role_id where user_id = #{userId}")
//	List<String> findByUserId(@Param("userId") Long userId);
//}
