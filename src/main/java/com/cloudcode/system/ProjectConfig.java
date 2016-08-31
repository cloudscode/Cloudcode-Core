package com.cloudcode.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.cloudcode.framework.annotation.ModuleConfig;
import com.cloudcode.framework.bean.ProjectBeanNameGenerator;
import com.cloudcode.framework.dao.ModelObjectDao;
import com.cloudcode.framework.dao.impl.BaseDaoImpl;
import com.cloudcode.system.model.SystemFile;

@ModuleConfig(name=ProjectConfig.NAME,domainPackages={"com.cloudcode.system.model"})
@ComponentScan(basePackages={"com.cloudcode.system.*"},nameGenerator=ProjectBeanNameGenerator.class)
@PropertySource(name="cloudcode.evn",value={"classpath:proj.properties"})
public class ProjectConfig {
	public static final String NAME="system";
	public static final String PREFIX=NAME+".";
	
	@Bean(name=PREFIX+"systemFileDao")
	public ModelObjectDao<SystemFile> generateSystemFileDao(){
		return new BaseDaoImpl<SystemFile>(SystemFile.class);
	}
}
