package com.neuedu.common;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.ibatis.mapping.CacheBuilder;
import org.joda.time.Hours;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TokenCache {
    //获取缓存对象LoadingCache,,,,在内存中开辟空间，往内存中缓存数据
    private static LoadingCache<String,String> loadingCache = com.google.common.cache.CacheBuilder.newBuilder()
           //初始化1000个缓存项
            .initialCapacity(1000)
            //最多10000个缓存项
            .maximumSize(10000)
            //设置缓存过期时间，12小时以后自动清空
            .expireAfterAccess(12,TimeUnit.HOURS)

            .build(new CacheLoader<String, String>() {
            //当Key不存在的时候，调用该方法
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });


    /**
     * 向缓存添加键值对
     * */
    public static void put(String key,String value){
        loadingCache.put(key, value);
    }


    /***
     * 获取缓存的值
     * 根据key获取value  都是String类型
     *
     */
    public static   String get(String key){
        try {
            String value =  loadingCache.get(key);
            if (value.equals("null")){
                return null;
            }
            return value;
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }



}
