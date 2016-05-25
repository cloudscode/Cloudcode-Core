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
import com.cloudcode.usersystem.model.Acl;

@Repository
public class AclDao extends BaseModelObjectDao<Acl> {
	
	@Resource(name=ProjectConfig.PREFIX+"aclDao")	
	private ModelObjectDao<Acl> aclDao;
	
	public void addAcl(Acl entity) {
		if(null != entity.getId() && "".equals(entity.getId())){
			entity.setId(UUID.generateUUID());
		}
		aclDao.createObject(entity);
	}
	
	public PaginationSupport<Acl> queryPagingData(Acl hhXtCd, PageRange pageRange) {
		HQLParamList hqlParamList = new HQLParamList();
		List<Object> list=null;
		return this.queryPaginationSupport(Acl.class, hqlParamList, pageRange);
	}
}
