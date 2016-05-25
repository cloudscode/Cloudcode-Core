package com.cloudcode.tk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudcode.tk.dao.ContactDao;
import com.cloudcode.tk.model.Contact;

@Service
public class ContactService {
	@Autowired
	private ContactDao contactDao;
	public Object create(Contact entity){
		return contactDao.create(entity);
	}
	public List<Contact> createAll(List<Contact> forCreate){
		return contactDao.createAll(forCreate);
	}
	public List<Contact> getAll(){
		return contactDao.getAll();
	}
	public void delete(String id){
		contactDao.delete(id);
	}
}
