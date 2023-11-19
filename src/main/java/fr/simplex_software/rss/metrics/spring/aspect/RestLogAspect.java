package fr.simplex_software.rss.metrics.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

@Aspect
@Component
public class RestLogAspect {

    @Around(value ="@annotation(restLogAnnotation)")
    public Object restLog(ProceedingJoinPoint joinPoint, RestLog restLogAnnotation) throws Throwable {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

        RestMappingAnnotation requestMapping = getRequestMapping(joinPoint);
        log.info("Starting {}", requestMapping);

        long startTime = System.nanoTime();
        double endTime = System.nanoTime();
        Throwable throwable = null; // an exception holder to read the exception in the finally block

        try {
            Object result = joinPoint.proceed();
            endTime = System.nanoTime();
            return result;

        } catch (Exception exception) {
            endTime = System.nanoTime();
            throwable = exception;
            throw exception;

        } finally {
            if ((throwable == null)) {
                log.info("{} ended successfully", requestMapping);
            } else {
                log.error("{} ended with exception: {}", requestMapping, throwable.getMessage());
            }
            log.info("Time used in ms: {}", (endTime - startTime) / 100000d);
        }
    }

    private RestMappingAnnotation getRequestMapping(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        GetMapping getMapping = method.getAnnotation(GetMapping.class);
        if (getMapping != null) {
            return new RestMappingAnnotation("GET", getMapping.value(), getMapping.path());
        }

        DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
        if (deleteMapping != null) {
            return new RestMappingAnnotation("DELETE", deleteMapping.path(), deleteMapping.value());
        }

        PutMapping putMapping = method.getAnnotation(PutMapping.class);
        if (putMapping != null) {
            return new RestMappingAnnotation("PUT", putMapping.path(), putMapping.value());
        }

        PostMapping postMapping = method.getAnnotation(PostMapping.class);
        if (postMapping != null) {
            return new RestMappingAnnotation("POST", postMapping.path(), postMapping.value());
        }

        PatchMapping patchMapping = method.getAnnotation(PatchMapping.class);
        if (patchMapping != null) {
            return new RestMappingAnnotation("PATCH", patchMapping.path(), patchMapping.value());
        }
        return new RestMappingAnnotation("N/A",new String[]{""}, new String[]{""});
    }

}
