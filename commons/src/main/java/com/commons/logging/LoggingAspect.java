/*
 * @author dhruv.aggarwal
 */
package com.commons.logging;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * The Class LoggingAspect.
 *
 * @author Dhruv
 */
@Aspect
@Component
public class LoggingAspect {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	/**
	 * Log method entry.
	 *
	 * @param joinPoint
	 *            the join point
	 */
	@Before("getNamePointcut()")
	public void logEntry(final JoinPoint joinPoint) {
		log("Entering method " + joinPoint.getSignature().toShortString() + " with args : "
				+ Arrays.toString(joinPoint.getArgs()));
	}

	/**
	 * Log method exit.
	 *
	 * @param joinPoint
	 *            the join point
	 */
	@After("getNamePointcut()")
	public void logExit(final JoinPoint joinPoint) {
		log("Exiting method " + joinPoint.getSignature().toShortString() + ".");
	}

	/**
	 * Log method exit after returning.
	 *
	 * @param joinPoint
	 *            the join point
	 * @param returningValue
	 *            the returning value
	 */
	@AfterReturning(value = "getNamePointcut()", returning = "returningValue")
	public void logExitAfterReturn(final JoinPoint joinPoint, Object returningValue) {
		log("Exiting method (after return) " + joinPoint.getSignature().toShortString() + ". Returned Value : "
				+ returningValue);
	}

	/**
	 * "Log" the provided String.
	 * 
	 * @param messageToLog
	 *            String to be logged.
	 */
	public static void log(final String messageToLog) {
		logger.info(messageToLog);
	}

	/**
	 * Gets the name pointcut.
	 *
	 * @return the name pointcut
	 */
	@Pointcut(" execution(* com.*..*(..)) && !execution(* com.*.exception..*(..)) && !execution(* com.*..*.*static*(..)) && !execution(* org.springframework..*(..))")
	public void getNamePointcut() {
		// To declare pointcut for application.
		// Method doesnt contains any code.
	}
}