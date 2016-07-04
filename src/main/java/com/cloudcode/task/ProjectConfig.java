package com.cloudcode.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.cloudcode.framework.annotation.ModuleConfig;
import com.cloudcode.framework.bean.ProjectBeanNameGenerator;
import com.cloudcode.framework.dao.ModelObjectDao;
import com.cloudcode.framework.dao.impl.BaseDaoImpl;
import com.cloudcode.task.model.TaskConfig;

@ModuleConfig(name=ProjectConfig.NAME,domainPackages={"com.cloudcode.task.model"})
@ComponentScan(basePackages={"com.cloudcode.task.*"},nameGenerator=ProjectBeanNameGenerator.class)
@PropertySource(name="cloudcode.evn",value={"classpath:proj.properties"})
public class ProjectConfig {
	public static final String NAME="task";
	public static final String PREFIX=NAME+".";
	
	@Bean(name=PREFIX+"taskConfigDao")
	public ModelObjectDao<TaskConfig> generateTaskConfigDao(){
		return new BaseDaoImpl<TaskConfig>(TaskConfig.class);
	}
}
