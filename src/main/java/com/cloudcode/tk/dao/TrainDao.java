package com.cloudcode.tk.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cloudcode.framework.dao.BaseModelObjectDao;
import com.cloudcode.framework.dao.ModelObjectDao;
import com.cloudcode.tk.ProjectConfig;
import com.cloudcode.tk.model.Train;

@Repository
public class TrainDao extends BaseModelObjectDao<Train> {
	@Resource(name=ProjectConfig.PREFIX+"trainDao")	
	 ModelObjectDao<Train> trainDao;
	
	public List<Train> getAll(){
		return trainDao.loadAll();
	}

	public void addTrain(Train entity) {
		trainDao.createObject(entity);
	}

	public void deleteTrainById(String id) {
		// TODO Auto-generated method stub
		trainDao.deleteObject(id);
	}

	public void updateTrain(Train entity) {
		// TODO Auto-generated method stub
		trainDao.updateObject(entity);
	}
	public void deleteAll(){
		trainDao.deleteAll();
	}
}


