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
import com.cloudcode.organization.model.Group;

@Repository
public class GroupDao extends BaseModelObjectDao<Group> {

	@Resource(name = ProjectConfig.PREFIX + "groupDao")
	private TreeNodeModelObjectDao<Group> groupDao;
	
	public void addGroup(Group entity) {
		groupDao.createObject(entity);
	}
	
	public PaginationSupport<Group> queryPagingData(Group hhXtCd, PageRange pageRange) {
		HQLParamList hqlParamList = new HQLParamList();
		List<Object> list=null;
		return this.queryPaginationSupport(Group.class, hqlParamList, pageRange);
	}
	public  List<Group> queryDataTreeByPid(String node) {
		HQLObjectParamList hqlParamList = new HQLObjectParamList()
				.addCondition(Restrictions.eq("idCode", node));
				
		List<Group> groups = groupDao.queryTreeList(Group.class, hqlParamList);
		return groups;
	}
}
