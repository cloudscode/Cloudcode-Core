package com.cloudcode.organization.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cloudcode.framework.dao.BaseModelObjectDao;
import com.cloudcode.framework.dao.ModelObjectDao;
import com.cloudcode.framework.utils.HQLParamList;
import com.cloudcode.framework.utils.PageRange;
import com.cloudcode.framework.utils.PaginationSupport;
import com.cloudcode.framework.utils.UUID;
import com.cloudcode.organization.ProjectConfig;
import com.cloudcode.organization.model.Staff;

@Repository
public class StaffDao extends BaseModelObjectDao<Staff> {

	@Resource(name = ProjectConfig.PREFIX + "staffDao")
	private ModelObjectDao<Staff> staffDao;
	
	public void addStaff(Staff entity) {
		if(null != entity.getId() && "".equals(entity.getId())){
			entity.setId(UUID.generateUUID());
		}
		staffDao.createObject(entity);
	}
	
	public PaginationSupport<Staff> queryPagingData(Staff hhXtCd, PageRange pageRange) {
		HQLParamList hqlParamList = new HQLParamList();
		List<Object> list=null;
		return this.queryPaginationSupport(Staff.class, hqlParamList, pageRange);
	}
}
