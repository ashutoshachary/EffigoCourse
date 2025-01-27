package com.ashutosh.learn_spring_aop_maven.aopexample.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcutConfig {
	
	@Pointcut("execution(* com.ashutosh.learn_spring_aop_maven.aopexample.*.*.*(..))")
	public void businessAndDataPackageConfig() {
	}
	
	@Pointcut("execution(* com.ashutosh.learn_spring_aop_maven.aopexample.business.*.*(..))")
	public void businessPackageConfig() {
	}
	@Pointcut("execution(* com.ashutosh.learn_spring_aop_maven.aopexample.data.*.*(..))")
	public void dataPackageConfig() {
	}
	@Pointcut("bean(*Service*)")
	public void allPackageConfigUsingBean() {}
	
	@Pointcut("@annotation(* com.ashutosh.learn_spring_aop_maven.aopexample.business.TrackTime")
	public void trackTimeAnnotation() {
		
	}

}
