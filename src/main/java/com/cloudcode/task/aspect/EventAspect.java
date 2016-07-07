package com.cloudcode.task.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
 
@Component
@Aspect
public class EventAspect {
	private static Log logger = LogFactory.getLog(EventAspect.class);
	
	/*@Pointcut("execution(public * com.cloudcode.task.dao.TaskConfigDao.addTaskConfig(..))")
    public void myMethod(){};

    @Before("execution(public void com.bjsxt.dao.impl.UserDAOImpl.save(com.bjsxt.model.User))")
    @Before("myMethod()")
    public void before() {
        System.out.println("method staet");
    } 
    @After("myMethod()")
    public void after() {
        System.out.println("method after");
    } */
    
	@Before("execution(public void com.cloudcode.task.dao.TaskConfigDao.addTaskConfig(..))")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("*********************addTaskConfig****@Before*************************");
		System.out.println("logBefore() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("**************************************************");
		logger.error("***********************************");
	}
	@After("execution(* com.cloudcode.task.dao.TaskConfigDao.addTaskConfig(..))")
    public void logAfterV1(JoinPoint joinPoint) 
    {
		logger.error("*************addTaskConfig*@After********************");
        System.out.println("EmployeeCRUDAspect.logAfterV1() : " + joinPoint.getSignature().getName());
        logger.error("***********************************");
    }
	
	@After("execution(* com.cloudcode.task.dao.TaskConfigDao.createTaskConfig(..))")
    public void logAfterV4(JoinPoint joinPoint) 
    {
		logger.error("*****************createTaskConfig*@After****************");
        System.out.println("EmployeeCRUDAspect.logAfterV1() : " + joinPoint.getSignature().getName());
    }
	@Before("execution(* com.cloudcode.task.dao.TaskConfigDao.createTaskConfig(..))")
	public void logBefore5(JoinPoint joinPoint) {
		System.out.println("*********************createTaskConfig******@Before***********************");
		System.out.println("logBefore() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("**************************************************");
		logger.error("***********************************");
	}
}

