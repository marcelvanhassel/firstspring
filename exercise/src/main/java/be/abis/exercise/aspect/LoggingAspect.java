package be.abis.exercise.aspect;

import be.abis.exercise.exceptions.EnrollException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class LoggingAspect {

    // Logging attributes
    private Logger log = LogManager.getLogger("springLogger");

    @AfterReturning("execution(* be.abis.exercise.service.TrainingService.enrollForSession(..))")
    public void afterEnrolled() {
        System.out.println("Enrollment succes! Very awesome");
        log.trace("Enrollment succes!!!");
    }

    @AfterThrowing(pointcut = "execution(* be.abis.exercise.service.TrainingService.enrollForSession(..))", throwing = "exc")
    public void enrollExceptionThrown(EnrollException exc) {
        System.out.println("Aspect exception: " + exc.getMessage());
        log.error("EnrollException!" + exc.getMessage());
    }

    @Around("execution(* be.abis.exercise.*.*.find*(..))")
    public Object timeExecution(ProceedingJoinPoint pjp) {
        Object o = null;
        try {
            long start = System.nanoTime();
            o = pjp.proceed();
            long end = System.nanoTime();
            log.trace("Time taken by " + pjp.getSignature().getName() + ": " + ((end-start)/1000000.0) + " ms");
            System.out.println("Time taken by " + pjp.getSignature().getName() + ": " + ((end-start)/1000000.0) + " ms");
        } catch(Throwable e) {
            e.printStackTrace();
        }
        return o;
    }

}
