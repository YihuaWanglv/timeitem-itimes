package com.iyihua.itimes.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.iyihua.itimes.component.tools.singletonManager;
import com.iyihua.itimes.mapper.user.UserXmlMapper;
import com.iyihua.itimes.model.User;
import com.iyihua.itimes.model.user.UserConfig;
import com.iyihua.itimes.repository.UserRepository;
import com.iyihua.itimes.repository.user.UserConfigRepository;
import com.iyihua.model.base.UserDTO;
import com.iyihua.model.base.user.UserConfigObject;
import com.iyihua.remote.base.UserRemote;

import util.PasswordSecureHash;

@Service
public class UserService implements UserRemote {

	@Autowired private UserRepository userRepository;
	@Autowired private UserXmlMapper userXmlMapper;
	@Autowired private UserConfigRepository userConfigRepository;
	
	@Override
	public UserDTO findUserById(Long id) {
		Assert.notNull(id, "userId can not be null!");
		UserDTO result = null;
		User user = userRepository.findOne(id);
		if (user != null) {
			result = new UserDTO();
			BeanUtils.copyProperties(user, result);
//			UserConfig uc = userConfigRepository.findOne(user.getUserConfigId());
		}
		return result;
	}

	@Override
	public UserDTO createUser(UserDTO user) throws NoSuchAlgorithmException, InvalidKeySpecException {
		Assert.notNull(user, "User can not be null!");
		User save = new User();
		BeanUtils.copyProperties(user, save);
		String salt = PasswordSecureHash.createRandom();
		save.setPassword(PasswordSecureHash.hashEncrypt(user.getPassword(), salt));
		save.setSalt(salt);
		save.setCode(DigestUtils.sha1DigestAsHex(user.getCode()+salt));
		save = userRepository.save(save);
		BeanUtils.copyProperties(save, user);
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);
	}

	@Override
	public UserDTO findUserByName(String username) {
		UserDTO result = null;
		User user = userRepository.findByName(username);
		if (user != null) {
			result = new UserDTO();
			BeanUtils.copyProperties(user, result);
		}
		return result;
	}


	@Override
	public UserDTO updateUser(UserDTO user, Boolean isUpdateSelected) throws NoSuchAlgorithmException, InvalidKeySpecException {
		Assert.notNull(user, "User can not be null!");
		User save = new User();
		BeanUtils.copyProperties(user, save);
		if (isUpdateSelected != null && isUpdateSelected) {
			int r = userXmlMapper.updateUserSelective(save);
			if (r <= 0) {
				return null;
			}
		} else {
			save = userRepository.save(save);
		}
		return user;
	}

	@Override
	public UserConfigObject getUserConfigById(long id) {
		
		UserConfig userConfig = userConfigRepository.findOne(id);
		Assert.notNull(userConfig.getDetail(), "There is no config of this user!");
		
		return singletonManager.getSimpleGson().fromJson(userConfig.getDetail(), UserConfigObject.class);
	}

	@Override
	public void saveUserConfig(long userId, UserConfigObject uco) {
		UserConfig uc = userConfigRepository.findOne(userId);
		uc.setDetail(singletonManager.getSimpleGson().toJson(uco));
		userConfigRepository.save(uc);
	}

	

}
