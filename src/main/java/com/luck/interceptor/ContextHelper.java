package com.luck.interceptor;

import com.luck.interceptor.strategy.StrategyManager;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by Hua wb on 2018/7/25.
 * 获取策略实例化(每个策略一一对应)，目的返回：StrategyManager
 * 如果使用spring注入的话完全可以舍去
 */
@Component
public class ContextHelper implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        ContextHelper.context=context;
    }

    public static StrategyManager getStrategyManager(){
        return context.getBean(StrategyManager.class);
    }
}

