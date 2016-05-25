package com.cloudcode.tk.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cloudcode.framework.controller.CrudController;
import com.cloudcode.framework.utils.UUID;
import com.cloudcode.tk.dao.CommentDao;
import com.cloudcode.tk.model.Comment;

@Controller
@RequestMapping("/comments")
public class CommentsController extends  CrudController<Comment>{// extends BaseModelObjectServiceSupport<Comment>

	@Autowired
	private CommentDao commentDao;

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("comments");
		List<Comment> comments = commentDao.loadAll();
		modelAndView.addObject("comments", comments);
		modelAndView.addObject("commentForm", new Comment());
		return modelAndView;
	}
	
	@RequestMapping(value = "getComment/{id}", method = RequestMethod.GET)
	public ModelAndView getComment(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("comment");
		for (Comment comment : commentDao.loadAll()) {
			if (id.equals(comment.getId())) {
				modelAndView.addObject("comment", comment);
			}
		}
		return modelAndView;
	}

	@RequestMapping(value= "postCommentForm",method = RequestMethod.POST)
	public String postCommentForm(@ModelAttribute Comment comment) {
		comment.setId(UUID.generateUUID());
		commentDao.create(comment);
		return "redirect:/comments";
	}

}
