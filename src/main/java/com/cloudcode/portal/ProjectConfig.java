package com.cloudcode.portal;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.cloudcode.framework.annotation.ModuleConfig;
import com.cloudcode.framework.bean.ProjectBeanNameGenerator;

@ModuleConfig(name=ProjectConfig.NAME,domainPackages={"com.cloudcode.portal.model"})
@ComponentScan(basePackages={"com.cloudcode.portal.*"},nameGenerator=ProjectBeanNameGenerator.class)
@PropertySource(name="cloudcode.evn",value={"classpath:proj.properties"})
public class ProjectConfig {
public static final String NAME="tk";
public static final String PREFIX=NAME+".";
	
	
	
}
