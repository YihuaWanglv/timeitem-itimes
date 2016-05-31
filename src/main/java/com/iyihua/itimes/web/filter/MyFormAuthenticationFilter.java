package com.iyihua.itimes.web.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 继承shiro原有默认认证filter，重写onAccessDenied方法，添加redictUrl参数到跳转loginUrl的后面
 * @author wanglvyihua
 *
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	private static final Logger log = LoggerFactory.getLogger(MyFormAuthenticationFilter.class);
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }

            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }
	
	protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        saveRequest(request);
        redirectToLogin(request, response);
    }
	
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        String loginUrl = getLoginUrl();
        HttpServletRequest httprequest = (HttpServletRequest)request;
        String url = httprequest.getRequestURL().toString();
        log.info("-----------------" + url);
        loginUrl += "?redirectUrl=" + url;
        WebUtils.issueRedirect(request, response, loginUrl);
    }
}
