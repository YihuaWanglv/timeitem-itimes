package com.iyihua.itimes.config.security.shiro;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

public class AppSessionManager extends DefaultWebSessionManager {

	@Override
	public Serializable getSessionId(SessionKey key) {
		// 从 cookies 和 url 中获取 sessionid.
		Serializable id = super.getSessionId(key);

		// 如果没有，则从 header 中获取 id.
		if (id == null && WebUtils.isWeb(key)) {
			ServletRequest request = WebUtils.getRequest(key);
			ServletResponse response = WebUtils.getResponse(key);

			if (request instanceof HttpServletRequest) {
				id = ((HttpServletRequest) request).getHeader(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
				if (id == null) {
					id = ((HttpServletRequest) request).getParameter("token");					
				}
			}
		}
		return id;
	}
}
