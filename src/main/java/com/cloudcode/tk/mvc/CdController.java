package com.cloudcode.tk.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloudcode.framework.controller.CrudController;
import com.cloudcode.tk.model.Contact;

@Controller
@RequestMapping("/Cd")
public class CdController extends CrudController<Contact>{

}
