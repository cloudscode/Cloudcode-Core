/**
 * Project Name:Cloudcode-Core
 * File Name:EventAspect.java
 * Package Name:com.cloudcode.task.aspect
 * Date:2016-7-4下午5:05:23
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.cloudcode.task.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * ClassName:EventAspect <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016-7-4 下午5:05:23 <br/>
 * @author   cloudscode   ljzhuanjiao@Gmail.com 
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Aspect
public class EventAspect {
	@Before("execution(* com.cloudcode.task.mvc.TaskConfigController.createTaskConfig(..))")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("**************************************************");
		System.out.println("logBefore() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("**************************************************");
	}
}

