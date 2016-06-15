package com.x.redis.dao.impl;

import com.x.redis.model.User;

public interface UserDAO {

	void saveUser(User user);

	User getUser(final long id);
	
}