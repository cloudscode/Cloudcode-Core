package com.cloudcode.menu.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.cloudcode.framework.dao.TreeNodeModelObjectDao;
import com.cloudcode.framework.dao.impl.BaseTreeNodeDaoImpl;
import com.cloudcode.framework.utils.HQLObjectParamList;
import com.cloudcode.framework.utils.HQLParamList;
import com.cloudcode.framework.utils.PageRange;
import com.cloudcode.framework.utils.PaginationSupport;
import com.cloudcode.framework.utils.UUID;
import com.cloudcode.menu.ProjectConfig;
import com.cloudcode.menu.model.Menu;
import com.cloudcode.usersystem.model.User;

@Repository
@Transactional
public class MenuDao extends BaseTreeNodeDaoImpl<Menu> {
	
	@Autowired  
	private RedisTemplate redisTemplate;
	
	@Resource(name = ProjectConfig.PREFIX + "menuDao")
	private TreeNodeModelObjectDao<Menu> menuDao;
	
	public void addMenu(Menu entity) {
		if(null != entity.getId() && "".equals(entity.getId())){
			entity.setId(UUID.generateUUID());
		}
		menuDao.createObject(entity);
	}
	public void test(){
		String key=UUID.generateUUID();
		final User user2 = new User();
		user2.setId(key);
		user2.setName("test");
		redisTemplate.execute(new RedisCallback<Object>() {
			//@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(
						redisTemplate.getStringSerializer().serialize(
								"user.uid." + user2.getId()),
						redisTemplate.getStringSerializer().serialize(
								user2.getName()));
				System.out.println("*********");
				return null;
			}
		});
	}
	public  List<Menu> queryDataTreeByPid(String node) {
		HQLObjectParamList hqlParamList = new HQLObjectParamList()
				.addCondition(Restrictions.eq("node", node));
				
		List<Menu> menus = menuDao.queryTreeList(Menu.class, hqlParamList);
		return menus;
	}
	@Transactional
	public void deleteMenu(Menu entity) {
		menuDao.deleteObject(entity);
	}
	public PaginationSupport<Menu> queryPagingData(Menu hhXtCd, PageRange pageRange) {
		HQLParamList hqlParamList = new HQLParamList();
		/*if (!Check.isEmpty(hhXtCd.getText())) {
			hqlParamList.add(Restrictions.like("text", "%" + hhXtCd.getText()
					+ "%"));
		}
		if (!Check.isEmpty(hhXtCd.getLeaf())) {
			hqlParamList.add(Restrictions.eq("leaf", 1));
		}

		if (!Check.isEmpty(hhXtCd.getNode())) {
			hqlParamList.add(Restrictions.eq("node", hhXtCd.getNode()));
		}

		if (!Check.isEmpty(hhXtCd.getOrgCode())) {
			DetachedCriteria ownerCriteria = DetachedCriteria
					.forEntityName(Organization.class.getName());
			ownerCriteria.setProjection(Property.forName("id"));
			ownerCriteria.add(Restrictions.like("code_", hhXtCd.getOrgCode()
					+ "%"));
			hqlParamList.add(Restrictions.or(
					Property.forName("vorgid").in(ownerCriteria), Property
							.forName("vorgid").isNull()));
		}*/List<Object> list=null;
		return this.queryPaginationSupport(Menu.class, hqlParamList, pageRange);
	}
}
