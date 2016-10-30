package com.cloudcode.report.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/api/user")
public class ApiController {

	@RequestMapping(value="/addUser.json",method={RequestMethod.POST})
	public String addUser() {
		return "addUser";
	}
}
