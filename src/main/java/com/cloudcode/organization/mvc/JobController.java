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
import com.cloudcode.organization.dao.JobDao;
import com.cloudcode.organization.model.Job;

@Controller
@RequestMapping("/jobs")
public class JobController extends CrudController<Job> {
	@Autowired
	private JobDao jobDao;
	
	@RequestMapping(value = "/createJob", method = RequestMethod.POST)
	public @ResponseBody
	void createJob(@ModelAttribute Job job, HttpServletRequest request) {
		String text = request.getParameter("text");
		jobDao.addJob(job);
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
		return null;
	}

	@RequestMapping(value = "jobList")
	public ModelAndView jobList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/job/list.ftl");
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
	
	@RequestMapping(value = "queryDataTreeByPid", method = {
			RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody
	JSONArray queryDataTreeByPid(HttpServletRequest request) {
		String id =request.getParameter("id");
		List<Job> lists = null;
		if(StringUtils.isEmpty(id)){
			lists = jobDao.queryDataTreeByPid("root");
		}else{
			lists = jobDao.queryDataTreeByPid(id);
		}
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();

		for (Job menu : lists) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("id", menu.getId());
			maps.put("name", menu.getName());
			maps.put("pId", menu.getNode());
			maps.put("isParent", true);
			listMap.add(maps);
		}
		return JSONArray.fromObject(listMap);
	}
}
