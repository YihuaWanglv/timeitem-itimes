package com.iyihua.itimes.web.controller.user;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import util.EmailUtil;

import com.iyihua.itimes.component.message.RedisMessage;
import com.iyihua.itimes.component.message.RedisPublisher;
import com.iyihua.itimes.component.security.LoginSessionManager;
import com.iyihua.itimes.web.dto.UserAccountSaveDTO;
import com.iyihua.itimes.web.dto.UserSaveResultDTO;
import com.iyihua.itimes.web.dto.UserViewDTO;
import com.iyihua.model.base.UserDTO;
import com.iyihua.model.component.JsonObject;
import com.iyihua.model.component.message.EmailMessage;
import com.iyihua.remote.base.UserRemote;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired private UserRemote userService;
	@Autowired private LoginSessionManager loginSessionManager;
	@Autowired private RedisPublisher redisPublisher;
	
	
	@Value("${project.host}")
	private String host = "localhost";

	@RequestMapping(method = RequestMethod.GET)
	public List<UserViewDTO> listUsers() {
		
//		userService.
		return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public JsonObject createUser(UserAccountSaveDTO user) {
		JsonObject json = new JsonObject();
		UserDTO save = new UserDTO();
		BeanUtils.copyProperties(user, save);
		save.setEnable(0);
		save.setDeleted(0);
		save.setType(0);
		String code = UUID.randomUUID().toString();
		save.setCode(code);
		try {
			UserDTO saved = userService.createUser(save);
			if (saved.getId() != null) {
				sendAccountValidationEmail(saved, code);
				json.setData(new UserSaveResultDTO(saved.getId(), saved.getName(), saved.getEmail(), EmailUtil.getEmailIndexFromUserEmail(saved.getEmail())));
			} else {
				json.setStatus(0);
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			json.setStatus(0);
			json.setMessage("operation failed! " + e.getMessage());
		}
		return json;
	}
	
	private void sendAccountValidationEmail(UserDTO saved, String code) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", saved.getId());
		map.put("username", saved.getName());
		map.put("url", initUrl(saved, code));
		EmailMessage email = new EmailMessage(saved.getEmail(), "Confirm your email to complete the registration of TimeItem", null,  map);
		RedisMessage message = new RedisMessage(0, "", email);
		redisPublisher.publish(new ChannelTopic("pubsub:queue"), message);
	}

	private String initUrl(UserDTO saved, String code) {
		return "http://"+host+":8080/account/active?code="+code+"&id="+saved.getId();
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test() {
		System.err.println(host);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public JsonObject updateUser(@RequestBody UserAccountSaveDTO user, @PathVariable Long userId) {
		JsonObject json = new JsonObject();
		UserDTO save = new UserDTO();
		BeanUtils.copyProperties(user, save);
		save.setId(userId);
		try {
			userService.updateUser(save, false);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			json.setStatus(0);
			json.setMessage("update failed! " + e.getMessage());
		}
		return json;
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
	}
	
}
