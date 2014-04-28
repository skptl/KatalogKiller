package com.cs9033.server.daos;

import redis.clients.jedis.Jedis;

import com.cs9033.server.utilities.Database;

public class UserDAO {
	
	private static final Jedis jedis = Database.getJedis();
	
	public static int createUser(String json)
	{
		return -1;
	}
	
	public static int updateUser(String json)
	{
		return -1;
	}
	
	public static int deleteUser(String json)
	{
		return -1;
	}
	
	public static int readUser(String json)
	{
		return -1;
	}

}
