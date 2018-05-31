package com.taotao.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.taotao.annotation.DataSource;
import com.taotao.common.DataSourceNames;
import com.taotao.dataSource.DynamicDataSource;

@Aspect
@Component
public class DataSourceAspect implements Ordered
{
    @Pointcut("@annotation(com.taotao.annotation.DataSource)")
    public void dataSourcePointcut()
    {
        
    }
    
    @Around("dataSourcePointcut()")
    public Object around(ProceedingJoinPoint joinPoint)
        throws Throwable
    {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        
        Method method = signature.getMethod();
        
        DataSource annotation = method.getAnnotation(DataSource.class);
        
        if (annotation == null)
        {
            DynamicDataSource.setDataSource(DataSourceNames.FIRST);
        }
        else
        {
            DynamicDataSource.setDataSource(annotation.name());
        }
        
        try
        {
            return joinPoint.proceed();
        }
        finally
        {
            DynamicDataSource.clearDataSource();
        }
        
    }
    
    @Override
    public int getOrder()
    {
        // TODO Auto-generated method stub
        return 1;
    }
    
}
