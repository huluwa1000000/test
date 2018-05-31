package com.taotao.dataSource;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource

{
    
    private static final ThreadLocal<String> contextholder = new ThreadLocal<>();
    
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    

    @Override
    protected Object determineCurrentLookupKey()
    {
        return getDataSource();
    }

    private Object getDataSource()
    {
        return contextholder.get();
    }
    
    public static void setDataSource(String dataSource) {
        contextholder.set(dataSource);
    }
    
    public static void clearDataSource() {
        contextholder.remove();
    }
    
}
