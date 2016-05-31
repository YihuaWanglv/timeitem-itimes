package com.iyihua.itimes.web.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iyihua.itimes.component.security.SecurityMatchManager;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired private SecurityMatchManager securityMatchManager;
	
	@Value("${project.host}")
	private String host = "localhost";
	
	@RequestMapping(value = "/active")
	public String active(Map<String, Object> model, String code, Integer id) {
		
		boolean match = securityMatchManager.userCodeMatcher(code, id);
//		boolean match = true;
		if (match) {
			model.put("status", 1);
			model.put("message", "Account activation success! Please enjoy yourself at TimeItem!");
			model.put("redirect", "/item.html");
		} else {
			model.put("status", 0);
			model.put("message", "Sorry, but failed to active your account, for the code is not correct!");
		}
		return "info";
	}
}
