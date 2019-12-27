package com.waoqi.service.impl;

import com.waoqi.common.redis.shiro.RedisManager;
import com.waoqi.common.utils.DateUtils;
import com.waoqi.service.JedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.Random;

/**
 * @author shiliang.gao
 * @since 2019-08-06
 */
@Service
public class JedisServiceImpl implements JedisService {
    private static final String ORDER_NO_KEY = "ORDER_NO_KEY";
    private static final int ORDER_SER_NO_MAX = 100000;
    private JedisPool jedisPool = new RedisManager().jedisPool();
    private static Jedis jedis;

    static {
        try {
            String host = "r-2zeed106921154a4pd.redis.rds.aliyuncs.com";//控制台显示访问地址
            int port = 6379;
            // jedis = new Jedis(host, port);
            //鉴权信息
            // jedis.auth("gzj666__");//password
            // jedis.quit();
            // jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int incr(String key) {
        //Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(key).intValue();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long decr(String key) {
        //Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decr(key);

        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String getKey(String key) {
        //Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long del(String key) {
        //Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String setKey(String key, String value, int expire) {
        //Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.setex(key, expire, value);


        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String setKey(String key, String value) {
        //Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);


        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long expire(String key, int seconds) {
        //Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.expire(key, seconds);


        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String genOrderNo(String prefix) {
        if (StringUtils.isEmpty(prefix)) {
            prefix = "DF";
        }
        StringBuffer sb = new StringBuffer(prefix).append(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN_NOSP));
        try {
            String orderNumKey = ORDER_NO_KEY + prefix;
            int incrNum = this.incr(orderNumKey);
            if (incrNum > ORDER_SER_NO_MAX) {
                this.del(orderNumKey);
            }
            return sb.append(incrNum).toString();
        } catch (Exception e) {
            return sb.append(new Random().nextInt(ORDER_SER_NO_MAX)).toString();
        }
    }
}
