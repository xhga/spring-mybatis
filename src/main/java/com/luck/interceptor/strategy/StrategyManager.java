package com.luck.interceptor.strategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Hua wb on 2018/7/26.
 * 实例化策略 通过key找到对应的策略类(xxxStrategy)
 */
public class StrategyManager {
    private Map<String,Strategy> strategies = new ConcurrentHashMap<String,Strategy>(10);

    public  Strategy getStrategy(String key){
        return strategies.get(key);
    }

    public Map<String, Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(Map<String, String> strategies) {
        for(Map.Entry<String, String> entry : strategies.entrySet()){
            try {
                this.strategies.put(entry.getKey(),(Strategy)Class.forName(entry.getValue()).newInstance());
            } catch (Exception e) {
                System.out.println("实例化策略出错"+e);
            }
        }
    }
}
