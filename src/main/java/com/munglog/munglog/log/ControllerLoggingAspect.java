package com.munglog.munglog.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ControllerLoggingAspect {

    @Around("execution(* com.munglog.munglog.controller..*(..))")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        long start = System.currentTimeMillis();

        log.info("[Controller Start] - {}.{}", className, methodName);

        try {

            Object result = joinPoint.proceed();

            long end = System.currentTimeMillis();

            log.info("[Controller End] - {}.{} ({} ms)", className, methodName, end - start);

            return result;

        } catch (Exception e) {

            log.error("[Controller Error] - {}.{} : {}",
                    className,
                    methodName,
                    e.getMessage(),
                    e);

            throw e;
        }
    }
}

