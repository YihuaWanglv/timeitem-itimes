package com.iyihua.itimes.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	@RequestMapping("/login")
	@ResponseBody
	public void login(HttpServletRequest req, HttpServletResponse resp, String username, String password) throws ServletException, IOException {
		Subject subject = SecurityUtils.getSubject();
		String error = null;
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			String sessioid = (String) subject.getSession().getId();
		} catch (UnknownAccountException e) {
			error = "用户名/密码错误";
		} catch (IncorrectCredentialsException e) {
			error = "用户名/密码错误";
		} catch (AuthenticationException e) {
			// 其他错误，比如锁定，如果想单独处理请单独catch处理
			error = "其他错误：" + e.getMessage();
		}
		if (error != null) {// 出错了，返回登录页面
			req.setAttribute("error", error);
			resp.sendRedirect("/forbidden.html");
		} else {// 登录成功
			Cookie cookie = new Cookie("username", username);
			cookie.setPath("/");
			resp.addCookie(cookie);
			resp.sendRedirect("/item.html");// 设置跳转的页面
		}
	}

	@RequestMapping(value = "/logout")
	@ResponseBody
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		
//		Cookie cookie = new Cookie("username", null);
//		cookie.setMaxAge(0);
//		cookie.setPath("/");
//		resp.addCookie(cookie);
		
		delete(req, resp, "username");
		
		resp.sendRedirect("/index.html");
	}
	
	public void delete(HttpServletRequest req, HttpServletResponse resp, String key) {
	    Cookie[] cookies = req.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	        	System.out.println("------" + cookie.getName().toString());
	            if (cookie.getName().toString().equals(key)) {
	                cookie.setValue(null);
	                cookie.setMaxAge(0);
	                cookie.setPath("/");
	                resp.addCookie(cookie);
	            }
	        }
	    }
	}
}
