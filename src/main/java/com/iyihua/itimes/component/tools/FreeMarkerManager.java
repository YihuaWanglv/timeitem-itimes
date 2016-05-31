package com.iyihua.itimes.component.tools;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Component
public class FreeMarkerManager {

	@Autowired
	Configuration configuration;

	public String initHtml(Map<String, Object> data) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {

		// prepare data
//		data.put("name", "Iyihua");
//		data.put("message", "Hello world!");

		// get template
		Template t = configuration.getTemplate("email/user-active.html");

		String readyParsedTemplate = FreeMarkerTemplateUtils.processTemplateIntoString(t, data);

		return readyParsedTemplate;

	}
}
