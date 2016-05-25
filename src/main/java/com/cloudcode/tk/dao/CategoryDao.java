package com.cloudcode.tk.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cloudcode.framework.dao.TreeNodeModelObjectDao;
import com.cloudcode.framework.dao.impl.BaseTreeNodeDaoImpl;
import com.cloudcode.framework.utils.HQLObjectParamList;
import com.cloudcode.framework.utils.HQLParamList;
import com.cloudcode.framework.utils.PageRange;
import com.cloudcode.framework.utils.PaginationSupport;
import com.cloudcode.framework.utils.UUID;
import com.cloudcode.tk.ProjectConfig;
import com.cloudcode.tk.model.Category;

@Repository
public class CategoryDao extends BaseTreeNodeDaoImpl<Category> {
	@Resource(name = ProjectConfig.PREFIX + "categoryDao")
	private TreeNodeModelObjectDao<Category> categoryDao;

	public void addCategory(Category entity) {
		if (null != entity.getId() && "".equals(entity.getId())) {
			entity.setId(UUID.generateUUID());
		}
		categoryDao.createObject(entity);
	}

	public List<Category> queryDataTreeByPid(String node) {
		HQLObjectParamList hqlParamList = new HQLObjectParamList()
				.addCondition(Restrictions.eq("node", node));

		List<Category> categorys = categoryDao.queryTreeList(Category.class, hqlParamList);
		return categorys;
	}

	@Transactional
	public void deleteCategory(Category entity) {
		categoryDao.deleteObject(entity);
	}

	public PaginationSupport<Category> queryPagingData(Category hhXtCd,
			PageRange pageRange) {
		HQLParamList hqlParamList = new HQLParamList();
		/*
		 * if (!Check.isEmpty(hhXtCd.getText())) {
		 * hqlParamList.add(Restrictions.like("text", "%" + hhXtCd.getText() +
		 * "%")); } if (!Check.isEmpty(hhXtCd.getLeaf())) {
		 * hqlParamList.add(Restrictions.eq("leaf", 1)); }
		 * 
		 * if (!Check.isEmpty(hhXtCd.getNode())) {
		 * hqlParamList.add(Restrictions.eq("node", hhXtCd.getNode())); }
		 * 
		 * if (!Check.isEmpty(hhXtCd.getOrgCode())) { DetachedCriteria
		 * ownerCriteria = DetachedCriteria
		 * .forEntityName(Organization.class.getName());
		 * ownerCriteria.setProjection(Property.forName("id"));
		 * ownerCriteria.add(Restrictions.like("code_", hhXtCd.getOrgCode() +
		 * "%")); hqlParamList.add(Restrictions.or(
		 * Property.forName("vorgid").in(ownerCriteria), Property
		 * .forName("vorgid").isNull())); }
		 */List<Object> list = null;
		return this.queryPaginationSupport(Category.class, hqlParamList, pageRange);
	}
}
