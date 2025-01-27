package com.ashutosh.learn_spring_aop_maven.aopexample.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//Configuration
//APO
@Configuration
@Aspect
public class LoggingAspect {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	// pointcut - when?
	//execution(* PACKAGE.*.*(..))
	// com.ashutosh.learn_spring_aop_maven.aopexample.business
	// execution(* com.ashutosh.learn_spring_aop_maven.aopexample.business.*.*(..))
	@Before("execution(* com.ashutosh.learn_spring_aop_maven.aopexample.aspects.CommonPointcutConfig.allPackageConfigUsingBean()") // When
	public void logMethodCallBeforExecution(JoinPoint joinPoint) {
		
		logger.info("Before Aspect {} - Method is called - {}", joinPoint, joinPoint.getArgs()); // what
		
		
	}
	
	@After("execution(* execution(* com.ashutosh.learn_spring_aop_maven.aopexample.aspects.CommonPointcutConfig.businessPackageConfig()") // When
	public void logMethodCallAfterExecution(JoinPoint joinPoint) {
		
		logger.info("After Aspect {} - has executed ", joinPoint); // what	
	}
	
	
	@AfterThrowing(pointcut =  "execution(* com.ashutosh.learn_spring_aop_maven.aopexample.aspects.CommonPointcutConfig.businessAndDataPackageConfig()",
			throwing = "exception") // When
	public void logMethodCallAfterException(JoinPoint joinPoint, Exception exception) {
		
		logger.info("After Throwing {} - has an exception {}", joinPoint, exception); // what	
	}
	
	
	@AfterReturning(pointcut =  "execution(* com.ashutosh.learn_spring_aop_maven.aopexample.aspects.CommonPointcutConfig.dataPackageConfig()",
			returning = "resultValue") // When
	public void logMethodCallAfterSucessfulExecution(JoinPoint joinPoint, Object resultValue) {
		
		logger.info("After Returning Aspect {} - has returned {}", joinPoint, resultValue); // what	
	}

}

//- [learn-spring-aop-maven] [  restartedMain] c.a.l.a.LoggingAspect$$SpringCGLIB$$0    : Before Aspect - Method is called - execution(int com.ashutosh.learn_spring_aop_maven.aopexample.business.BusinessService1.calculateMax())
//2025-01-27T10:59:52.097+05:30  INFO 12192 --- [learn-spring-aop-maven] [  restartedMain] c.a.l.a.LoggingAspect$$SpringCGLIB$$0    : Before Aspect - Method is called - execution(int[] com.ashutosh.learn_spring_aop_maven.aopexample.business.DataService1.retriveData())
//2025-01-27T10:59:52.101+05:30  INFO 12192 --- [learn-spring-aop-maven] [  restartedMain] pringAopMavenApplication$$SpringCGLIB$$0 : Value returned is 4
