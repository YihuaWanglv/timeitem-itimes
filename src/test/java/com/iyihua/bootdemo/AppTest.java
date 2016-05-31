//package com.iyihua.bootdemo;
//
//
//
//import java.security.NoSuchAlgorithmException;
//import java.security.spec.InvalidKeySpecException;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.iyihua.itimes.App;
//import com.iyihua.itimes.component.tools.UserConfigManager;
//import com.iyihua.itimes.component.tools.singletonManager;
//import com.iyihua.itimes.mapper.user.UserRoleMapper;
//import com.iyihua.itimes.model.user.UserConfig;
//import com.iyihua.itimes.repository.user.UserConfigRepository;
//import com.iyihua.model.base.UserDTO;
//import com.iyihua.model.base.user.UserConfigObject;
//import com.iyihua.remote.base.UserRemote;
//
///**
// * Unit test for simple App.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
//public class AppTest {
//	
//	
//	@Autowired UserConfigManager userConfigManager;
//	
////	@Test
//	public void testRedis() {
////		userConfigManager.setUserConfigToCache(1, userConfigManager.getUserConfig(1l));
//		UserConfigObject obj = userConfigManager.getUserConfigFromCache(1l);
//		System.out.println(singletonManager.getSimpleGson().toJson(obj));
//	}
//	
//	
////	@Autowired UserConfigRepository userConfigRepository;
////	
////	@Test
////	public void test() {
////		
////		UserConfig config = new UserConfig();
////		config.setDetail("{\"test\":\"iyihua\"}");
////		userConfigRepository.save(config);
////	}
//	
////	@Autowired
////	DefaultSecurityManager securityManager;
////	
////	@Test
////	public void TestShiro() {
////		SecurityUtils.setSecurityManager(securityManager);
////		Subject subject = SecurityUtils.getSubject();  
////	    UsernamePasswordToken token = new UsernamePasswordToken("iyihua", "123456");  
////	  
////	    try {  
////	        //4、登录，即身份验证  
////	        subject.login(token);  
////	    } catch (AuthenticationException e) {  
////	        //5、身份验证失败  
////	    }  
////	  
////	    Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录  
////	  
////	    //6、退出  
////	    subject.logout();  
////	}
//	
////	@Autowired
////	UserRoleMapper userRoleMapper;
////	
////	@Test
////	public void TestRole() {
////		
////		List<String> roles = userRoleMapper.findByUserId(1L);
////		if (roles != null && roles.size() > 0) {
////			for (String role : roles) {
////				System.err.println("role= " + role);
////			}
////		} else {
////			System.err.println("null--------------------");
////		}
////	}
////	@Autowired
////	UserRemote userService;
////	@Test
////	public void TestUserSave() throws NoSuchAlgorithmException, InvalidKeySpecException {
////		
////		UserDTO user = new UserDTO();
////		user.setName("test");
////		user.setPassword("123456");
////		user.setType(0);
////		user.setEmail("619361578@qq.com");
////		user.setEnable(1);
////		user.setDeleted(0);
////		user.setMobile("13560427788");
////		userService.createUser(user);
////	}
//}
