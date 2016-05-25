package com.cloudcode.usersystem.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cloudcode.framework.controller.CrudController;
import com.cloudcode.framework.rest.ReturnResult;
import com.cloudcode.framework.service.ServiceResult;
import com.cloudcode.framework.utils.BeanUpdater;
import com.cloudcode.framework.utils.PageRange;
import com.cloudcode.framework.utils.PaginationSupport;
import com.cloudcode.usersystem.dao.UserDao;
import com.cloudcode.usersystem.model.User;

@Controller
@RequestMapping({ "/users" })
public class UserController  extends CrudController<User>{
	@Autowired
	private UserDao userDao;

	protected Validator getValidator() {
		// TODO Auto-generated method stub
		return null;
	}
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public @ResponseBody
	void createUser(@ModelAttribute User user, HttpServletRequest request) {
		String text = request.getParameter("text");
		userDao.addUser(user);
	}

	@RequestMapping(value = "/{id}/updateUser", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	Object updateUser(@PathVariable("id") String id,
			@ModelAttribute User updateObject, HttpServletRequest request) {
		User user = userDao.loadObject(id);
		if (user != null) {
			BeanUpdater.copyProperties(updateObject, user);
			// org.springframework.beans.BeanUtils.copyProperties(updateObject,
			// user);
			userDao.updateObject(user);
			return new ServiceResult(ReturnResult.SUCCESS);
		}
		return null;
	}

	@RequestMapping(value = "userList")
	public ModelAndView userList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/usersystem/ftl/user/list.ftl");
		modelAndView.addObject("result", "cloudcode");
		return modelAndView;
	}

	@RequestMapping(value = "create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/usersystem/ftl/user/detail.ftl");
		modelAndView.addObject("result", "cloudcode");
		modelAndView.addObject("entityAction", "create");
		return modelAndView;
	}

	@RequestMapping(value = "/{id}/update")
	public ModelAndView update(@PathVariable("id") String id) {
		User user = userDao.loadObject(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/usersystem/ftl/user/detail.ftl");
		modelAndView.addObject("user", user);
		modelAndView.addObject("entityAction", "update");
		return modelAndView;
	}
	
	@RequestMapping(value = "/deleteAll")
	public @ResponseBody
	Object deleteAll(HttpServletRequest request) {
		String ids = request.getParameter("ids");
		String[] arrayId = ids.split(",");
		for (String id : arrayId) {
			userDao.deleteObject(id);
		}
		return new ServiceResult(ReturnResult.SUCCESS);
	}
	
	@RequestMapping(value = "query", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	PaginationSupport<User> query(User user, PageRange pageRange) {
		PaginationSupport<User> users = userDao
				.queryPagingData(user, pageRange);
		return users;
	}
}
