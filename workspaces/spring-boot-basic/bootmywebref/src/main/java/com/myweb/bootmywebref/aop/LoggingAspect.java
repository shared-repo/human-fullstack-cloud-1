package com.myweb.bootmywebref.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	@Before("execution(* com..service.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		
		System.out.printf("-----> BEFORE : %s.%s\n", 
				joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName());
		
	}
	
	@After("execution(* com..repository.*.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		
		System.out.printf("-----> AFTER : %s.%s\n", 
				joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName());
		
	}
	
	@Around("execution(* com..controller.*.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		
		long begin = System.currentTimeMillis(); // 현재 시간을 1/1000초 단위로 반환
		
		Object result = joinPoint.proceed();
		
		long end = System.currentTimeMillis();
		
		System.out.printf("-----> EXECUTION LAB : %s.%s : %d\n", 
				joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(),
				(end-begin));
		
		return result;
		
	}
	
}











