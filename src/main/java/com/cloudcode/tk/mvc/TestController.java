package com.cloudcode.tk.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.cloudcode.tk.service.UserService;
import com.cloudcode.tk.service.testService;
import com.cloudcode.usersystem.dao.UserDao;
import com.cloudcode.usersystem.model.User;

@Controller
public class TestController  {
	@Autowired
	private UserService userService;
	
   @Autowired
   private testService testService;
	@Autowired
	private UserDao userDao;
	

	private Map<String, Object> getMap(List<User> user) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("total", user.size());
		modelMap.put("data", user);
		modelMap.put("success", true);

		return modelMap;
	}

}
