package com.cloudcode.task.init;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudcode.framework.utils.Initializer;
import com.cloudcode.task.dao.TaskConfigDao;
import com.cloudcode.task.model.TaskConfig;

@Service
public class init implements Initializer {

	private static final Logger log = Logger.getLogger(init.class);
	public static String webPath = "";
	@Autowired
	private TaskConfigDao taskConfigDao;

	@PostConstruct
	public void initialize() {
		String webPath = this.getClass().getClassLoader().getResource("/")
				.getPath().replace("/WEB-INF/classes/", "");
		init.webPath = webPath.substring(0,
				webPath.lastIndexOf("/"));
		List<TaskConfig> taskConfigList = taskConfigDao.findByProperty("valid", 1);
		for (TaskConfig taskConfig : taskConfigList) {
			taskConfigDao.addTaskInfo(taskConfig);
		}
	}

}
