package com.cloudcode.task.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cloudcode.framework.dao.BaseModelObjectDao;
import com.cloudcode.framework.dao.ModelObjectDao;
import com.cloudcode.framework.utils.HQLParamList;
import com.cloudcode.framework.utils.PageRange;
import com.cloudcode.framework.utils.PaginationSupport;
import com.cloudcode.framework.utils.UUID;
import com.cloudcode.task.ProjectConfig;
import com.cloudcode.task.model.TaskConfig;
@Repository
public class TaskConfigDao extends BaseModelObjectDao<TaskConfig> {

	@Resource(name = ProjectConfig.PREFIX + "taskConfigDao")
	private ModelObjectDao<TaskConfig> taskConfigDao;
	@Transactional
	public void addTaskConfig(TaskConfig entity) {
		if(null != entity.getId() && "".equals(entity.getId())){
			entity.setId(UUID.generateUUID());
		}
		taskConfigDao.createObject(entity);
	}
	
	public PaginationSupport<TaskConfig> queryPagingData(TaskConfig hhXtCd, PageRange pageRange) {
		HQLParamList hqlParamList = new HQLParamList();
		List<Object> list=null;
		return this.queryPaginationSupport(TaskConfig.class, hqlParamList, pageRange);
	}
	@Transactional
	public void createTaskConfig(TaskConfig entity) {
		if(null != entity.getId() && "".equals(entity.getId())){
			entity.setId(UUID.generateUUID());
		}
		taskConfigDao.createObject(entity);
		//int q=1/0;
	}
	//@Cacheable(value="user") 
	public TaskConfig loadObject(String id) {System.out.println("*******"+id);
		return taskConfigDao.loadObject(id);
	}
}
