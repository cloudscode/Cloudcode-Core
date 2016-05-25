package com.cloudcode.tk.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Admin")
public class AdminController {

	@RequestMapping
    public String getIndexPage() {
        return "datagrid";
    }
}
