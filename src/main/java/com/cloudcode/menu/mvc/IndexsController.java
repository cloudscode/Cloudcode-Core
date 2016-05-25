package com.cloudcode.menu.mvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cloudcode.menu.dao.MenuDao;
import com.cloudcode.menu.model.Menu;

@Controller
@RequestMapping("/indexs")
public class IndexsController{

	@Autowired
	private MenuDao menuDao;
	
	@RequestMapping(value = "jqueryui")
	public ModelAndView jqueryui() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/menu/ftl/jqueryui.ftl");
		//modelAndView.addObject("result", "cloudcode");
		List<Menu> menus = menuDao.queryDataTreeByPid("root");
		modelAndView.addObject("menus", menus);
		
		List<String> hhxtcdIdList = new ArrayList<String>();

		
		List<Menu> hhXtCdList =  new ArrayList<Menu>();
		if (hhxtcdIdList.size()>0) {
			hhXtCdList = menuDao.queryList(Menu.class,
					"id", hhxtcdIdList);
		}
		
		return modelAndView;
	}

}
