package com.cloudcode.tk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.cloudcode.framework.annotation.ModuleConfig;
import com.cloudcode.framework.bean.ProjectBeanNameGenerator;
import com.cloudcode.framework.dao.ModelObjectDao;
import com.cloudcode.framework.dao.TreeNodeModelObjectDao;
import com.cloudcode.framework.dao.impl.BaseDaoImpl;
import com.cloudcode.framework.dao.impl.BaseTreeNodeDaoImpl;
import com.cloudcode.tk.model.Category;
import com.cloudcode.tk.model.Comment;
import com.cloudcode.tk.model.Contact;
import com.cloudcode.tk.model.Train;

@ModuleConfig(name=ProjectConfig.NAME,domainPackages={"com.cloudcode.tk.model"})
@ComponentScan(basePackages={"com.cloudcode.tk.*"},nameGenerator=ProjectBeanNameGenerator.class)
@PropertySource(name="cloudcode.evn",value={"classpath:proj.properties"})
public class ProjectConfig {
public static final String NAME="tk";
public static final String PREFIX=NAME+".";
	
	@Bean(name=PREFIX+"contactDao")
	public ModelObjectDao<Contact> generateContactDao(){
		return new BaseDaoImpl<Contact>(Contact.class);
	}
	@Bean(name=PREFIX+"commentDao")
	public ModelObjectDao<Comment> generateCommentDao(){
		return new BaseDaoImpl<Comment>(Comment.class);
	}

	@Bean(name=PREFIX+"trainDao")
	public ModelObjectDao<Train> generateTrainDao(){
		return new BaseDaoImpl<Train>(Train.class);
	}
	
	@Bean(name=PREFIX+"categoryDao")
	public TreeNodeModelObjectDao<Category> generateCategoryDao(){
		return new BaseTreeNodeDaoImpl<Category>(Category.class);
	}
	
}
