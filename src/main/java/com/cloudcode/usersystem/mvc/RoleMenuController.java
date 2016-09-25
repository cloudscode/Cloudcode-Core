package com.cloudcode.usersystem.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.cloudcode.usersystem.dao.RoleMenuDao;
import com.cloudcode.usersystem.model.RoleMenu;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/rolemenus")
public class RoleMenuController extends CrudController<RoleMenu>{

	@Autowired
	private RoleMenuDao roleMenuDao;
	
	
	@RequestMapping(value = "/createRoleMenu", method = RequestMethod.POST)
	public @ResponseBody
	Object createRoleMenu(@ModelAttribute RoleMenu role, HttpServletRequest request) {
		roleMenuDao.addRoleMenu(role);
		return new ServiceResult(ReturnResult.SUCCESS);
	}

	@RequestMapping(value = "/{id}/updateRoleMenu", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	Object updateRoleMenu(@PathVariable("id") String id,
			@ModelAttribute RoleMenu updateObject, HttpServletRequest request) {
		RoleMenu role = roleMenuDao.loadObject(id);
		if (role != null) {
			BeanUpdater.copyProperties(updateObject, role);
			// org.springframework.beans.BeanUtils.copyProperties(updateObject,
			// role);
			roleMenuDao.updateObject(role);
			return new ServiceResult(ReturnResult.SUCCESS);
		}
		return new ServiceResult(ReturnResult.FAILURE);
	}

	@RequestMapping(value = "roleMenuList")
	public ModelAndView roleMenuList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/usersystem/ftl/rolemenu/list.ftl");
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
		RoleMenu role = roleMenuDao.loadObject(id);
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
			roleMenuDao.deleteObject(id);
		}
		return new ServiceResult(ReturnResult.SUCCESS);
	}
	
	@RequestMapping(value = "query", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	PaginationSupport<RoleMenu> query(RoleMenu role, PageRange pageRange) {
		PaginationSupport<RoleMenu> roles = roleMenuDao
				.queryPagingData(role, pageRange);
		return roles;
	}
	@RequestMapping(value = "/{id}/toDetail")
	public ModelAndView toDetail(@PathVariable("id") String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/usersystem/ftl/role/detail2.ftl");
		if("collection".equals(id)){
			modelAndView.addObject("entityAction", "create");
		}else{
			RoleMenu obj = roleMenuDao.loadObject(id);
			JSONObject json = JSONObject.fromObject(obj);
			modelAndView.addObject("entity",json.toString() );
			modelAndView.addObject("entityAction", "update");
		}
		return modelAndView;
	}
}
