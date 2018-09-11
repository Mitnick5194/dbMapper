package com.ajie.dao.redis;

/**
 * @author niezhenjie
 */
public interface RedisClient {

	/**
	 * 保存一个键值对
	 * 
	 * @param key
	 * @param value
	 * @throws JedisException
	 */
	String set(String key, Object value) throws JedisException;

	/**
	 * 保存一个键值对
	 * 
	 * @param key
	 * @param value
	 */
	String set(String key, String value);

	/**
	 * 获取指定键的值
	 * 
	 * @param key
	 * @return
	 * @throws JedisException
	 */
	Object getAsBean(String key) throws JedisException;

	/**
	 * 获取指定键的值
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 * @throws JedisException
	 */
	<T> T getAsBean(String key, Class<T> clazz) throws JedisException;

	/**
	 * 获取指定键的值
	 * 
	 * @param key
	 * @return
	 */
	String get(String key);

	/**
	 * 删除指定键
	 * 
	 * @param key
	 */
	boolean del(String key);

	/**
	 * 设置或更新过期时间
	 * 
	 * @param seconds
	 */
	long expire(String key, int seconds);

	/**
	 * hash键值
	 * 
	 * @param field
	 * @param key
	 * @param value
	 * @throws JedisException
	 */
	long hset(String key, String field, Object value) throws JedisException;

	/**
	 * hash键值
	 * 
	 * @param field
	 * @param key
	 * @param value
	 */
	long hset(String key, String field, String value);

	/**
	 * 获取hash键值
	 * 
	 * @param field
	 * @param key
	 * @return
	 */
	String hget(String key, String field);

	/**
	 * 获取hash键值
	 * 
	 * @param field
	 * @param key
	 * @return
	 * @throws JedisException
	 */
	Object hgetAsBean(String key, String field) throws JedisException;

	/**
	 * 获取hash键值
	 * 
	 * @param field
	 * @param key
	 * @return
	 * @throws JedisException
	 */
	<T> T hgetAsBean(String key, String field, Class<T> clazz) throws JedisException;

	/**
	 * 删除hash域中的指定的key
	 * 
	 * @param field
	 * @param key
	 */
	boolean hdel(String key, String field);

	/**
	 * 删除整个hash域，其实就是del
	 * 
	 * @param field
	 */
	boolean hdel(String key);

	/**
	 * 过期剩余时间
	 * 
	 * @param key
	 * @return
	 */
	long ttl(String key);

}
