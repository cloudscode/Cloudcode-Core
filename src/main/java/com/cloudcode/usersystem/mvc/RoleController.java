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
import com.cloudcode.usersystem.dao.RoleDao;
import com.cloudcode.usersystem.model.Role;

@Controller
@RequestMapping("/roles")
public class RoleController extends CrudController<Role>{

	@Autowired
	private RoleDao roleDao;
	
	protected Validator getValidator() {
		return null;
	}
	@RequestMapping(value = "/createRole", method = RequestMethod.POST)
	public @ResponseBody
	void createRole(@ModelAttribute Role role, HttpServletRequest request) {
		String text = request.getParameter("text");
		roleDao.addRole(role);
	}

	@RequestMapping(value = "/{id}/updateRole", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	Object updateRole(@PathVariable("id") String id,
			@ModelAttribute Role updateObject, HttpServletRequest request) {
		Role role = roleDao.loadObject(id);
		if (role != null) {
			BeanUpdater.copyProperties(updateObject, role);
			// org.springframework.beans.BeanUtils.copyProperties(updateObject,
			// role);
			roleDao.updateObject(role);
			return new ServiceResult(ReturnResult.SUCCESS);
		}
		return null;
	}

	@RequestMapping(value = "roleList")
	public ModelAndView roleList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/usersystem/ftl/role/list.ftl");
		modelAndView.addObject("result", "cloudcode");
		return modelAndView;
	}

	@RequestMapping(value = "create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/usersystem/ftl/role/detail.ftl");
		modelAndView.addObject("result", "cloudcode");
		modelAndView.addObject("entityAction", "create");
		return modelAndView;
	}

	@RequestMapping(value = "/{id}/update")
	public ModelAndView update(@PathVariable("id") String id) {
		Role role = roleDao.loadObject(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/usersystem/ftl/role/detail.ftl");
		modelAndView.addObject("role", role);
		modelAndView.addObject("result", "cloudcode");
		modelAndView.addObject("entityAction", "update");
		return modelAndView;
	}
	
	@RequestMapping(value = "/deleteAll")
	public @ResponseBody
	Object deleteAll(HttpServletRequest request) {
		String ids = request.getParameter("ids");
		String[] arrayId = ids.split(",");
		for (String id : arrayId) {
			roleDao.deleteObject(id);
		}
		return new ServiceResult(ReturnResult.SUCCESS);
	}
	
	@RequestMapping(value = "query", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	PaginationSupport<Role> query(Role role, PageRange pageRange) {
		PaginationSupport<Role> roles = roleDao
				.queryPagingData(role, pageRange);
		return roles;
	}
}
