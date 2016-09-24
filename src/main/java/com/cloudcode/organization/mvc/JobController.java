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
import com.cloudcode.organization.dao.JobDao;
import com.cloudcode.organization.model.Job;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/jobs")
public class JobController extends CrudController<Job> {
	@Autowired
	private JobDao jobDao;
	
	@RequestMapping(value = "/createJob", method = RequestMethod.POST)
	public @ResponseBody
	Object createJob(@ModelAttribute Job job, HttpServletRequest request) {
		jobDao.addJob(job);
		return new ServiceResult(ReturnResult.SUCCESS);
	}

	@RequestMapping(value = "/{id}/updateJob", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	Object updateJob(@PathVariable("id") String id,
			@ModelAttribute Job updateObject, HttpServletRequest request) {
		Job job = jobDao.loadObject(id);
		if (job != null) {
			BeanUpdater.copyProperties(updateObject, job);
			// org.springframework.beans.BeanUtils.copyProperties(updateObject,
			// job);
			jobDao.updateObject(job);
			return new ServiceResult(ReturnResult.SUCCESS);
		}
		return new ServiceResult(ReturnResult.FAILURE);
	}

	@RequestMapping(value = "jobList")
	public ModelAndView jobList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/job/list2.ftl");
		modelAndView.addObject("result", "cloudcode");
		return modelAndView;
	}

	@RequestMapping(value = "create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/job/detail.ftl");
		modelAndView.addObject("result", "cloudcode");
		modelAndView.addObject("entityAction", "create");
		return modelAndView;
	}

	@RequestMapping(value = "/{id}/update")
	public ModelAndView update(@PathVariable("id") String id) {
		Job job = jobDao.loadObject(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/job/detail.ftl");
		modelAndView.addObject("job", job);
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
			jobDao.deleteObject(id);
		}
		return new ServiceResult(ReturnResult.SUCCESS);
	}
	
	@RequestMapping(value = "query", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	PaginationSupport<Job> query(Job job, PageRange pageRange) {
		PaginationSupport<Job> jobs = jobDao
				.queryPagingData(job, pageRange);
		return jobs;
	}
	
	@RequestMapping(value = "queryTree", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Object queryTree(Job Job, PageRange pageRange, HttpServletRequest request) {
		String nodeid =request.getParameter("nodeid");
		String n_level =request.getParameter("n_level");
		List<Job> lists = null;
		if(StringUtils.isEmpty(nodeid)){
			lists = jobDao.queryDataTreeByPid("root");
		}else{
			lists = jobDao.queryDataTreeByPid(nodeid);
		}
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();

		for (Job obj : lists) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("id", obj.getId());
			maps.put("name", obj.getName());
			maps.put("text", obj.getName());
			maps.put("node", obj.getIdCode());
			maps.put("code", obj.getCode());
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
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/job/detail2.ftl");
		if("collection".equals(id)){
			modelAndView.addObject("entityAction", "create");
		}else{
			Job obj = jobDao.loadObject(id);
			JSONObject json = JSONObject.fromObject(obj);
			modelAndView.addObject("entity",json.toString() );
			modelAndView.addObject("entityAction", "update");
		}
		return modelAndView;
	}
	@RequestMapping(value = "queryTreeList", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody Object queryTreeList() {
		List<Job> lists = jobDao.findByProperty("idCode", "root");
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (Job obj : lists) {
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
	private void addChildren(Map<String, Object> maps2,Job entity){
		List<Job> lists = jobDao.findByProperty("idCode", entity.getId());
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		if(lists.size()>0){
			for (Job obj : lists) {
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
