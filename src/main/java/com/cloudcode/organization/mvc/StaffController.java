package com.cloudcode.organization.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
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
import com.cloudcode.organization.dao.StaffDao;
import com.cloudcode.organization.model.Staff;

@Controller
@RequestMapping("/staffs")
public class StaffController extends CrudController<Staff> {
	@Autowired
	private StaffDao staffDao;
	
	@RequestMapping(value = "/createStaff", method = RequestMethod.POST)
	public @ResponseBody
	void createStaff(@ModelAttribute Staff staff, HttpServletRequest request) {
		String text = request.getParameter("text");
		staffDao.addStaff(staff);
	}

	@RequestMapping(value = "/{id}/updateStaff", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	Object updateStaff(@PathVariable("id") String id,
			@ModelAttribute Staff updateObject, HttpServletRequest request) {
		Staff staff = staffDao.loadObject(id);
		if (staff != null) {
			BeanUpdater.copyProperties(updateObject, staff);
			// org.springframework.beans.BeanUtils.copyProperties(updateObject,
			// staff);
			staffDao.updateObject(staff);
			return new ServiceResult(ReturnResult.SUCCESS);
		}
		return null;
	}

	@RequestMapping(value = "staffList")
	public ModelAndView staffList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/staff/list.ftl");
		modelAndView.addObject("result", "cloudcode");
		return modelAndView;
	}

	@RequestMapping(value = "create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/staff/detail.ftl");
		modelAndView.addObject("result", "cloudcode");
		modelAndView.addObject("entityAction", "create");
		return modelAndView;
	}

	@RequestMapping(value = "/{id}/update")
	public ModelAndView update(@PathVariable("id") String id) {
		Staff staff = staffDao.loadObject(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/staff/detail.ftl");
		modelAndView.addObject("staff", staff);
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
			staffDao.deleteObject(id);
		}
		return new ServiceResult(ReturnResult.SUCCESS);
	}
	
	@RequestMapping(value = "query", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	PaginationSupport<Staff> query(Staff staff, PageRange pageRange) {
		PaginationSupport<Staff> staffs = staffDao
				.queryPagingData(staff, pageRange);
		return staffs;
	}
	
	@RequestMapping(value = "userselect")
	public ModelAndView userselect() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/staff/userselect.ftl");
		return modelAndView;
	}
	public static void main(String[] args) {
//		Md5PasswordEncoder md5 = new Md5PasswordEncoder();       
//        // false 表示：生成32位的Hex版, 这也是encodeHashAsBase64的, Acegi 默认配置; true  表示：生成24位的Base64版       
//        md5.setEncodeHashAsBase64(false);       
//        String pwd = md5.encodePassword("admin", null);       
//        System.out.println("MD5: " + pwd + " len=" + pwd.length());  
        
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();       
        md5.setEncodeHashAsBase64(false);       
               
        // 使用动态加密盐的只需要在注册用户的时候将第二个参数换成用户名即可       
        String pwd = md5.encodePassword("admin", "admin");       
        System.out.println("MD5 SystemWideSaltSource: " + pwd + " len=" + pwd.length());   
	}
}
