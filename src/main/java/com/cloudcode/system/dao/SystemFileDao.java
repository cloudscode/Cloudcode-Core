package com.cloudcode.system.dao;

import java.util.List;

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
import com.cloudcode.system.ProjectConfig;
import com.cloudcode.system.model.SystemFile;
@Repository
public class SystemFileDao extends BaseModelObjectDao<SystemFile> {

	@Resource(name = ProjectConfig.PREFIX + "systemFileDao")
	private ModelObjectDao<SystemFile> systemFileDao;
	@Resource(name = "global.cacheManager")
	CacheManager cacheManager;

	public Cache<Object, Object> getCache() {
		return cacheManager.getCache(SystemFile.class.getName());
	}
	@Transactional
	public void addSystemFile(SystemFile entity) {
		if(null != entity.getId() && "".equals(entity.getId())){
			entity.setId(UUID.generateUUID());
		}
		systemFileDao.createObject(entity);
	}
	public void updateSystemFile(SystemFile entity) {
		systemFileDao.updateObject(entity);
	}

	public void deleteSystemFile(String id) {
		if (id != null && !"".equals(id)) {
			String[] idarr =	id.split(",");
			systemFileDao.deleteObjects(idarr);
		}
	}
	public PaginationSupport<SystemFile> queryPagingData(SystemFile hhXtCd, PageRange pageRange) {
		HQLParamList hqlParamList = new HQLParamList();
		List<Object> list=null;
		return this.queryPaginationSupport(SystemFile.class, hqlParamList, pageRange);
	}
	@Transactional
	public void createSystemFile(SystemFile entity) {
		if(null != entity.getId() && "".equals(entity.getId())){
			entity.setId(UUID.generateUUID());
		}
		systemFileDao.createObject(entity);
		//int q=1/0;
	}
	//@Cacheable(value="user") 
	public SystemFile loadObject(String id) {System.out.println("*******"+id);
		return systemFileDao.loadObject(id);
	}
	

}
