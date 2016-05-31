package com.iyihua.itimes.component.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import com.iyihua.model.base.UserDTO;

@Component
public class LoginSessionManager {

	public UserDTO getSessionUser() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null && subject.getPrincipal() != null && subject.getPrincipal() instanceof UserDTO) {
			return (UserDTO) subject.getPrincipal();
		}
		return null;
	}
	
	public Long getSessionUserId() {
		Subject subject = SecurityUtils.getSubject();
		UserDTO user = null;
		if (subject != null && subject.getPrincipal() != null && subject.getPrincipal() instanceof UserDTO) {
			user = (UserDTO) subject.getPrincipal();
			if (user != null) {
				return user.getId();
			}
		}
		return null;
	}
}
