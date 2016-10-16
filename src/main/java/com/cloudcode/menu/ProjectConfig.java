package com.cloudcode.menu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.cloudcode.framework.annotation.ModuleConfig;
import com.cloudcode.framework.bean.ProjectBeanNameGenerator;
import com.cloudcode.framework.dao.TreeNodeModelObjectDao;
import com.cloudcode.framework.dao.impl.BaseTreeNodeDaoImpl;
import com.cloudcode.menu.model.Menu;

@ModuleConfig(name=ProjectConfig.NAME,domainPackages={"com.cloudcode.menu.model"})
@ComponentScan(basePackages={"com.cloudcode.menu.*,com.cloudcode.report.*"},nameGenerator=ProjectBeanNameGenerator.class)
@PropertySource(name="cloudcode.evn",value={"classpath:proj.properties"})
public class ProjectConfig {
	public static final String NAME="menu";
	public static final String PREFIX=NAME+".";
	
	@Bean(name=PREFIX+"menuDao")
	public TreeNodeModelObjectDao<Menu> generateMenuDao(){
		return new BaseTreeNodeDaoImpl<Menu>(Menu.class);
	}
}
