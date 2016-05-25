package com.cloudcode.tk.mvc;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cloudcode.tk.service.UserService;
import com.cloudcode.usersystem.model.User;

@Controller
@RequestMapping("/movie")
public class MovieController {
	/*
	 * @Autowired private UserDao userDaos;
	 */
	@Autowired
	private UserService userService;

	/*@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}*/

	//private userService userService;//=new userService();
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String getMovie(@PathVariable String name, ModelMap model) {
		User user = new User();
		//user.setId("2");
		user.setBirthdate(new Date());
		user.setCreateDateTime(new Date());
		user.setCreateAuthor("cloudcode");
		// UserDao userDao=new UserDao();
		//userService.create(user);
		model.addAttribute("movie", name);
		return "list";

	}

}