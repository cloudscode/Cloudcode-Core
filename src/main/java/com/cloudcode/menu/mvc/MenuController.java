package com.cloudcode.menu.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cloudcode.framework.controller.CrudController;
import com.cloudcode.framework.rest.ReturnResult;
import com.cloudcode.framework.service.ServiceResult;
import com.cloudcode.framework.utils.BeanUpdater;
import com.cloudcode.framework.utils.PageRange;
import com.cloudcode.framework.utils.PaginationSupport;
import com.cloudcode.framework.utils.StringUtils;
import com.cloudcode.framework.utils.UUID;
import com.cloudcode.menu.dao.MenuDao;
import com.cloudcode.menu.model.Menu;
import com.cloudcode.usersystem.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/menus")
public class MenuController extends CrudController<Menu> {
	@Autowired
	private MenuDao menuDao;
	//@Autowired
	//private RedisTemplate redisTemplate;

	@RequestMapping(value = "/loadAll", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody List<Menu> LoadAll() {
		return menuDao.loadAll();
	}

	@RequestMapping(value = "Menuslist.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Object getMenuList() {
		Gson gson = new Gson();
		JSONObject jsonObject = new JSONObject();
		JsonArray js = new JsonArray();
		try {
			jsonObject.put("page", 1);
			jsonObject.put("records", 1);
			jsonObject.put("total", 1);
			jsonObject
					.put("rows",
							"{\"action\":\"e32324\",\"id\":\"cfbbd713ee3e4d90aeda010ae4f3f075\"}");
			//menuDao.test();
			String key = UUID.generateUUID();
			User user2 = new User();
			user2.setId(key);
			user2.setName("test");
			/*ValueOperations<String, User> valueops = redisTemplate
					.opsForValue();
			valueops.set(user2.getId(), user2);*/

			//User user = valueops.get(key);
			//System.out.println(user.getName());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println(gson.toJson(menuDao.loadAll()));
		return jsonObject.toString();
	}

	@RequestMapping(value = "/addMenu", method = RequestMethod.POST)
	public @ResponseBody void addMenu(@RequestBody Menu menu) {
		menu.setId(UUID.generateUUID());
		menuDao.addMenu(menu);
		
	}


	@RequestMapping(value = "/createMenu", method = RequestMethod.POST)
	public @ResponseBody Object createMenu(@ModelAttribute Menu menu,
			HttpServletRequest request) {
		menuDao.addMenu(menu);
		return new ServiceResult(ReturnResult.SUCCESS);
	}

	@RequestMapping(value = "/{id}/updateMenu", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody Object updateMenu(@PathVariable("id") String id,
			@ModelAttribute Menu updateObject, HttpServletRequest request) {
		Menu menu = menuDao.loadObject(id);
		if (menu != null) {
			BeanUpdater.copyProperties(updateObject, menu);
			// org.springframework.beans.BeanUtils.copyProperties(updateObject,
			// menu);
			menuDao.updateObject(menu);
			return new ServiceResult(ReturnResult.SUCCESS);
		}
		return new ServiceResult(ReturnResult.FAILURE);
	}

	@RequestMapping(value = "menuList")
	public ModelAndView menuList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/menu/ftl/list2.ftl");
		modelAndView.addObject("result", "cloudcode");
		return modelAndView;
	}	
	@RequestMapping(value = "create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/menu/ftl/detail.ftl");
		modelAndView.addObject("result", "cloudcode");
		modelAndView.addObject("entityAction", "create");
		return modelAndView;
	}

	@RequestMapping(value = "/{id}/update")
	public ModelAndView update(@PathVariable("id") String id) {
		Menu menu = menuDao.loadObject(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/menu/ftl/detail.ftl");
		modelAndView.addObject("menu", menu);
		modelAndView.addObject("result", "cloudcode");
		modelAndView.addObject("entityAction", "update");
		return modelAndView;
	}

	@RequestMapping(value = "query", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody PaginationSupport<Menu> query(Menu menu,
			PageRange pageRange) {
		PaginationSupport<Menu> menus = menuDao
				.queryPagingData(menu, pageRange);
		/*
		 * List<Menu> lists = menuDao. menus.setRows(lists); menus.setPage(1);
		 * menus.setRecords(lists.size()); menus.setTotal(1);
		 */
		return menus;
	}

	@RequestMapping(value = "/deleteAll")
	public @ResponseBody Object deleteAll(HttpServletRequest request) {
		String ids = request.getParameter("ids");
		String[] arrayId = ids.split(",");
		for (String id : arrayId) {
			menuDao.deleteObject(id);
		}
		return new ServiceResult(ReturnResult.SUCCESS);
	}

	@RequestMapping(value = "queryDataTreeByPid", method = {
			RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody JSONArray queryDataTreeByPid(HttpServletRequest request) {
		String id = request.getParameter("id");
		List<Menu> lists = null;
		if (StringUtils.isEmpty(id)) {
			lists = menuDao.queryDataTreeByPid("root");
		} else {
			lists = menuDao.queryDataTreeByPid(id);
		}
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();

		for (Menu menu : lists) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("id", menu.getId());
			maps.put("name", menu.getName());
			maps.put("pId", menu.getNode());
			maps.put("isParent", true);
			listMap.add(maps);
		}
		return JSONArray.fromObject(listMap);
	}

	@RequestMapping(value = "queryTree", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Object queryTree(Menu menu, PageRange pageRange,
			HttpServletRequest request) {
		String nodeid = request.getParameter("nodeid");
		String n_level = request.getParameter("n_level");
		List<Menu> lists = null;
		if (StringUtils.isEmpty(nodeid)) {
			lists = menuDao.queryDataTreeByPid("root");
		} else {
			lists = menuDao.queryDataTreeByPid(nodeid);
		}
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (Menu menu1 : lists) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("id", menu1.getId());
			maps.put("name", menu1.getName());
			maps.put("action", menu1.getAction());
			maps.put("node", menu1.getNode());
			maps.put("expanded", false);
			maps.put("isLeaf", false);
			maps.put("parent", menu1.getNode());
			maps.put("level", n_level == null ? 0
					: (Integer.parseInt(n_level) + 1));
			listMap.add(maps);
		}
		return listMap;
	}
	@RequestMapping(value = "/{id}/toDetail")
	public ModelAndView toDetail(@PathVariable("id") String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/organization/ftl/org/detail2.ftl");
		if("collection".equals(id)){
			modelAndView.addObject("entityAction", "create");
		}else{
			Menu obj = menuDao.loadObject(id);
			JSONObject json = JSONObject.fromObject(obj);
			modelAndView.addObject("entity",json.toString() );
			modelAndView.addObject("entityAction", "update");
		}
		return modelAndView;
	}
	@RequestMapping(value = "queryTreeList", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody Object queryTreeList() {
		List<Menu> lists = menuDao.findByProperty("idCode", "root");
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (Menu obj : lists) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("id", obj.getId());
			maps.put("name", obj.getName());
			maps.put("text", obj.getName());
			maps.put("pId", obj.getNode());
			maps.put("parent", obj.getNode());
			addChildren(maps,obj);
			listMap.add(maps);
		}
		return listMap;
	}
	private void addChildren(Map<String, Object> maps2,Menu entity){
		List<Menu> lists = menuDao.findByProperty("idCode", entity.getId());
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		if(lists.size()>0){
			for (Menu obj : lists) {
				Map<String, Object> maps = new HashMap<String, Object>();
				maps.put("id", obj.getId());
				maps.put("name", obj.getName());
				maps.put("text", obj.getName());
				maps.put("pId", obj.getNode());
				maps.put("parent", obj.getId());
				addChildren(maps,obj);
				listMap.add(maps);
			}
			maps2.put("children", listMap);
		}
	}
}
