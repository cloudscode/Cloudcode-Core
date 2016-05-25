package com.cloudcode.usersystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.cloudcode.framework.annotation.ModuleConfig;
import com.cloudcode.framework.bean.ProjectBeanNameGenerator;
import com.cloudcode.framework.dao.ModelObjectDao;
import com.cloudcode.framework.dao.impl.BaseDaoImpl;
import com.cloudcode.usersystem.model.Acl;
import com.cloudcode.usersystem.model.Role;
import com.cloudcode.usersystem.model.User;

@ModuleConfig(name=ProjectConfig.NAME,domainPackages={"com.cloudcode.usersystem.model"})
@ComponentScan(basePackages={"com.cloudcode.usersystem.*"},nameGenerator=ProjectBeanNameGenerator.class, excludeFilters={@org.springframework.context.annotation.ComponentScan.Filter({Configuration.class})})
@PropertySource(name="cloudcode.evn",value={"classpath:proj.properties"})
public class ProjectConfig {
public static final String NAME="us";
public static final String PREFIX=NAME+".";
	
	@Bean(name=PREFIX+"userDao")
	public ModelObjectDao<User> generateUserDao(){
		return new BaseDaoImpl<User>(User.class);
	}
	@Bean(name=PREFIX+"roleDao")
	public ModelObjectDao<Role> generateRoleDao(){
		return new BaseDaoImpl<Role>(Role.class);
	}
	@Bean(name=PREFIX+"aclDao")
	public ModelObjectDao<Acl> generateAclDao(){
		return new BaseDaoImpl<Acl>(Acl.class);
	}
}
