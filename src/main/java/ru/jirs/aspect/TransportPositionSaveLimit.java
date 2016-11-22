package ru.jirs.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class TransportPositionSaveLimit {

    private static final int LIMIT_CALLS = 10;
    private static volatile int currentCall = 0;

    @Pointcut("execution(public * ru.jirs.controller.TransportPositionRestController.save* (..))")
    public void saveTransportPositionPointcut(){}

    @Around("saveTransportPositionPointcut()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal = null;
        if (currentCall < LIMIT_CALLS) {
            currentCall++;
            retVal = pjp.proceed();
            currentCall--;
        }
        return retVal;
    }

}
