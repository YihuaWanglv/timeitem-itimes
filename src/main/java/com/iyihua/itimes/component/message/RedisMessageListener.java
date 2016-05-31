package com.iyihua.itimes.component.message;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import com.iyihua.itimes.component.tools.FreeMarkerManager;
import com.iyihua.itimes.service.component.EmailService;
import com.iyihua.model.component.message.EmailMessage;

import freemarker.template.TemplateException;

@Component
public class RedisMessageListener implements MessageListener {

//	MessageSerializer serializer = new MessageSerializer();
	@Autowired
	MessageSerializer serializer;
	@Autowired
	FreeMarkerManager freeMarkerManager;
	@Autowired
	EmailService emailService;
	
	
	@Override
    public void onMessage( final Message message, final byte[] pattern ) {
		RedisMessage obj = (RedisMessage) serializer.deserialize(message.getBody());
		EmailMessage map = (EmailMessage) obj.getData();
		System.out.println( "Message received: " + map.toString());
		
		try {
			String content = freeMarkerManager.initHtml(map.getParams());
			
			emailService.send(new EmailMessage(map.getTo(), map.getSubject(), content, map.getParams()));
		} catch (IOException | TemplateException | MessagingException e) {
			e.printStackTrace();
		}
		
    }
	
}
