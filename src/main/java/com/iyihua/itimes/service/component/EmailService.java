package com.iyihua.itimes.service.component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.iyihua.model.component.message.EmailMessage;

@Component
public class EmailService {

	@Autowired private JavaMailSender javaMailSender;
	
	public void send(EmailMessage message) throws MessagingException {
//		SimpleMailMessage msg = new SimpleMailMessage();
//	    msg.setFrom("619361578@qq.com");
//	    msg.setTo(message.getTo());
//	    msg.setSubject(message.getSubject());
//	    msg.setText(message.getContent());
//		javaMailSender.send(msg);
		
		MimeMessage mm = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mm);
		helper.setTo(message.getTo());
		helper.setFrom("619361578@qq.com");
		helper.setSubject(message.getSubject());
		helper.setText(message.getContent(), true);
		javaMailSender.send(mm);
	}
}
