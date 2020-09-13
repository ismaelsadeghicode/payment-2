package com.dml.topup.aop;

import com.dml.topup.util.ObjectUtils;
import com.fasterxml.uuid.Generators;
import org.apache.commons.lang.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Aspect for logging execution of endpoints.
 *
 * @author Ismael Sadeghi
 */
@Aspect
public class LoggingAspec {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.dml.topup.controller.charge..*(..))")
    public void loggingPointcut() {
    }

    @Around("loggingPointcut()")
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        UUID uuid = Generators.timeBasedGenerator().generate();
        if (log.isInfoEnabled()) {
            log.info("{"
                    + "\"action\"" + ":" + "\"Topup\", "
                    + "\"event\"" + ":" + "\"Request\", "
                    + "\"uid\"" + ":" + "\"" + uuid + "\", "
                    + "\"time\"" + ":" + "\"" + stopWatch.getTime() + "\", "
                    + "\"serviceName\"" + ":" + "\"" + joinPoint.getSignature().getName() + "\", "
                    + "\"arguments\"" + ":" + "\"" + Arrays.toString(joinPoint.getArgs()) + "\" "
                    + "}"
            );
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isInfoEnabled()) {
                if (ObjectUtils.isNotNull(result)) {
                    log.info("{"
                            + "\"action\"" + ":" + "\"Topup\", "
                            + "\"event\"" + ":" + "\"Response\", "
                            + "\"uid\"" + ":" + "\"" + uuid + "\", "
                            + "\"serviceName\"" + ":" + "\"" + joinPoint.getSignature().getName() + "\", "
                            + "\"spentTime\"" + ":" + stopWatch.getTime() + ", "
                            + "\"response\"" + ":" + "\"" + ((CompletableFuture) Collections.singletonList(result).get(0)).get() + "\", "
                            + "}"
                    );
                } else {
                    log.info("{"
                            + "\"action\"" + ":" + "\"Topup\", "
                            + "\"event\"" + ":" + "\"Response\", "
                            + "\"uid\"" + ":" + "\"" + uuid + "\", "
                            + "\"serviceName\"" + ":" + "\"" + joinPoint.getSignature().getName() + "\", "
                            + "\"spentTime\"" + ":" + stopWatch.getTime() + ", "
                            + "\"result\"" + ":" + "\"" + ((CompletableFuture) Collections.singletonList(result).get(0)).get() + "\" "
                            + "}"
                    );
                }
            }
            stopWatch.stop();
            return result;
        } catch (Exception e) {
            log.error("{"
                    + "\"action\"" + ":" + "\"Topup\", "
                    + "\"event\"" + ":" + "\"Error\", "
                    + "\"uid\"" + ":" + "\"" + uuid + "\", "
                    + "\"serviceName\"" + ":" + "\"" + joinPoint.getSignature().getName() + "\", "
                    + "\"spentTime\"" + ":" + stopWatch.getTime() + ", "
                    + "\"message\"" + ":" + "\"" + e.getMessage() + "\", "
                    + "\"arguments\"" + ":" + "\"" + Arrays.toString(joinPoint.getArgs()) + "\" "
                    + "}"
            );
            throw e;
        }
    }
}