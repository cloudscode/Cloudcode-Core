package com.cloudcode.system.mvc;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cloudcode.common.cache.Cache;
import com.cloudcode.common.cache.CacheManager;
import com.cloudcode.common.util.FileUpload;
import com.cloudcode.framework.controller.CrudController;
import com.cloudcode.framework.rest.ReturnResult;
import com.cloudcode.framework.service.ServiceResult;
import com.cloudcode.framework.utils.BeanUpdater;
import com.cloudcode.framework.utils.PageRange;
import com.cloudcode.framework.utils.PaginationSupport;
import com.cloudcode.framework.utils.UUID;
import com.cloudcode.system.dao.SystemFileDao;
import com.cloudcode.system.model.SystemFile;

@Controller
@RequestMapping("/systemfile")
@Component
public class SystemFileController extends CrudController<SystemFile> {

	@Autowired
	private SystemFileDao systemFileDao;
	@Resource(name = "global.cacheManager")
	CacheManager cacheManager;

	@RequestMapping(value = "/createSystemFile", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object createSystemFile(@ModelAttribute SystemFile objs, @RequestParam("attachment") MultipartFile[] myfiles,
			HttpServletRequest request) {
		for (MultipartFile myfile : myfiles) {
			if (myfile.isEmpty()) {
				System.out.println("文件未上传");
			} else {
				System.out.println("文件长度: " + myfile.getSize());
				System.out.println("文件类型: " + myfile.getContentType());
				System.out.println("文件名称: " + myfile.getName());
				System.out.println("文件原名: " + myfile.getOriginalFilename());
				System.out.println("========================================");
				// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
				// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
				// FileUtils.copyInputStreamToFile(myfile.getInputStream(), new
				// File(realPath, myfile.getOriginalFilename()));
				try {
					String name = request.getParameter("name");
					String type = request.getParameter("type");
					String serviceId = request.getParameter("serviceId");
					String parentServiceId = request.getParameter("parentServiceId");
					String path ="";
					String fileType = FilenameUtils.getExtension(myfile.getOriginalFilename());
					String filename = objs.getId()+"."+fileType;
					String oName = myfile.getOriginalFilename().replace("."+fileType, "");
					
					path = FileUpload.uploadFile(myfile.getInputStream(), filename, type);
					
					objs.setId(UUID.generateUUID());
					objs.setOriginalName(oName);
					objs.setFileType(fileType);
					objs.setType(type);
					objs.setPath(path);
					objs.setFileSize(myfile.getSize());
					objs.setServiceId(serviceId);
					objs.setParentServiceId(parentServiceId);
					systemFileDao.addSystemFile(objs);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return new ServiceResult(ReturnResult.SUCCESS);
	}

	@RequestMapping(value = "/createSystemFile2", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody void createSystemFile2(@ModelAttribute SystemFile objs, HttpServletRequest request) {
		Cache<Object, Object> cahce = cacheManager.getCache("test");
		if (null == cahce || null == cahce.get("test")) {
			cahce.put("test", "test1");
			System.out.println("cahce put");
		} else {
			System.out.println("cahce get" + cahce.get("test"));
		}
	}

	@RequestMapping(value = "/{id}/updateSystemFile", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object updateSystemFile(@PathVariable("id") String id, @ModelAttribute SystemFile updateObject,
			HttpServletRequest request) {
		SystemFile objs = systemFileDao.loadObject(id);
		if (objs != null) {
			BeanUpdater.copyProperties(updateObject, objs);
			systemFileDao.updateSystemFile(objs);
			return new ServiceResult(ReturnResult.SUCCESS);
		}
		return null;
	}

	@RequestMapping(value = "fileList")
	public ModelAndView fileList() {
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
		modelAndView.addObject("entity", new SystemFile());
		return modelAndView;
	}

	@RequestMapping(value = "/{id}/update")
	public ModelAndView update(@PathVariable("id") String id) {
		SystemFile task = systemFileDao.loadObject(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/task/ftl/task/detail.ftl");
		modelAndView.addObject("entity", task);
		modelAndView.addObject("entityAction", "update");
		return modelAndView;
	}

	@RequestMapping(value = "/deleteAll")
	public @ResponseBody Object deleteAll(HttpServletRequest request) {
		String ids = request.getParameter("ids");
		systemFileDao.deleteSystemFile(ids);
		return new ServiceResult(ReturnResult.SUCCESS);
	}

	@RequestMapping(value = "query", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody PaginationSupport<SystemFile> query(SystemFile objs, PageRange pageRange) {
		PaginationSupport<SystemFile> objss = systemFileDao.queryPagingData(objs, pageRange);
		return objss;
	}

	@RequestMapping(value = "file")
	public ModelAndView file() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/system/ftl/file/file.ftl");
		modelAndView.addObject("result", "cloudcode");
		return modelAndView;
	}
}
