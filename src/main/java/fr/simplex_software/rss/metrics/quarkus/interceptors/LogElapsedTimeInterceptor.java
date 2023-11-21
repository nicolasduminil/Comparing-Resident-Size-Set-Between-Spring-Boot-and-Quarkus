package fr.simplex_software.rss.metrics.quarkus.interceptors;

import org.jboss.logging.Logger;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.*;
import java.lang.reflect.Method;

@LogTime
@Interceptor
@Priority(1000)
public class LogElapsedTimeInterceptor {

    @AroundInvoke
    Object logInvocation(InvocationContext context) {
        long startTime = 0;
        long endTime = 0;
        Throwable throwable = null;

        Object target = context.getTarget();
        Logger log = Logger.getLogger(target.getClass().getSuperclass());

        try {
            log.info(String.format("Starting %s", getRestMapping(context)));
            startTime = System.nanoTime();
            Object result = context.proceed();
            endTime = System.nanoTime();
            return result;

        } catch (Exception e) {
            endTime = System.nanoTime();
            throwable = e;
            throw new RuntimeException(e);

        } finally {
            if (throwable == null) {
                log.info("ended successfully");
            } else {
                log.error(String.format("ended with Exception: %s", throwable), throwable);
            }
            log.info(String.format("Elapsed time in ms: %s", ((endTime - startTime) / 1000)));
        }
    }

    private RestMapping getRestMapping(InvocationContext context) {
        Method method = context.getMethod();

        String path = "/";
        Path pathAnnotation = method.getAnnotation(Path.class);
        if (pathAnnotation != null) {
            path = pathAnnotation.value();
        }

        GET getAnnotation = method.getAnnotation(GET.class);
        if (getAnnotation != null) {
            return new RestMapping("GET", path);
        }

        DELETE deleteAnnotation = method.getAnnotation(DELETE.class);
        if (deleteAnnotation != null) {
            return new RestMapping("DELETE", path);
        }

        PUT putAnnotation = method.getAnnotation(PUT.class);
        if (putAnnotation != null) {
            return new RestMapping("PUT", path);
        }

        POST postAnnotation = method.getAnnotation(POST.class);
        if (postAnnotation != null) {
            return new RestMapping("POST", path);
        }

        PATCH patchAnnotation = method.getAnnotation(PATCH.class);
        if (patchAnnotation != null) {
            return new RestMapping("PATCH", path);
        }

        return new RestMapping("N/A", path);
    }
}
