package com.cloudcode.usersystem.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cloudcode.framework.dao.BaseModelObjectDao;
import com.cloudcode.framework.dao.ModelObjectDao;
import com.cloudcode.framework.utils.HQLParamList;
import com.cloudcode.framework.utils.PageRange;
import com.cloudcode.framework.utils.PaginationSupport;
import com.cloudcode.framework.utils.UUID;
import com.cloudcode.usersystem.ProjectConfig;
import com.cloudcode.usersystem.model.User;
@Repository
public class UserDao extends BaseModelObjectDao<User> {
	
	@Resource(name=ProjectConfig.PREFIX+"userDao")	
	private  ModelObjectDao<User> userDao;
	
	public void addUser(User entity) {
		if(null == entity.getId() || "".equals(entity.getId())){
			entity.setId(UUID.generateUUID());
		}
		userDao.createObject(entity);
	}
	
	public PaginationSupport<User> queryPagingData(User hhXtCd, PageRange pageRange) {
		HQLParamList hqlParamList = new HQLParamList();
		List<Object> list=null;
		return this.queryPaginationSupport(User.class, hqlParamList, pageRange);
	}
	public User findObjectByLoginId(String loginId) {
		return userDao.findObject("loginId",loginId);
	}
	public User findObjectById(String id) {
		User user = userDao.findEntityByPK(User.class, id);
		/*List<userDao> hhXtYhJsList = userDao.queryList(User.class,
				new HQLParamList().addCondition(Restrictions.eq("yhId", id)));

		String jsidsStr = "";
		for (UserJs hhXtYhJs : hhXtYhJsList) {
			hhXtYh.getJsList().add(hhXtYhJs.getJsId());
			jsidsStr += hhXtYhJs.getJsId() + ",";
		}

		if (Check.isNoEmpty(jsidsStr)) {
			jsidsStr = jsidsStr.substring(0, jsidsStr.length() - 1);
		}
		//hhXtYh.setJsIdsStr(jsidsStr);
		// editUser_orgList(hhXtYh);
		hhXtYh.setOrgIdsStr(findOrgIdsStr(hhXtYh.getId()));*/
		return user;
	}
}
