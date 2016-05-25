package com.cloudcode.organization.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

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
import com.cloudcode.framework.utils.StringUtils;
import com.cloudcode.organization.dao.OrganizationDao;
import com.cloudcode.organization.model.Organization;

@Controller
@RequestMapping("/orgs")
public class OrganizationController extends CrudController<Organization> {
	@Autowired
	private OrganizationDao orgDao;
	
	@RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
	public @ResponseBody
	void createOrganization(@ModelAttribute Organization org, HttpServletRequest request) {
		String text = request.getParameter("text");
		orgDao.addOrganization(org);
	}

	@RequestMapping(value = "/{id}/updateOrganization", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	Object updateOrganization(@PathVariable("id") String id,
			@ModelAttribute Organization updateObject, HttpServletRequest request) {
		Organization org = orgDao.loadObject(id);
		if (org != null) {
			BeanUpdater.copyProperties(updateObject, org);
			// org.springframework.beans.BeanUtils.copyProperties(updateObject,
			// org);
			orgDao.updateObject(org);
			return new ServiceResult(ReturnResult.SUCCESS);
		}
		return null;
	}

	@RequestMapping(value = "orgList")
	public ModelAndView orgList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/org/list.ftl");
		modelAndView.addObject("result", "cloudcode");
		return modelAndView;
	}

	@RequestMapping(value = "create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/org/detail.ftl");
		modelAndView.addObject("result", "cloudcode");
		modelAndView.addObject("entityAction", "create");
		return modelAndView;
	}

	@RequestMapping(value = "/{id}/update")
	public ModelAndView update(@PathVariable("id") String id) {
		Organization org = orgDao.loadObject(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/org/detail.ftl");
		modelAndView.addObject("org", org);
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
			orgDao.deleteObject(id);
		}
		return new ServiceResult(ReturnResult.SUCCESS);
	}
	
	@RequestMapping(value = "query", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	PaginationSupport<Organization> query(Organization org, PageRange pageRange) {
		PaginationSupport<Organization> orgs = orgDao
				.queryPagingData(org, pageRange);
		return orgs;
	}
	@RequestMapping(value = "queryDataTreeByPid", method = {
			RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody
	JSONArray queryDataTreeByPid(HttpServletRequest request) {
		String id =request.getParameter("id");
		List<Organization> lists = null;
		if(StringUtils.isEmpty(id)){
			lists = orgDao.queryDataTreeByPid("root");
		}else{
			lists = orgDao.queryDataTreeByPid(id);
		}
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();

		for (Organization menu : lists) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("id", menu.getId());
			maps.put("name", menu.getName());
			maps.put("pId", menu.getNode());
			maps.put("isParent", true);
			listMap.add(maps);
		}
		return JSONArray.fromObject(listMap);
	}
	@RequestMapping(value = "queryTree", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Object queryTree(Organization org, PageRange pageRange, HttpServletRequest request) {
		String nodeid =request.getParameter("nodeid");
		String n_level =request.getParameter("n_level");
		List<Organization> lists = null;
		if(StringUtils.isEmpty(nodeid)){
			lists = orgDao.queryDataTreeByPid("root");
		}else{
			lists = orgDao.queryDataTreeByPid(nodeid);
		}
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (Organization obj : lists) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("id", obj.getId());
			maps.put("name", obj.getName());
			//maps.put("action", obj.getAction());
			maps.put("node", obj.getNode());
			maps.put("expanded", false);
			maps.put("isLeaf", false);
			maps.put("parent", obj.getNode());
			maps.put("level", n_level==null?0:(Integer.parseInt(n_level)+1));
			listMap.add(maps);
		}
		return listMap;
	}
}
