package com.waoqi.service;

/**
 * @author shiliang.gao
 * @since 2019-08-06
 */
public interface JedisService {

    int incr(String key);

    Long decr(String key);

    String getKey(String key);

    Long del(String key);

    String setKey(String key, String value, int expire);

    String setKey(String key, String value);

    Long expire(String key, int seconds);

    String genOrderNo(String prefix);
}
