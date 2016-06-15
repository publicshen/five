package com.x.redis.dao.impl;

import java.io.Serializable;
import java.sql.SQLOutput;

import org.junit.experimental.theories.Theories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.x.redis.model.User;

public class UserDAOImpl implements UserDAO {
	
	@Autowired private JedisConnectionFactory  factory;

	@Autowired protected RedisTemplate<Serializable, Serializable> redisTemplate;
	
	@Autowired private StringRedisTemplate stringRedisTemplate;

    public void saveUser(final User user) {
    	RedisConnection conn = factory.getConnection();
        redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(redisTemplate.getStringSerializer().serialize("user.uid." + user.getId()),
                               redisTemplate.getStringSerializer().serialize(user.getName()));
                return null;
            }
        });
    }

    public User getUser(final long id) {
    	this.t();
        return redisTemplate.execute(new RedisCallback<User>() {
            public User doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key = redisTemplate.getStringSerializer().serialize("user.uid." + id);
                if (connection.exists(key)) {
                    byte[] value = connection.get(key);
                    String name = redisTemplate.getStringSerializer().deserialize(value);
                    User user = new User();
                    user.setName(name);
                    user.setId(id);
                    return user;
                }
                return null;
            }
        });
    }
    
    public void t() {
    	redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				return null;
			}
		});
//    	String hostName = factory.getHostName();
//    	JedisConnection conn = factory.getConnection();
//    	conn.set("sb".getBytes(), "邵志强".getBytes());
//    	byte[] bs = conn.get("sb".getBytes());
//    	System.out.println(bs);
    }
}
