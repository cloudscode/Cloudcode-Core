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
import com.cloudcode.organization.model.Job;

@Repository
public class JobDao extends BaseModelObjectDao<Job> {

	@Resource(name = ProjectConfig.PREFIX + "jobDao")
	private TreeNodeModelObjectDao<Job> jobDao;
	
	public void addJob(Job entity) {
		if(null != entity.getId() && "".equals(entity.getId())){
			entity.setId(UUID.generateUUID());
		}
		jobDao.createObject(entity);
	}
	
	public PaginationSupport<Job> queryPagingData(Job hhXtCd, PageRange pageRange) {
		HQLParamList hqlParamList = new HQLParamList();
		List<Object> list=null;
		return this.queryPaginationSupport(Job.class, hqlParamList, pageRange);
	}
	public  List<Job> queryDataTreeByPid(String node) {
		HQLObjectParamList hqlParamList = new HQLObjectParamList()
				.addCondition(Restrictions.eq("idCode", node));
				
		List<Job> jobs = jobDao.queryTreeList(Job.class, hqlParamList);
		return jobs;
	}
}
