package com.guguangming.forwarder.aspect;

import com.google.common.collect.ImmutableMap;
import com.moxie.cloud.commons.constant.MetricsConst;
import com.moxie.cloud.commons.metrics.BaseCounter;
import com.moxie.cloud.commons.metrics.BaseGauge;
import com.moxie.commons.BaseAspectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class DaoAspect {
    @Autowired(required = false)
    private BaseCounter baseCounter;
    @Autowired(required = false)
    private BaseGauge baseGauge;

    @Around("execution(public * com.guguangming.forwarder.dao.*Dao.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return BaseAspectUtils.logAround(joinPoint, 300L);
        } finally {
            if (baseCounter != null && baseGauge != null) {
                long duration = System.currentTimeMillis() - start;
                Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
                String methodInfo = method.getDeclaringClass().getSimpleName() + "." + method.getName();
                baseCounter.inc(MetricsConst.MYSQL_COUNT, 1, ImmutableMap.of(MetricsConst.METHOD, methodInfo));
                baseGauge.set(MetricsConst.MYSQL_DURATION, duration, ImmutableMap.of(MetricsConst.METHOD, methodInfo));
            }
        }
    }
}