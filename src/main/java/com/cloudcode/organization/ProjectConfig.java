package com.cloudcode.organization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.cloudcode.framework.annotation.ModuleConfig;
import com.cloudcode.framework.bean.ProjectBeanNameGenerator;
import com.cloudcode.framework.dao.ModelObjectDao;
import com.cloudcode.framework.dao.TreeNodeModelObjectDao;
import com.cloudcode.framework.dao.impl.BaseDaoImpl;
import com.cloudcode.framework.dao.impl.BaseTreeNodeDaoImpl;
import com.cloudcode.organization.model.Department;
import com.cloudcode.organization.model.Group;
import com.cloudcode.organization.model.Job;
import com.cloudcode.organization.model.Organization;
import com.cloudcode.organization.model.Staff;

@ModuleConfig(name = ProjectConfig.NAME, domainPackages = { "com.cloudcode.organization.model" })
@ComponentScan(basePackages = { "com.cloudcode.organization.*" }, nameGenerator = ProjectBeanNameGenerator.class)
@PropertySource(name = "cloudcode.evn", value = { "classpath:proj.properties" })
public class ProjectConfig {
	public static final String NAME = "org";
	public static final String PREFIX = NAME + ".";

	@Bean(name = PREFIX + "staffDao")
	public ModelObjectDao<Staff> generateContactDao() {
		return new BaseDaoImpl<Staff>(Staff.class);
	}

	@Bean(name=PREFIX+"departmentDao")
	public TreeNodeModelObjectDao<Department> generateDepartmentDao(){
		return new BaseTreeNodeDaoImpl<Department>(Department.class);
	}
	
	@Bean(name=PREFIX+"jobDao")
	public TreeNodeModelObjectDao<Job> generateJobDao(){
		return new BaseTreeNodeDaoImpl<Job>(Job.class);
	}
	
	@Bean(name=PREFIX+"groupDao")
	public TreeNodeModelObjectDao<Group> generateGroupDao(){
		return new BaseTreeNodeDaoImpl<Group>(Group.class);
	}
	
	@Bean(name=PREFIX+"organizationDao")
	public TreeNodeModelObjectDao<Organization> generateOrganizationDao(){
		return new BaseTreeNodeDaoImpl<Organization>(Organization.class);
	}
}
