package com.iyihua.itimes.component.tools;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iyihua.itimes.config.redis.RedisManager;
import com.iyihua.itimes.config.redis.SerializeUtils;
import com.iyihua.model.base.user.UserConfigObject;
import com.iyihua.remote.base.UserRemote;

@Component
public class UserConfigManager {

	@Autowired private UserRemote userService;
	// @Autowired private RedisService redisService;
	@Autowired private RedisManager redisManager;

	public static final String REDIS_KEY_PREFIX_USER_CONFIG = "config:user:";
	public static final int REDIS_CONFIG_DEFAULT_EXPIRE_TIME = 86400;

	private byte[] getByteKey(Serializable id) {
		String preKey = REDIS_KEY_PREFIX_USER_CONFIG + id;
		return preKey.getBytes();
	}

	public UserConfigObject getUserConfigFromCache(long userId) {
		UserConfigObject obj = null;
		byte[] data = redisManager.get(getByteKey(userId));
		if (data != null && data.length > 0) {
			obj = (UserConfigObject) SerializeUtils.deserialize(data);
		}
		return obj;
	}

	public void setUserConfigToCache(long userId, UserConfigObject obj) {
		redisManager.set(getByteKey(userId), SerializeUtils.serialize(obj), REDIS_CONFIG_DEFAULT_EXPIRE_TIME);
	}

	public UserConfigObject getUserConfig(long id) {
		UserConfigObject uco = getUserConfigFromCache(id);
		if (uco == null) {
			uco = userService.getUserConfigById(id);
			setUserConfigToCache(id, uco);
		}
		return uco;
	}

	public void saveUserConfig(long userId, UserConfigObject uco) {
		
		userService.saveUserConfig(userId, uco);
		if (getUserConfigFromCache(userId) != null) {
			setUserConfigToCache(userId, uco);
		}
	}
}
