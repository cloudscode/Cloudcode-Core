package com.cloudcode.usersystem.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.cloudcode.common.security.UserPrincipal;
import com.cloudcode.usersystem.model.User;

@Repository
public class CurrentUserInfo {
	
	public User getCurrentUserInfo() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null && null != auth.getPrincipal()){
			UserPrincipal userPrincipal= (UserPrincipal) auth.getPrincipal();
			return userPrincipal.getUser();
		}
		return null;
	}
}
