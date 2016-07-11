package com.cloudcode.common.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cloudcode.common.security.UserPrincipal;
import com.cloudcode.usersystem.model.User;

public class BeanUtils {

	  public static User getUserDetailAdapter()
	  {
	    Authentication auth = SecurityContextHolder.getContext()
	      .getAuthentication();
	    UserPrincipal userDetails = null;
	    User user = null;
	    if (auth != null) {
	      Object obj = auth.getPrincipal();
	      if ((obj instanceof UserPrincipal)) {
	    	  userDetails = (UserPrincipal)obj;
	      }
	    }
	    if(null != userDetails){
	    	user=userDetails.getUser();
	    }
	    return user;
	  }
	  
}
