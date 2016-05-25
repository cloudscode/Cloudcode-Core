package com.cloudcode.tk.mvc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cloudcode.tk.model.Contact;
import com.cloudcode.tk.service.ContactService;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping("/restful")
	public String listContacts(Map<String, Object> map) {

		map.put("contact", new Contact());
		map.put("contactList", contactService.getAll());

		return "indexs";
	}
	@RequestMapping("/restfulJson")
	public Object list(Map<String, Object> map) {

		map.put("contact", new Contact());
		map.put("contactList", contactService.getAll());

		return map;
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("contact")
	Contact contact, BindingResult result) {
		//contact.setId(1);
		contactService.create(contact);

		return "redirect:/restful";
	}

	@RequestMapping("/delete/{contactId}")
	public String deleteContact(@PathVariable("contactId")
	String contactId) {

		contactService.delete(contactId);

		return "redirect:/restful";
	}
}