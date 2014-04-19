package com.cs9033.server.utilities;

import redis.clients.jedis.Jedis;

public class Database {
	
	private static Jedis jedis;
	
	public Database()
	{
		if(jedis==null)
			jedis = new Jedis("127.0.0.1");
			
	}

	/**
	 * @return the jedis
	 */
	public Jedis getJedis() {
		return jedis;
	}	

}
