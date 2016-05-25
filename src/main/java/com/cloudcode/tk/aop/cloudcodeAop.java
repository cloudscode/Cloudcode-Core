package com.cloudcode.tk.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class cloudcodeAop {
	@Pointcut("execution(* com.cloudcode.tk.service.*.*(..))")
	public void create(){
		System.out.println("cloudcode aop:");
	}
  @Around("create()")
  public Object profile(ProceedingJoinPoint pjp) throws Throwable {
          long start = System.currentTimeMillis();
          System.out.println("Going to call the method.");
          Object output = pjp.proceed();
          System.out.println("Method execution completed.");
          long elapsedTime = System.currentTimeMillis() - start;
          System.out.println("Method execution time: " + elapsedTime + " milliseconds.");
          return output;
  }
}
