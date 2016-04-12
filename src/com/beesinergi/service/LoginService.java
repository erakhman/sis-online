package com.beesinergi.service;

import java.util.List;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beesinergi.mapper.UserMapper;
import com.beesinergi.model.AppUser;

@Service("loginService")
public class LoginService {
	
	@Autowired
	private UserMapper userMapper;
	
	public AppUser findUser(AppUser user) {
		List<AppUser> userList = userMapper.selectAll(user); 
		if (userList.isEmpty()){
			return null;
		} else if (!checkPassword(user.getPassword(),userList.get(0).getPassword())){
			return null;
		}
		return userList.get(0);
	}
	
	
	public boolean checkPassword(String inputPassword, String dbPassword) {
		boolean match = false;
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		if (passwordEncryptor.checkPassword(inputPassword, dbPassword)) {
			match = true;
		} 
		return match;
	}

}
