package com.cloudcode.organization.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cloudcode.framework.dao.BaseModelObjectDao;
import com.cloudcode.framework.dao.TreeNodeModelObjectDao;
import com.cloudcode.framework.utils.HQLObjectParamList;
import com.cloudcode.framework.utils.HQLParamList;
import com.cloudcode.framework.utils.PageRange;
import com.cloudcode.framework.utils.PaginationSupport;
import com.cloudcode.framework.utils.UUID;
import com.cloudcode.organization.ProjectConfig;
import com.cloudcode.organization.model.Organization;

@Repository
public class OrganizationDao extends BaseModelObjectDao<Organization> {

	@Resource(name = ProjectConfig.PREFIX + "organizationDao")
	private TreeNodeModelObjectDao<Organization> organizationDao;
	
	public void addOrganization(Organization entity) {
		if(null != entity.getId() && "".equals(entity.getId())){
			entity.setId(UUID.generateUUID());
		}
		organizationDao.createObject(entity);
	}
	
	public PaginationSupport<Organization> queryPagingData(Organization hhXtCd, PageRange pageRange) {
		HQLParamList hqlParamList = new HQLParamList();
		List<Object> list=null;
		return this.queryPaginationSupport(Organization.class, hqlParamList, pageRange);
	}
	
	public  List<Organization> queryDataTreeByPid(String node) {
		HQLObjectParamList hqlParamList = new HQLObjectParamList()
				.addCondition(Restrictions.eq("node", node));
				
		List<Organization> orgs = organizationDao.queryTreeList(Organization.class, hqlParamList);
		return orgs;
	}
}
