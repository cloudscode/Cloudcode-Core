package com.cloudcode.tk.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cloudcode.framework.dao.ModelObjectDao;
import com.cloudcode.framework.utils.HibernateUUId;
import com.cloudcode.tk.ProjectConfig;
import com.cloudcode.tk.model.Contact;

@Repository
public class ContactDao {
	@Resource(name=ProjectConfig.PREFIX+"contactDao")
	ModelObjectDao<Contact> contactDao;
	public Object create(Contact entity){
		if(entity.getId()==null){
			entity.setId((String) new HibernateUUId().generate());
		}
		return contactDao.createObject(entity);
	}
	public List<Contact> createAll(List<Contact> forCreate){
		for(Contact entity:forCreate){
			contactDao.createObject(entity);
		}
		return forCreate;
	}
	public List<Contact> getAll(){
		return contactDao.loadAll();
	}
	public void delete(String id){
		contactDao.deleteObject(id);
	}
}
