package com.cloudcode.tk.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloudcode.framework.controller.CrudController;
import com.cloudcode.usersystem.model.User;

@Controller
@RequestMapping("/Ab")
public class AbController extends CrudController<User> {

	
}
