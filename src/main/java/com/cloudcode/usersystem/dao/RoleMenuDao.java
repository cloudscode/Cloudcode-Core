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
import com.cloudcode.usersystem.model.RoleMenu;

@Repository
public class RoleMenuDao extends BaseModelObjectDao<RoleMenu> {

	@Resource(name = ProjectConfig.PREFIX + "roleDao")
	private ModelObjectDao<RoleMenu> roleDao;
	
	public void addRoleMenu(RoleMenu entity) {
		if(null == entity.getId() || "".equals(entity.getId())){
			entity.setId(UUID.generateUUID());
		}
		roleDao.createObject(entity);
	}
	
	public PaginationSupport<RoleMenu> queryPagingData(RoleMenu hhXtCd, PageRange pageRange) {
		HQLParamList hqlParamList = new HQLParamList();
		List<Object> list=null;
		return this.queryPaginationSupport(RoleMenu.class, hqlParamList, pageRange);
	}
}
