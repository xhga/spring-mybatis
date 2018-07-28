package com.luck.interceptor.strategy;

/**
 * Created by Hua wb on 2018/7/26.
 * 策略接口(由策略类实现，根据各自的策略返回表名)
 */
public interface Strategy {
    /**
     * 传入一个需要分表的表名，返回一个处理后的表名
     * @param tableName
     * @return
     */
    String returnTableName(String tableName);
}
