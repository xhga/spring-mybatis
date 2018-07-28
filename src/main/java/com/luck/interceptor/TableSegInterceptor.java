package com.luck.interceptor;

import com.luck.interceptor.strategy.Strategy;
import com.luck.interceptor.strategy.StrategyManager;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.util.Properties;

import static org.apache.ibatis.reflection.SystemMetaObject.DEFAULT_OBJECT_FACTORY;
import static org.apache.ibatis.reflection.SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY;

/**
 * Created by Hua wb on 2018/7/25.
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class TableSegInterceptor implements Interceptor {
    @Autowired
    StrategyManager strategyManager;
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("进入拦截器：====================");
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);

        Object parameterObject = metaStatementHandler.getValue("delegate.boundSql.parameterObject");
        doSplitTable(metaStatementHandler,parameterObject);
        // 传递给下一个拦截器处理
        return invocation.proceed();

    }

    @Override
    public Object plugin(Object o) {
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
        if (o instanceof StatementHandler) {
            return Plugin.wrap(o, this);
        } else {
            return o;
        }

    }

    @Override
    public void setProperties(Properties properties) {

    }

    private void doSplitTable(MetaObject metaStatementHandler,Object param) throws ClassNotFoundException{

        String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        if (originalSql != null && !originalSql.equals("")) {
            System.out.println("分表前的SQL："+originalSql);
            MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
            String id = mappedStatement.getId();
            String className = id.substring(0, id.lastIndexOf("."));
            Class<?> classObj = Class.forName(className);
            // 根据配置自动生成分表SQL
            TableSplit tableSplit = classObj.getAnnotation(TableSplit.class);
            if (tableSplit != null && tableSplit.split()) {
                // StrategyManager可以使用ContextHelper策略帮助类获取，本次使用注入
                String key = tableSplit.strategy();
                // 根据key获取策略类
                Strategy strategy = strategyManager.getStrategy(key);
                String convertedSql= null;
                try {
                    convertedSql = originalSql.replaceAll(tableSplit.value(), strategy.returnTableName(tableSplit.value()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                metaStatementHandler.setValue("delegate.boundSql.sql",convertedSql);
                System.out.println("分表后的SQL："+convertedSql);
            }
        }
    }


}
