package com.cs9033.server.utilities;

import redis.clients.jedis.Jedis;

public class Database {

	private static Jedis jedis;

	/**
	 * @return the jedis
	 */
	public static Jedis getJedis() {
		try {
			if (jedis == null)
				jedis = new Jedis("127.0.0.1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jedis;
	}

}
