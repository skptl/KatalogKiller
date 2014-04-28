package com.cs9033.server.daos;

import redis.clients.jedis.Jedis;

import com.cs9033.server.utilities.Database;

public class ImageDAO {
	
	private static final Jedis jedis = Database.getJedis();
	
	public static int createImage(String json)
	{
		return -1;
	}
	
	public static int updateImage(String json)
	{
		return -1;
	}
	
	public static int deleteImage(String json)
	{
		return -1;
	}
	
	public static int readImage(String json)
	{
		return -1;
	}

}
