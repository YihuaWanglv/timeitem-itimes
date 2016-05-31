package com.iyihua.itimes.web.controller.user;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iyihua.itimes.component.security.LoginSessionManager;
import com.iyihua.itimes.component.tools.UserConfigManager;
import com.iyihua.model.base.report.ReportConfig;
import com.iyihua.model.base.user.UserConfigObject;
import com.iyihua.model.component.JsonObject;


@RestController
//@Controller
@RequestMapping("/data/user")
public class UserDataController {
	
	@Autowired private LoginSessionManager loginSessionManager;
	@Autowired private UserConfigManager userConfigManager;

	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public UserConfigObject config() {
		Long userId = loginSessionManager.getSessionUserId();
		return userConfigManager.getUserConfig(userId);
	}
	
	@RequestMapping(value = "/config/chart/update", method = RequestMethod.POST)
	@SuppressWarnings("rawtypes")
	public JsonObject updateChartConfig(String key, Integer enable, String title, String type) {
		JsonObject json = new JsonObject();
		Long userId = loginSessionManager.getSessionUserId();
		UserConfigObject uco = userConfigManager.getUserConfig(userId);
		List<ReportConfig> reports = uco.getReportConfigs();
		for (Iterator config = reports.iterator(); config.hasNext();) {
			ReportConfig reportConfig = (ReportConfig) config.next();
			if (reportConfig.getKey().equals(key)) {
				if (enable != null) {
					reportConfig.setEnabled(enable);					
				}
				if (title != null) {
					reportConfig.setTitle(title);
				}
				if (type != null) {
					reportConfig.setType(type);
				}
			}
		}
		try {
			userConfigManager.saveUserConfig(userId, uco);			
		} catch (Exception e) {
			e.printStackTrace();
			json.setStatus(0);
			json.setMessage(e.getMessage());
		}
		return json;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public JsonObject test() {
		
		return new JsonObject();
	}
}
