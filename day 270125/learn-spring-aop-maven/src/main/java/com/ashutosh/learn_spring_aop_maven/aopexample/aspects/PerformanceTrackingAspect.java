package com.ashutosh.learn_spring_aop_maven.aopexample.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class PerformanceTrackingAspect {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	//@Around("execution(* com.ashutosh.learn_spring_aop_maven.aopexample.*.*.*(..))")
	@Around("execution(* com.ashutosh.learn_spring_aop_maven.aopexample.aspects.CommonPointcutConfig.trackTimeAnnotation())")
	public Object findExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// Start timer 
		
		long startTimeMillis = System.currentTimeMillis();
		
		// Execute to Method
	    Object returnValue = proceedingJoinPoint.proceed();
		
		//stop the timer
		
		long stopTimeMillis = System.currentTimeMillis();
		
		long execDuration = stopTimeMillis - startTimeMillis;
		
		logger.info("Around Aspect -{} Method execution in {} ms",proceedingJoinPoint,execDuration);
		
		return returnValue;
		
	}
}
