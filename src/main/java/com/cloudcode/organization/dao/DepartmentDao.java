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
import com.cloudcode.organization.model.Department;

@Repository
public class DepartmentDao extends BaseModelObjectDao<Department> {

	@Resource(name = ProjectConfig.PREFIX + "departmentDao")
	private TreeNodeModelObjectDao<Department> departmentDao;
	
	public void addDepartment(Department entity) {
		if(null != entity.getId() && "".equals(entity.getId())){
			entity.setId(UUID.generateUUID());
		}
		departmentDao.createObject(entity);
	}
	
	public PaginationSupport<Department> queryPagingData(Department hhXtCd, PageRange pageRange) {
		HQLParamList hqlParamList = new HQLParamList();
		List<Object> list=null;
		return this.queryPaginationSupport(Department.class, hqlParamList, pageRange);
	}
	public  List<Department> queryDataTreeByPid(String node) {
		HQLObjectParamList hqlParamList = new HQLObjectParamList()
				.addCondition(Restrictions.eq("idCode", node));
				
		List<Department> depts = departmentDao.queryTreeList(Department.class, hqlParamList);
		return depts;
	}
	
}
