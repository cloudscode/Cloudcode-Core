package com.cloudcode.tk.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Index")
public class IndexController {

	@RequestMapping
    public String getIndexPage() {
        return "index";
    }
}
