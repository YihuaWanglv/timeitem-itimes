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

package com.iyihua.itimes.config.security.shiro;

import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import com.iyihua.itimes.config.security.CustomCredentialsMatcher;
import com.iyihua.itimes.config.security.MyRealm;
import com.iyihua.itimes.web.filter.DigitalSignaturesFilter;

@Configuration
@EnableConfigurationProperties(ShiroProperties.class)
@Import(ShiroManager.class)
public class ShiroAutoConfiguration {
	@Autowired
	private ShiroProperties properties;

	// @Bean(name = "credentialsMatcher")
	// public CustomCredentialsMatcher credentialsMatcher() {
	// final CustomCredentialsMatcher credentialsMatcher = new
	// CustomCredentialsMatcher();
	// return credentialsMatcher;
	// }

	@Bean(name = "realm")
	@DependsOn("lifecycleBeanPostProcessor")
	@ConditionalOnMissingBean
	public MyRealm realm() {
		Class<?> relmClass = properties.getRealm();
		MyRealm r = (MyRealm) BeanUtils.instantiate(relmClass);
		/**
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		// credentialsMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
		credentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
		r.setCredentialsMatcher(credentialsMatcher);
		*/
		CustomCredentialsMatcher credentialsMatcher = new CustomCredentialsMatcher();
		r.setCredentialsMatcher(credentialsMatcher);
		return r;
	}

	@Bean(name = "shiroFilter")
	@DependsOn("securityManager")
	@ConditionalOnMissingBean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultSecurityManager securityManager, Realm realm) {
		MyRealm myRealm = (MyRealm) realm;
//		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//		credentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
//		myRealm.setCredentialsMatcher(credentialsMatcher);

		securityManager.setRealm(myRealm);

		// 设置跨域session共享,目前支持父域以及其子域下的session共享
//		String enableShare = properties.getEnableShareJsession();
//		if (enableShare != null && enableShare.equals(enableShare)) {
//			DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
//			Cookie c = new SimpleCookie();
//			c.setDomain(properties.getDomain());
//			c.setPath(properties.getPath());
//			c.setName(properties.getShareJsessionKey());
//			c.setHttpOnly(true);
//			sessionManager.setSessionIdCookie(c);
//		}
		
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
//		Map<String, Filter> filters = shiroFilter.getFilters();
// 		filters.put("digital", new DigitalSignaturesFilter());
		shiroFilter.setSecurityManager(securityManager);
		shiroFilter.setLoginUrl(properties.getLoginUrl());
		shiroFilter.setSuccessUrl(properties.getSuccessUrl());
		shiroFilter.setUnauthorizedUrl(properties.getUnauthorizedUrl());
		shiroFilter.setFilterChainDefinitionMap(properties.getFilterChainDefinitions());
		return shiroFilter;
	}
}
