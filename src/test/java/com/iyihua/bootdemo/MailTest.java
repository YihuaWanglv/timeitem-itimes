//package com.iyihua.bootdemo;
//
//
//
//import java.util.concurrent.CountDownLatch;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.iyihua.itimes.App;
//import com.iyihua.itimes.component.message.RedisPublisher;
//
///**
// * Unit test for simple App.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
//public class MailTest {
//
//	
////	@Autowired private JavaMailSender javaMailSender;
////
////	@Test
////	public void send() {
////		SimpleMailMessage msg = new SimpleMailMessage();
////	    msg.setFrom("619361578@qq.com");
////	    msg.setTo("wanglvyh@cf-ec.com");
////	    msg.setSubject("testemail");
////	    msg.setText("test");
////		javaMailSender.send(msg);
////    }
//	
////	@Autowired
////	StringRedisTemplate stringRedisTemplate;
////	@Autowired
////	CountDownLatch latch;
//	
//	@Autowired RedisPublisher redisPublisher;
//	
////	@Test
//	public void MessageTest() {
////		System.err.println("Sending message...");
////		stringRedisTemplate.convertAndSend("chat", "Hello from Redis!");
////		
////		
////		stringRedisTemplate.convertAndSend("email", "Send email to wanglvyh!");
//		
////		redisPublisher.publish();
//		
//	}
//
//}
