package com.cloudcode.task.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cloudcode.common.cache.Cache;
import com.cloudcode.common.cache.CacheManager;
import com.cloudcode.framework.dao.BaseModelObjectDao;
import com.cloudcode.framework.dao.ModelObjectDao;
import com.cloudcode.framework.utils.HQLParamList;
import com.cloudcode.framework.utils.PageRange;
import com.cloudcode.framework.utils.PaginationSupport;
import com.cloudcode.framework.utils.UUID;
import com.cloudcode.task.ProjectConfig;
import com.cloudcode.task.model.TaskConfig;
import com.cloudcode.task.utils.PlanTask;
@Repository
public class TaskConfigDao extends BaseModelObjectDao<TaskConfig> {

	@Resource(name = ProjectConfig.PREFIX + "taskConfigDao")
	private ModelObjectDao<TaskConfig> taskConfigDao;
	@Resource(name = "global.cacheManager")
	CacheManager cacheManager;

	public Cache<Object, Object> getCache() {
		return cacheManager.getCache(TaskConfig.class.getName());
	}
	@Transactional
	public void addTaskConfig(TaskConfig entity) {
		if(null != entity.getId() && "".equals(entity.getId())){
			entity.setId(UUID.generateUUID());
		}
		taskConfigDao.createObject(entity);
		addTaskInfo(entity);
	}
	public void updateTaskConfig(TaskConfig entity) {
		taskConfigDao.updateObject(entity);
		removeTaskInfo(entity.getId());
		addTaskInfo(entity);
	}

	public void deleteTaskConfig(String id) {
		if (id != null && !"".equals(id)) {
			String[] idarr =	id.split(",");
			taskConfigDao.deleteObjects(idarr);
			for (int i = 0; i < idarr.length; i++) {
				removeTaskInfo(idarr[i]);
			}
		}
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
	public void addTaskInfo(TaskConfig taskConfig) {
		if (taskConfig.getValid() == 1) {
			long time = taskConfig.getSecond() * 1000 + taskConfig.getMinute()
					* 60 * 1000 + taskConfig.getHour() * 60 * 60 * 1000;
			Timer timer = new Timer();
			if (taskConfig.getHourRegister() != 0
					|| taskConfig.getMinuteRegister() != 0
					|| taskConfig.getSecondRegister() != 0) {
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY, taskConfig.getHourRegister());
				calendar.set(Calendar.MINUTE, taskConfig.getMinuteRegister());
				calendar.set(Calendar.SECOND, taskConfig.getSecondRegister());
				timer.schedule(new PlanTask(taskConfig), calendar.getTime(),
						time);
			} else {
				timer.schedule(new PlanTask(taskConfig), 1000, time);
			}
			getCache().put(taskConfig.getId(), timer);
		}
	}

	private void removeTaskInfo(String id) {
		Object object = getCache().get(id);
		if (object != null) {
			Timer timer = (Timer) object;
			timer.cancel();
		}
	}
}
