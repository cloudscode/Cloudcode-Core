package com.cloudcode.task.mvc;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cloudcode.common.cache.Cache;
import com.cloudcode.common.cache.CacheManager;
import com.cloudcode.framework.controller.CrudController;
import com.cloudcode.framework.rest.ReturnResult;
import com.cloudcode.framework.service.ServiceResult;
import com.cloudcode.framework.utils.BeanUpdater;
import com.cloudcode.framework.utils.PageRange;
import com.cloudcode.framework.utils.PaginationSupport;
import com.cloudcode.task.dao.TaskConfigDao;
import com.cloudcode.task.model.TaskConfig;

@Controller
@RequestMapping("/taskconfig")
@Component
public class TaskConfigController extends CrudController<TaskConfig> {
	@Autowired
	private TaskConfigDao taskConfigDao;
	@Resource(name="global.cacheManager")
	CacheManager cacheManager;
	
	@RequestMapping(value = "/createTaskConfig", method ={ RequestMethod.POST,
			RequestMethod.GET} )
	public @ResponseBody
	void createTaskConfig(@ModelAttribute TaskConfig task, HttpServletRequest request) {
		String name = request.getParameter("name");
		taskConfigDao.addTaskConfig(task);
	}
	@RequestMapping(value = "/createTaskConfig2", method ={ RequestMethod.POST,
			RequestMethod.GET} )
	public @ResponseBody
	void createTaskConfig2(@ModelAttribute TaskConfig task, HttpServletRequest request) {
		Cache<Object, Object> cahce= cacheManager.getCache("test");
		if(null == cahce || null == cahce.get("test")){
			cahce.put("test", "test1");
			System.out.println("cahce put");
		}else{
			System.out.println("cahce get"+cahce.get("test"));
		}
	}
	@RequestMapping(value = "/{id}/updateTaskConfig", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	Object updateTaskConfig(@PathVariable("id") String id,
			@ModelAttribute TaskConfig updateObject, HttpServletRequest request) {
		TaskConfig task = taskConfigDao.loadObject(id);
		if (task != null) {
			BeanUpdater.copyProperties(updateObject, task);
			taskConfigDao.updateTaskConfig(task);
			return new ServiceResult(ReturnResult.SUCCESS);
		}
		return null;
	}

	@RequestMapping(value = "taskList")
	public ModelAndView taskList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/task/ftl/task/list.ftl");
		modelAndView.addObject("result", "cloudcode");
		return modelAndView;
	}

	@RequestMapping(value = "create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/task/ftl/task/detail.ftl");
		modelAndView.addObject("entityAction", "create");
		modelAndView.addObject("entity", new TaskConfig());
		return modelAndView;
	}
	
	@RequestMapping(value = "/{id}/update")
	public ModelAndView update(@PathVariable("id") String id) {
		TaskConfig task = taskConfigDao.loadObject(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/task/ftl/task/detail.ftl");
		modelAndView.addObject("entity", task);
		modelAndView.addObject("entityAction", "update");
		return modelAndView;
	}
	
	@RequestMapping(value = "/deleteAll")
	public @ResponseBody
	Object deleteAll(HttpServletRequest request) {
		String ids = request.getParameter("ids");
		taskConfigDao.deleteTaskConfig(ids);
		return new ServiceResult(ReturnResult.SUCCESS);
	}
	
	@RequestMapping(value = "query", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	PaginationSupport<TaskConfig> query(TaskConfig task, PageRange pageRange) {
		PaginationSupport<TaskConfig> tasks = taskConfigDao
				.queryPagingData(task, pageRange);
		return tasks;
	}
}
