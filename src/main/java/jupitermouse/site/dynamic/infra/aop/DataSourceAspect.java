package jupitermouse.site.dynamic.infra.aop;

import jupitermouse.site.dynamic.infra.annotion.DS;
import jupitermouse.site.dynamic.infra.config.DynamicContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源，切面处理类
 * @author jupiter
 */
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DataSourceAspect {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("@annotation(jupitermouse.site.dynamic.infra.annotion.DS) " +
            "|| @within(jupitermouse.site.dynamic.infra.annotion.DS)")
    public void dataSourcePointCut() {
    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class targetClass = point.getTarget().getClass();
        Method method = signature.getMethod();
        DS targetDataSource = (DS) targetClass.getAnnotation(DS.class);
        DS methodDataSource = method.getAnnotation(DS.class);
        if (targetDataSource != null || methodDataSource != null) {
            String value;
            if (methodDataSource != null) {
                value = methodDataSource.name();
            } else {
                value = targetDataSource.name();
            }
            DynamicContextHolder.push(value);
            logger.info("set datasource is {}", value);
        }

        try {
            return point.proceed();
        } finally {
            DynamicContextHolder.poll();
            logger.info("clear datasource");
        }
    }
}