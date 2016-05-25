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
import com.cloudcode.usersystem.dao.AclDao;
import com.cloudcode.usersystem.model.Acl;

@Controller
@RequestMapping({ "/acl" })
public class AclController  extends CrudController<Acl>{

	@Autowired
	private AclDao aclDao;

	protected Validator getValidator() {
		return null;
	}
	@RequestMapping(value = "/createAcl", method = RequestMethod.POST)
	public @ResponseBody
	void createAcl(@ModelAttribute Acl acl, HttpServletRequest request) {
		String text = request.getParameter("text");
		aclDao.addAcl(acl);
	}

	@RequestMapping(value = "/{id}/updateAcl", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	Object updateAcl(@PathVariable("id") String id,
			@ModelAttribute Acl updateObject, HttpServletRequest request) {
		Acl acl = aclDao.loadObject(id);
		if (acl != null) {
			BeanUpdater.copyProperties(updateObject, acl);
			// org.springframework.beans.BeanUtils.copyProperties(updateObject,
			// acl);
			aclDao.updateObject(acl);
			return new ServiceResult(ReturnResult.SUCCESS);
		}
		return null;
	}

	@RequestMapping(value = "aclList")
	public ModelAndView aclList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/acl/list.ftl");
		modelAndView.addObject("result", "cloudcode");
		return modelAndView;
	}

	@RequestMapping(value = "create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/acl/detail.ftl");
		modelAndView.addObject("result", "cloudcode");
		modelAndView.addObject("entityAction", "create");
		return modelAndView;
	}

	@RequestMapping(value = "/{id}/update")
	public ModelAndView update(@PathVariable("id") String id) {
		Acl acl = aclDao.loadObject(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/acl/detail.ftl");
		modelAndView.addObject("acl", acl);
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
			aclDao.deleteObject(id);
		}
		return new ServiceResult(ReturnResult.SUCCESS);
	}
	
	@RequestMapping(value = "query", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	PaginationSupport<Acl> query(Acl acl, PageRange pageRange) {
		PaginationSupport<Acl> acls = aclDao
				.queryPagingData(acl, pageRange);
		return acls;
	}
}
