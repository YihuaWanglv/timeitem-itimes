package com.iyihua.itimes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

//@EnableAutoConfiguration
@SpringBootApplication
//@ImportResource("classpath:dubbo_client.xml")
//@PropertySource("classpath:common.properties")
public class App 
{
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}
}
