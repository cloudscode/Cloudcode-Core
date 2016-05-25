package com.cloudcode.tk.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cloudcode.framework.dao.BaseModelObjectDao;
import com.cloudcode.framework.dao.ModelObjectDao;
import com.cloudcode.tk.ProjectConfig;
import com.cloudcode.tk.model.Comment;


@Repository
public class CommentDao extends BaseModelObjectDao<Comment> {


	@Resource(name=ProjectConfig.PREFIX+"commentDao")	
	ModelObjectDao<Comment> commentDao;
	
	public Object create(Comment comment){
		return commentDao.createObject(comment);
	}
	/*public List<Comment> listAll(){
		return commentDao.getAll();
	}*/

}
