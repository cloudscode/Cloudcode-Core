package com.cloudcode.tk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudcode.usersystem.dao.UserDao;
import com.cloudcode.usersystem.model.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public Object create(User entity) {
		return userDao.create(entity);
	}

	public List<User> listList() {
		return userDao.loadAll();
	}

	
}
