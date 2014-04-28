package com.cs9033.server.daos;

import redis.clients.jedis.Jedis;

import com.cs9033.server.utilities.Database;

public class UserDAO {
	
	private static final Jedis jedis = Database.getJedis();

}
