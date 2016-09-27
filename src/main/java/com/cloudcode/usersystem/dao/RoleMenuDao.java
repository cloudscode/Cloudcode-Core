package com.cloudcode.usersystem.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloudcode.common.security.UserPrincipal;
import com.cloudcode.framework.dao.BaseModelObjectDao;
import com.cloudcode.framework.dao.ModelObjectDao;
import com.cloudcode.framework.utils.HQLParamList;
import com.cloudcode.framework.utils.PageRange;
import com.cloudcode.framework.utils.PaginationSupport;
import com.cloudcode.framework.utils.StringUtils;
import com.cloudcode.framework.utils.UUID;
import com.cloudcode.framework.utils.dao.ParamFactory;
import com.cloudcode.menu.dao.MenuDao;
import com.cloudcode.menu.model.Menu;
import com.cloudcode.usersystem.ProjectConfig;
import com.cloudcode.usersystem.model.RoleMenu;
import com.cloudcode.usersystem.model.User;

@Repository
public class RoleMenuDao extends BaseModelObjectDao<RoleMenu> {

	@Resource(name = ProjectConfig.PREFIX + "roleDao")
	private ModelObjectDao<RoleMenu> roleDao;
	@Autowired
	MenuDao menuDao;
	public void addRoleMenu(RoleMenu entity) {
		if(null == entity.getId() || "".equals(entity.getId())){
			entity.setId(UUID.generateUUID());
		}
		roleDao.createObject(entity);
	}
	
	public PaginationSupport<RoleMenu> queryPagingData(RoleMenu hhXtCd, PageRange pageRange) {
		HQLParamList hqlParamList = new HQLParamList();
		List<Object> list=null;
		return this.queryPaginationSupport(RoleMenu.class, hqlParamList, pageRange);
	}
	public List<Menu> getUserRoleMenu(User user,UserPrincipal userPrincipal) {
		List<Menu> menus=new ArrayList<Menu>();
		List<RoleMenu> roleMenus=new ArrayList<RoleMenu>();
		List<String> menuIds = new ArrayList<String>();
		List<String> roleIds = new ArrayList<String>();
		if(!StringUtils.isEmpty(user.getRoleIds())){
			for(String roleId:user.getRoleIds().split(",")){
				roleIds.add(roleId);
			}
			roleMenus= roleDao.queryList(RoleMenu.class, ParamFactory.getParamHb().in("roleId", roleIds));
			if(!StringUtils.isEmpty(user.getRoleIds())){
				userPrincipal.setRoleMenus(roleMenus);
				for(RoleMenu roleMenu:roleMenus){
					menuIds.add(roleMenu.getMenuId());
				}
			
				menus=menuDao.queryList(Menu.class, "id",menuIds);
				userPrincipal.setMenus(menus);
			}
		}
		return menus;
	}
}
