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
import com.cloudcode.organization.dao.DepartmentDao;
import com.cloudcode.organization.model.Department;
import com.cloudcode.organization.model.Organization;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/depts")
public class DepartmentController extends CrudController<Department> {
	@Autowired
	private DepartmentDao deptDao;
	@RequestMapping(value = "/createDepartment", method = RequestMethod.POST)
	public @ResponseBody
	Object createDepartment(@ModelAttribute Department dept, HttpServletRequest request) {
		deptDao.addDepartment(dept);
		return new ServiceResult(ReturnResult.SUCCESS);
	}

	@RequestMapping(value = "/{id}/updateDepartment", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	Object updateDepartment(@PathVariable("id") String id,
			@ModelAttribute Department updateObject, HttpServletRequest request) {
		Department dept = deptDao.loadObject(id);
		if (dept != null) {
			BeanUpdater.copyProperties(updateObject, dept);
			deptDao.updateObject(dept);
			return new ServiceResult(ReturnResult.SUCCESS);
		}
		return new ServiceResult(ReturnResult.FAILURE);
	}

	@RequestMapping(value = "deptList")
	public ModelAndView deptList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/dept/list2.ftl");
		modelAndView.addObject("result", "cloudcode");
		return modelAndView;
	}

	@RequestMapping(value = "create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/dept/detail.ftl");
		modelAndView.addObject("result", "cloudcode");
		modelAndView.addObject("entityAction", "create");
		return modelAndView;
	}

	@RequestMapping(value = "/{id}/update")
	public ModelAndView update(@PathVariable("id") String id) {
		Department dept = deptDao.loadObject(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/dept/detail.ftl");
		modelAndView.addObject("dept", dept);
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
			deptDao.deleteObject(id);
		}
		return new ServiceResult(ReturnResult.SUCCESS);
	}
	
	@RequestMapping(value = "query", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	PaginationSupport<Department> query(Department dept, PageRange pageRange) {
		PaginationSupport<Department> depts = deptDao
				.queryPagingData(dept, pageRange);
		return depts;
	}
	
	@RequestMapping(value = "queryDataTreeByPid", method = {
			RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody
	JSONArray queryDataTreeByPid(HttpServletRequest request) {
		String id =request.getParameter("id");
		List<Department> lists = null;
		if(StringUtils.isEmpty(id)){
			lists = deptDao.queryDataTreeByPid("root");
		}else{
			lists = deptDao.queryDataTreeByPid(id);
		}
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();

		for (Department menu : lists) {
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
	public @ResponseBody Object queryTree(Department dept, PageRange pageRange, HttpServletRequest request) {
		String nodeid =request.getParameter("nodeid");
		String n_level =request.getParameter("n_level");
		List<Department> lists = null;
		if(StringUtils.isEmpty(nodeid)){
			lists = deptDao.queryDataTreeByPid("root");
		}else{
			lists = deptDao.queryDataTreeByPid(nodeid);
		}
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (Department obj : lists) {
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
	@RequestMapping(value = "/{id}/toDetail")
	public ModelAndView toDetail(@PathVariable("id") String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/dept/detail2.ftl");
		if("collection".equals(id)){
			modelAndView.addObject("entityAction", "create");
		}else{
			Department obj = deptDao.loadObject(id);
			JSONObject json = JSONObject.fromObject(obj);
			modelAndView.addObject("entity",json.toString() );
			modelAndView.addObject("entityAction", "update");
		}
		return modelAndView;
	}
	@RequestMapping(value = "queryTreeList", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody Object queryTreeList() {
		List<Department> lists = deptDao.findByProperty("idCode", "root");
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (Department obj : lists) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("id", obj.getId());
			maps.put("name", obj.getName());
			maps.put("text", obj.getName());
			maps.put("pId", obj.getIdCode());
			maps.put("parent", obj.getIdCode());
			addChildren(maps,obj);
			listMap.add(maps);
		}
		return listMap;
	}
	private void addChildren(Map<String, Object> maps2,Department entity){
		List<Department> lists = deptDao.findByProperty("idCode", entity.getId());
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		if(lists.size()>0){
			for (Department obj : lists) {
				Map<String, Object> maps = new HashMap<String, Object>();
				maps.put("id", obj.getId());
				maps.put("name", obj.getName());
				maps.put("text", obj.getName());
				maps.put("pId", obj.getIdCode());
				maps.put("parent", obj.getId());
				addChildren(maps,obj);
				listMap.add(maps);
			}
			maps2.put("children", listMap);
		}
	}
}
