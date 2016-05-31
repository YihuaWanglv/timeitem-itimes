package com.iyihua.itimes.service.component;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

//@Component
public class RedisService {

//	@Autowired
	private JedisCluster jedisCluster;

	private int expire = 0;

	// timeout for jedis try to connect to redis server, not expire time! In milliseconds
	private int timeout = 0;

	/**
	 * get value from redis
	 * 
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key) {
		// byte[] value = null;
		// Jedis jedis = jedisPool.getResource();
		// try{
		// value = jedis.get(key);
		// }finally{
		// jedisPool.returnResource(jedis);
		// }
		// return value;
		return jedisCluster.get(key);
	}

	/**
	 * set
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public byte[] set(byte[] key, byte[] value) {
		// Jedis jedis = jedisPool.getResource();
		// try{
		// jedis.set(key,value);
		// if(this.expire != 0){
		// jedis.expire(key, this.expire);
		// }
		// }finally{
		// jedisPool.returnResource(jedis);
		// }
		// return value;
		jedisCluster.set(key, value);
		return value;
	}

	/**
	 * set
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public byte[] set(byte[] key, byte[] value, int expire) {
		// Jedis jedis = jedisPool.getResource();
		// try{
		// jedis.set(key,value);
		// if(expire != 0){
		// jedis.expire(key, expire);
		// }
		// }finally{
		// jedisPool.returnResource(jedis);
		// }
		// return value;
		jedisCluster.set(key, value);
		if (expire != 0) {
			jedisCluster.expire(key, expire);
		}
		return value;
	}

	/**
	 * del
	 * 
	 * @param key
	 */
	public void del(byte[] key) {
		// Jedis jedis = jedisPool.getResource();
		// try{
		// jedis.del(key);
		// }finally{
		// jedisPool.returnResource(jedis);
		// }
		jedisCluster.del(key);
	}

	/**
	 * flush
	 */
	public void flushDB() {
		// Jedis jedis = jedisPool.getResource();
		// try{
		// jedis.flushDB();
		// }finally{
		// jedisPool.returnResource(jedis);
		// }
		jedisCluster.flushDB();
		// jedisCluster.flushAll();
	}

	/**
	 * size
	 */
	public Long dbSize() {
		// Long dbSize = 0L;
		// Jedis jedis = jedisPool.getResource();
		// try{
		// dbSize = jedis.dbSize();
		// }finally{
		// jedisPool.returnResource(jedis);
		// }
		// return dbSize;
		return jedisCluster.dbSize();
	}

	/**
	 * keys
	 * 
	 * @param regex
	 * @return
	 */
	public Set<byte[]> keys(String pattern) {
		// Set<byte[]> keys = null;
		// Jedis jedis = jedisPool.getResource();
		// try{
		// keys = jedis.keys(pattern.getBytes());
		// }finally{
		// jedisPool.returnResource(jedis);
		// }
		// return keys;
		Set<byte[]> keys = new TreeSet<byte[]>();
		Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
		for (String k : clusterNodes.keySet()) {
			JedisPool jp = clusterNodes.get(k);
			Jedis connection = jp.getResource();
			try {
				keys.addAll(connection.keys(pattern.getBytes()));
			} catch (Exception e) {
				System.err.println("Getting keys error: {}" + e.getMessage());
			} finally {
				System.out.println("Connection closed.");
				connection.close();// 用完一定要close这个链接！！！
			}
		}
		System.out.println("Keys gotten!");
		return keys;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
