package com.ajie.dao.redis.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.ajie.chilli.utils.common.JsonUtil;
import com.ajie.chilli.utils.common.StringUtil;
import com.ajie.dao.redis.JedisException;
import com.ajie.dao.redis.RedisClient;

/**
 * RedisClient工具类的实现。在需要使用redis的项目引入dao包，在spring中配置jedisPool连接池<br>
 * 和RedisClientImpl bean，并将jedisPool注入<br>
 * 
 * <p>
 * 例如：
 * 
 * <bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig"><br>
 * ……<br>
 * </bean><br>
 * <bean id="jedisPool" class="redis.clients.jedis.JedisPool"><br>
 * ……<br>
 * <constructor-arg name="poolConfig" ref="jedisPoolConfig"></constructor-arg><br>
 * </bean><br>
 * <bean id="redisClientImpl" class="com.ajie.dao.redis.impl.RedisClientImpl"><br>
 * <constructor-arg name="jedisPool" ref="jedisPool" /><br>
 * </bean>
 * </p>
 * 
 * @author niezhenjie
 */
public class RedisClientImpl implements RedisClient {
	private static final Logger logger = LoggerFactory.getLogger(RedisClientImpl.class);

	/**
	 * 为了兼容更多的场景，这里使用构造注入而不适用注解注入
	 */
	protected JedisPool jedisPool;

	public RedisClientImpl(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Override
	public String set(String key, Object value) throws JedisException {
		try {
			String val = JsonUtil.toJSONString(value);
			return set(key, val);
		} catch (Exception e) {
			logger.error("无法将bean【" + value.toString() + "】转换为json字串", e);
			throw new JedisException("无法将bean【" + value.toString() + "】转换为json字串", e);
		}
	}

	@Override
	public String set(String key, String value) {
		assertKV(key, value);
		Jedis jedis = jedisPool.getResource();
		String ret = jedis.set(key, value);
		jedis.close();
		return ret;
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String ret = jedis.get(key);
		jedis.close();
		return ret;
	}

	@Override
	public Object getAsBean(String key) throws JedisException {
		String str = get(key);
		try {
			Object ret = JsonUtil.toBean(str);
			return ret;
		} catch (Exception e) {
			logger.error("无法将字串【" + str + "】转换为bean对象", e);
			throw new JedisException("无法将字串【" + str + "】转换为bean对象", e);
		}
	}

	@Override
	public <T> T getAsBean(String key, Class<T> clazz) throws JedisException {
		String str = get(key);
		try {
			T ret = JsonUtil.toBean(str, clazz);
			return ret;
		} catch (Exception e) {
			logger.error("无法将字串【" + str + "】转换为bean对象", e);
			throw new JedisException("无法将字串【" + str + "】转换为bean对象", e);

		}
	}

	@Override
	public boolean del(String key) {
		Jedis jedis = jedisPool.getResource();
		long ret = jedis.del(key);
		jedis.close();
		return ret > 0;
	}

	@Override
	public long expire(String key, int seconds) {
		Jedis jedis = jedisPool.getResource();
		long ret = jedis.expire(key, seconds);
		jedis.close();
		return ret;
	}

	@Override
	public long hset(String key, String field, Object value) throws JedisException {
		String str = null;
		try {
			str = JsonUtil.toJSONString(value);
			return hset(key, field, str);
		} catch (Exception e) {
			logger.error("无法将字串【" + str + "】转换为bean对象", e);
			throw new JedisException("无法将字串【" + str + "】转换为bean对象", e);
		}
	}

	@Override
	public long hset(String key, String field, String value) {
		assertKV(field, value);
		Jedis jedis = jedisPool.getResource();
		long ret = jedis.hset(key, field, value);
		jedis.close();
		return ret;
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = jedisPool.getResource();
		String ret = jedis.hget(key, field);
		jedis.close();
		return ret;
	}

	@Override
	public Object hgetAsBean(String key, String field) throws JedisException {
		String str = hget(key, field);
		try {
			Object ret = JsonUtil.toBean(str);
			return ret;
		} catch (Exception e) {
			logger.error("无法将字串【" + str + "】转换为bean对象", e);
			throw new JedisException("无法将字串【" + str + "】转换为bean对象", e);
		}
	}

	@Override
	public <T> T hgetAsBean(String key, String field, Class<T> clazz) throws JedisException {
		String str = hget(key, field);
		try {
			T ret = JsonUtil.toBean(str, clazz);
			return ret;
		} catch (Exception e) {
			logger.error("无法将字串【" + str + "】转换为bean对象", e);
			throw new JedisException("无法将字串【" + str + "】转换为bean对象", e);
		}
	}

	@Override
	public boolean hdel(String key, String field) {
		Jedis jedis = jedisPool.getResource();
		long ret = jedis.hdel(key, field);
		jedis.close();
		return 0 < ret;
	}

	@Override
	public boolean hdel(String key) {
		return del(key);
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		long ttl = jedis.ttl(key);
		jedis.close();
		return ttl;
	}

	protected void assertKV(String key, String val) {
		if (StringUtil.isEmpty(key))
			throw new IllegalArgumentException("key不能为空");
		if (StringUtil.isEmpty(val)) {
			throw new IllegalArgumentException("key不能为空");
		}
	}

}
