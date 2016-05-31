package com.iyihua.itimes.web.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iyihua.itimes.component.tools.ConfigDataHolder;

//@Component
public class DigitalSignaturesFilter implements Filter {

	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//			String url = httpServletRequest.getRequestURL().toString();
			String uri = httpServletRequest.getRequestURI().toString();
			String queryString = httpServletRequest.getQueryString();
			System.out.println("---in filter RequestUrlFilter----");
			System.out.println("---uri----:" + uri);
			System.out.println("---queryString----:" + queryString);
			if (!isTargetUri(uri, ConfigDataHolder.getDigitalSignaturesIgnorePaths()) && !isDigitalSignaturesAuthentication(request, response)) {
				HttpServletResponse resp = (HttpServletResponse) response;
				resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	private boolean isDigitalSignaturesAuthentication(ServletRequest request, ServletResponse response) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		StringBuilder builder = new StringBuilder();
		Map<String, String[]> params = httpServletRequest.getParameterMap();
		for (String key : params.keySet()) {
			if (!key.equals("__fccy")) {
				if (builder.length()!=0) {
					builder.append("&");
				}
				builder.append(key).append("=").append(params.get(key)[0].toString());				
			}
		}
		String queryString = builder.toString();
		String fccy = MD5(queryString).toUpperCase();
		String __fccy = httpServletRequest.getParameter("__fccy");
		if (__fccy != null && fccy.equals(__fccy)) {
			return true;
		}
		return false;
		
//		return true;
	}

	public String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

	private boolean isTargetUri(String uri, Set<String> ignoreSet) {
		
		for (String filterKey : ignoreSet) {
			if (uri.matches(filterKey)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	public static String testMD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println("----------" + testMD5("iyihua"));
	}
}
