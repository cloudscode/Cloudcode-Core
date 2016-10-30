/**
 * Project Name:Cloudcode-Core
 * File Name:MvcFilter.java
 * Package Name:com.cloudcode.common.filter
 * Date:2016-7-1下午4:50:00
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.cloudcode.common.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * ClassName:MvcFilter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016-7-1 下午4:50:00 <br/>
 * @author   cloudscode   ljzhuanjiao@Gmail.com 
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MvcFilter extends OncePerRequestFilter {

	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		//System.out.println("*************************************"+url+"--"+request.getSession().getAttribute("username"));
		if(url.indexOf("/login.html") >-1 || url.indexOf("/captcha")>-1 || url.indexOf("/cxfService")>-1 || url.indexOf("/resources")>-1
				|| url.indexOf("/rest/api/")>-1|| url.indexOf("/protected/saludo")>-1	|| url.indexOf("/oauth/token")>-1	
				){
			
		}else{
	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null || auth.getPrincipal() instanceof String){
			String str="会话超时！";
			response.setContentType("text/html;charset=UTF-8");
			try {
				PrintWriter writer = response.getWriter();
				writer.write(str);
				writer.flush();
				writer.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			return;
		}
		}
		filterChain.doFilter(request, response);
	}

}

