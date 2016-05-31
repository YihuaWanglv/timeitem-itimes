/*
 *    Copyright 2010-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.iyihua.itimes.mapper.user;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iyihua.itimes.model.User;

/**
 * 
 * @ClassName: UserXmlMapper
 * @Description: 使用xml sql的方式操作User，提供能只能灵活的方式
 * @author: wanglvyh@cf-ec.com
 * @date: 2016年3月3日 下午5:43:42
 */
@Component
public class UserXmlMapper {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int updateUserSelective(User user) {
		return this.sqlSessionTemplate.update("updateUserSelective", user);
	}
	
	
}
