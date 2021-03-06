package com.cloudcode.organization.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cloudcode.framework.utils.StringUtils;
import com.cloudcode.organization.dao.GroupDao;
import com.cloudcode.organization.model.Group;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/groups")
public class GroupController extends CrudController<Group> {
	@Autowired
	private GroupDao groupDao;
	
	@RequestMapping(value = "/createGroup", method = RequestMethod.POST)
	public @ResponseBody
	Object createGroup(@ModelAttribute Group group, HttpServletRequest request) {
		String text = request.getParameter("text");
		groupDao.addGroup(group);
		return new ServiceResult(ReturnResult.SUCCESS);
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
		return new ServiceResult(ReturnResult.FAILURE);
	}

	@RequestMapping(value = "groupList")
	public ModelAndView groupList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/group/list2.ftl");
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
		if(null != group.getIdCode() && !"root".equals(group.getIdCode())){
			Group group1 = groupDao.loadObject(group.getIdCode());
			group.setIdCodeName(group1.getName());
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/group/detail.ftl");
		JSONObject json = JSONObject.fromObject(group);
		modelAndView.addObject("entity",json.toString() );
		modelAndView.addObject("entityAction", "update");
		return modelAndView;
	}
	@RequestMapping(value = "/{id}/toDetail")
	public ModelAndView toDetail(@PathVariable("id") String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/group/detail2.ftl");
		if("collection".equals(id)){
			modelAndView.addObject("entityAction", "create");
		}else{
			Group group = groupDao.loadObject(id);
			JSONObject json = JSONObject.fromObject(group);
			modelAndView.addObject("entity",json.toString() );
			modelAndView.addObject("entityAction", "update");
		}
		return modelAndView;
	}
	@RequestMapping(value = "/deleteAll")
	public @ResponseBody
	Object deleteAll(HttpServletRequest request) {
		String ids = request.getParameter("ids");
		String[] arrayId = ids.split(",");
		try {
			for (String id : arrayId) {
				groupDao.deleteObject(id);
			}
		} catch (Exception e) {
			return new ServiceResult(ReturnResult.FAILURE);
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
			maps.put("pId", menu.getIdCode());
			maps.put("isParent", menu.getLeaf()!=0?false:true);
			maps.put("isLeaf", menu.getLeaf()==0?true:false);
			System.out.println(menu.getIdCode()=="root"?false:true);
			listMap.add(maps);
		}
		return JSONArray.fromObject(listMap);
	}
	@RequestMapping(value = "queryTree", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody Object queryTree(Group group, PageRange pageRange, HttpServletRequest request) {
		String idCode =request.getParameter("nodeid");
		String n_level =request.getParameter("n_level");
		List<Group> lists = null;
		if(StringUtils.isEmpty(idCode)){
			lists = groupDao.queryDataTreeByPid("root");
		}else{
			lists = groupDao.queryDataTreeByPid(idCode);
		}
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (Group obj : lists) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("id", obj.getId());
			maps.put("name", obj.getName());
			maps.put("text", obj.getName());
			maps.put("node", obj.getIdCode());
			maps.put("code", obj.getCode());
			maps.put("shortName", obj.getShortName());
			maps.put("expanded", false);
			maps.put("isLeaf", false);
			maps.put("parent", obj.getNode());
			maps.put("leaf", true);
			if(StringUtils.isEmpty(n_level)){
				maps.put("level", 0);
			}else{
				maps.put("level",Integer.parseInt(n_level)+1);
			}
			listMap.add(maps);
		}
		return listMap;
	}
	@RequestMapping(value = "tree")
	public ModelAndView tree() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/group/tree.ftl");
		modelAndView.addObject("result", "cloudcode");
		modelAndView.addObject("entityAction", "create");
		return modelAndView;
	}
	@RequestMapping(value = "queryTreeList", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody Object queryTreeList() {
		List<Group> lists = groupDao.findByProperty("idCode", "root");
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (Group obj : lists) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("id", obj.getId());
			maps.put("name", obj.getName());
			maps.put("text", obj.getName());
			maps.put("pId", obj.getIdCode());
			maps.put("parent", obj.getIdCode());
			addChildren(maps,obj);
			//maps.put("code", obj.getCode());
			//maps.put("shortName", obj.getShortName());
			//maps.put("expanded", false);
			//maps.put("isLeaf", true);
			//maps.put("parent", obj.getNode());
			//maps.put("leaf", true);
			
			listMap.add(maps);
		}
		return listMap;
	}
	private void addChildren(Map<String, Object> maps2,Group entity){
		List<Group> lists = groupDao.findByProperty("idCode", entity.getId());
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		if(lists.size()>0){
			for (Group obj : lists) {
				Map<String, Object> maps = new HashMap<String, Object>();
				maps.put("id", obj.getId());
				maps.put("name", obj.getName());
				maps.put("text", obj.getName());
				maps.put("pId", obj.getIdCode());
				maps.put("parent", obj.getId());
				addChildren(maps,obj);
				//maps.put("code", obj.getCode());
				//maps.put("shortName", obj.getShortName());
				//maps.put("expanded", false);
				//maps.put("isLeaf", true);
				//maps.put("parent", obj.getNode());
				//maps.put("leaf", true);
				
				listMap.add(maps);
			}
			maps2.put("children", listMap);
		}
	}
			
}
