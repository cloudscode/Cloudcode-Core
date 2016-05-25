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
import com.cloudcode.organization.dao.GroupDao;
import com.cloudcode.organization.model.Group;

@Controller
@RequestMapping("/groups")
public class GroupController extends CrudController<Group> {
	@Autowired
	private GroupDao groupDao;
	
	@RequestMapping(value = "/createGroup", method = RequestMethod.POST)
	public @ResponseBody
	void createGroup(@ModelAttribute Group group, HttpServletRequest request) {
		String text = request.getParameter("text");
		groupDao.addGroup(group);
	}

	@RequestMapping(value = "/{id}/updateGroup", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	Object updateGroup(@PathVariable("id") String id,
			@ModelAttribute Group updateObject, HttpServletRequest request) {
		Group group = groupDao.loadObject(id);
		if (group != null) {
			BeanUpdater.copyProperties(updateObject, group);
			// org.springframework.beans.BeanUtils.copyProperties(updateObject,
			// group);
			groupDao.updateObject(group);
			return new ServiceResult(ReturnResult.SUCCESS);
		}
		return null;
	}

	@RequestMapping(value = "groupList")
	public ModelAndView groupList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/group/list.ftl");
		modelAndView.addObject("result", "cloudcode");
		return modelAndView;
	}

	@RequestMapping(value = "create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/group/detail.ftl");
		modelAndView.addObject("result", "cloudcode");
		modelAndView.addObject("entityAction", "create");
		return modelAndView;
	}

	@RequestMapping(value = "/{id}/update")
	public ModelAndView update(@PathVariable("id") String id) {
		Group group = groupDao.loadObject(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/group/detail.ftl");
		modelAndView.addObject("group", group);
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
			groupDao.deleteObject(id);
		}
		return new ServiceResult(ReturnResult.SUCCESS);
	}
	
	@RequestMapping(value = "query", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	PaginationSupport<Group> query(Group group, PageRange pageRange) {
		PaginationSupport<Group> groups = groupDao
				.queryPagingData(group, pageRange);
		return groups;
	}
	
	@RequestMapping(value = "queryDataTreeByPid", method = {
			RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody
	JSONArray queryDataTreeByPid(HttpServletRequest request) {
		String id =request.getParameter("id");
		List<Group> lists = null;
		if(StringUtils.isEmpty(id)){
			lists = groupDao.queryDataTreeByPid("root");
		}else{
			lists = groupDao.queryDataTreeByPid(id);
		}
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();

		for (Group menu : lists) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("id", menu.getId());
			maps.put("name", menu.getName());
			maps.put("pId", menu.getNode());
			maps.put("isParent", menu.getNode()==null?false:true);
			listMap.add(maps);
		}
		return JSONArray.fromObject(listMap);
	}
	@RequestMapping(value = "queryTree", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Object queryTree(Group group, PageRange pageRange, HttpServletRequest request) {
		String nodeid =request.getParameter("nodeid");
		String n_level =request.getParameter("n_level");
		List<Group> lists = null;
		if(StringUtils.isEmpty(nodeid)){
			lists = groupDao.queryDataTreeByPid("root");
		}else{
			lists = groupDao.queryDataTreeByPid(nodeid);
		}
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (Group obj : lists) {
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
