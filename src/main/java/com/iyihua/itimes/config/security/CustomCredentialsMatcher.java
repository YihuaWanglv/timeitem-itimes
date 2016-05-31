package com.iyihua.itimes.config.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import util.PasswordHash;
import util.PasswordSecureHash;

import com.iyihua.model.component.CredentialsInfoHolder;

//@Component
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
//		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
//
//		Object tokenCredentials = encrypt(String.valueOf(token.getPassword()));
//		Object accountCredentials = getCredentials(info);
//		// 将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
//		return equals(tokenCredentials, accountCredentials);
		
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		CredentialsInfoHolder accountCredentials = (CredentialsInfoHolder)getCredentials(info);
		String salt = accountCredentials.getSalt();
		String password = accountCredentials.getPassword();
		Object tokenCredentials = superEncrypt(String.valueOf(token.getPassword()), salt);
		// 将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
		return equals(tokenCredentials, password);
	}

	// 将传进来密码加密方法
//	private String encrypt(String data) {
//		String sha384Hex = new Sha384Hash(data).toBase64();
//		System.out.println(data + ":" + sha384Hex);
//		return sha384Hex;
//	}
	
	private String superEncrypt(String password, String salt) {
		String result = "";
		try {
			result = PasswordSecureHash.hashEncrypt(password, salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Object getCredentials(AuthenticationInfo info) {
        Object credentials = info.getCredentials();
        CredentialsInfoHolder cih = (CredentialsInfoHolder) credentials;
        return cih;
    }
	
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
//		CustomCredentialsMatcher m = new CustomCredentialsMatcher();
////		
//		String r = m.encrypt("123456");
//		
////		String r = new Md5Hash("123456").toString();
//		
//		
//		System.out.println(r);
		
//		SecureRandom random = new SecureRandom();
////		int next = random.nextInt();
//		long next = random.nextLong();
//		System.err.println(next);
		
		
		String next = PasswordHash.createHash("123456");
		System.err.println(next);
	}
}