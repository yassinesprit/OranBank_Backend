package com.bfi.orabank.Aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;


@Component
@Aspect
@Slf4j
public class LoggingAspect {
    @Around("execution(* com.bfi.orabank.Services.*.*.*(..))")
     public Object handleExceptionServiceAnalysis(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        HandlerMethod handlerMethod = new HandlerMethod(joinPoint.getTarget(), signature.getMethod());

        try {
            return joinPoint.proceed();
        } catch (Exception ex) {
            log.info("Exception occurred in method: {} - {}.{}() - {}"+
                    handlerMethod.getBeanType().getSimpleName()+
                    handlerMethod.getMethod().getName()+
                    ex.getMessage());

            throw ex;
        }
        }

    @Around("execution(* com.bfi.orabank.Services.*.*.*(..))")
    public Object handleExceptionIASystem(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Exception ex) {
            log.info("Exception occurred in method: {}.{} - {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    ex.getMessage());
            throw new RuntimeException(ex);
        }
    }



}
