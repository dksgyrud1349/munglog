package com.munglog.munglog.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ServiceLoggingAspect {

    @Around("execution(* com.munglog.munglog.service..*(..))")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        long start = System.currentTimeMillis();

        log.info("[Service Start] - {}.{}", className, methodName);

        try {

            Object result = joinPoint.proceed();

            long end = System.currentTimeMillis();

            log.info("[Service End] - {}.{} ({} ms)",
                    className,
                    methodName,
                    end - start);

            return result;

        } catch (Exception e) {

            log.error("[Service Error] - {}.{} : {}",
                    className,
                    methodName,
                    e.getMessage(),
                    e);

            throw e;
        }
    }
}
