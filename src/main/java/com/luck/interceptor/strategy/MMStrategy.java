package com.luck.interceptor.strategy;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Hua wb on 2018/7/26.
 * 策略
 */
public class MMStrategy implements Strategy {
    @Override
    public String returnTableName(String tableName) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        StringBuilder sb=new StringBuilder(tableName);
        sb.append("_");
        sb.append(sdf.format(new Date()));
        return sb.toString();
    }
}
