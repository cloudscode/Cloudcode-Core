package com.cloudcode.common.mvc;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cloudcode.menu.dao.MenuDao;
import com.cloudcode.menu.model.Menu;
import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
public class HomeController{
	 protected final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Resource
    private ImageCaptchaService imageCaptchaService;
    
   /* @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }*/
    @Autowired
	private MenuDao menuDao;
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView jqueryui() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classpath:com/cloudcode/menu/ftl/index.ftl");
		//modelAndView.addObject("result", "cloudcode");
		List<Menu> menus = menuDao.queryDataTreeByPid("root");
		modelAndView.addObject("menus", menus);
		
		List<String> hhxtcdIdList = new ArrayList<String>();

		
		List<Menu> hhXtCdList =  new ArrayList<Menu>();
		if (hhxtcdIdList.size()>0) {
			hhXtCdList = menuDao.queryList(Menu.class,
					"id", hhxtcdIdList);
		}
		
		return modelAndView;
	}
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void getCode(HttpServletRequest request, HttpServletResponse response) {

        try {
            ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
            String captchaId = request.getSession().getId();
            
            BufferedImage challenge = (BufferedImage) imageCaptchaService.getChallengeForID(captchaId,request.getLocale());
            
            ImageIO.write(challenge, "jpeg", jpegOutputStream);
            byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
            
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            ServletOutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(captchaChallengeAsJpeg);
            responseOutputStream.flush();
            responseOutputStream.close();
            
        } catch (Exception e) {
            logger.error("generate captcha image error: {}", e.getMessage());
        }
        
    }
    
}

