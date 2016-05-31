package com.iyihua.itimes.component.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.iyihua.model.base.UserDTO;
import com.iyihua.remote.base.UserRemote;

@Component
public class SecurityMatchManager {

	@Autowired private UserRemote userService;
	
	public boolean userCodeMatcher(String code, long id) {
		Assert.noNullElements(new Object[]{code , id}, "code and id must not be null.");
		UserDTO user = userService.findUserById(id);
		String hash = DigestUtils.sha1DigestAsHex(code+user.getSalt());
		if (hash.equals(user.getCode())) {
			return true;
		}
		return false;
	}
}
