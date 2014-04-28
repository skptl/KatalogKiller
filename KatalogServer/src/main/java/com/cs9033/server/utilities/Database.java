package com.cs9033.server.utilities;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Database {

	private static Jedis jedis;
	private static JedisPool jedisPool;

	/**
	 * @return the jedis
	 */
	public static Jedis getJedis() {
		try {
			if (jedisPool == null)
				jedisPool = new JedisPool("127.0.0.1");
			if(jedis == null)
				jedis = jedisPool.getResource();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jedis;
	}

}
