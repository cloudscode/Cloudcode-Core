package com.cloudcode.tk.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
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
import com.cloudcode.tk.dao.CategoryDao;
import com.cloudcode.tk.model.Category;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

@Controller
@RequestMapping("/categories")
public class CategoryController extends CrudController<Category> {

	@Autowired
	private CategoryDao categoryDao;

	@RequestMapping(value = "/loadAll", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	List<Category> LoadAll() {
		return categoryDao.loadAll();
	}

	@RequestMapping(value = "Categoryslist.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Object getCategoryList() {
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
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println(gson.toJson(categoryDao.loadAll()));
		return jsonObject.toString();
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public @ResponseBody
	void addCategory(@RequestBody Category category) {
		category.setId(UUID.generateUUID());
		categoryDao.addCategory(category);
	}


	@RequestMapping(value = "/createCategory", method = RequestMethod.POST)
	public @ResponseBody
	void createCategory(@ModelAttribute Category category, HttpServletRequest request) {
		String text = request.getParameter("text");
		categoryDao.addCategory(category);
	}

	@RequestMapping(value = "/{id}/updateCategory", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	Object updateCategory(@PathVariable("id") String id,
			@ModelAttribute Category updateObject, HttpServletRequest request) {
		Category category = categoryDao.loadObject(id);
		if (category != null) {
			BeanUpdater.copyProperties(updateObject, category);
			// org.springframework.beans.BeanUtils.copyProperties(updateObject,
			// category);
			categoryDao.updateObject(category);
			return new ServiceResult(ReturnResult.SUCCESS);
		}
		return null;
	}

	@RequestMapping(value = "categoryList")
	public ModelAndView categoryList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/tk/ftl/category/list.ftl");
		modelAndView.addObject("result", "cloudcode");
		return modelAndView;
	}

	@RequestMapping(value = "create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/tk/ftl/category/detail.ftl");
		modelAndView.addObject("result", "cloudcode");
		modelAndView.addObject("entityAction", "create");
		return modelAndView;
	}

	@RequestMapping(value = "/{id}/update")
	public ModelAndView update(@PathVariable("id") String id) {
		Category category = categoryDao.loadObject(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/tk/ftl/category/detail.ftl");
		modelAndView.addObject("category", category);
		modelAndView.addObject("result", "cloudcode");
		modelAndView.addObject("entityAction", "update");
		return modelAndView;
	}

	@RequestMapping(value = "query", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	PaginationSupport<Category> query(Category category, PageRange pageRange) {
		PaginationSupport<Category> categorys = categoryDao
				.queryPagingData(category, pageRange);
		/*
		 * List<Category> lists = categoryDao. categorys.setRows(lists); categorys.setPage(1);
		 * categorys.setRecords(lists.size()); categorys.setTotal(1);
		 */
		return categorys;
	}

	@RequestMapping(value = "/deleteAll")
	public @ResponseBody
	Object deleteAll(HttpServletRequest request) {
		String ids = request.getParameter("ids");
		String[] arrayId = ids.split(",");
		for (String id : arrayId) {
			categoryDao.deleteObject(id);
		}
		return new ServiceResult(ReturnResult.SUCCESS);
	}

	@RequestMapping(value = "queryDataTreeByPid", method = {
			RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public @ResponseBody
	JSONArray queryDataTreeByPid(HttpServletRequest request) {
		String id = request.getParameter("id");
		List<Category> lists = null;
		if (StringUtils.isEmpty(id)) {
			lists = categoryDao.queryDataTreeByPid("root");
		} else {
			lists = categoryDao.queryDataTreeByPid(id);
		}
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();

		for (Category category : lists) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("id", category.getId());
			maps.put("name", category.getName());
			maps.put("pId", category.getNode());
			maps.put("isParent", true);
			listMap.add(maps);
		}
		return JSONArray.fromObject(listMap);
	}
	@RequestMapping(value = "queryTree", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Object queryTree(Category category, PageRange pageRange, HttpServletRequest request) {
		String nodeid =request.getParameter("nodeid");
		String n_level =request.getParameter("n_level");
		List<Category> lists = null;
		if(StringUtils.isEmpty(nodeid)){
			lists = categoryDao.queryDataTreeByPid("root");
		}else{
			lists = categoryDao.queryDataTreeByPid(nodeid);
		}
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (Category obj : lists) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("id", obj.getId());
			maps.put("name", obj.getName());
			//maps.put("action", obj.getAction());
			maps.put("node", obj.getNode());
			maps.put("expanded", false);
			maps.put("isLeaf", false);
			maps.put("parent", obj.getNode());
			maps.put("level", n_level==null?0:(Integer.parseInt(n_level)+1));
			listMap.add(maps);
		}
		return listMap;
	}
}
