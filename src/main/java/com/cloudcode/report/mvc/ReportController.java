package com.cloudcode.report.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/report")
public class ReportController{

	
	@RequestMapping(value = "echart")
	public ModelAndView echart() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/report/ftl/echart.ftl");
		 
		return modelAndView;
	}
	@RequestMapping(value = "highchart")
	public ModelAndView highchart() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/report/ftl/highchart.ftl");
		return modelAndView;
	}
}
