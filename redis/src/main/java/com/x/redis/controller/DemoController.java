package com.x.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.x.redis.dao.impl.UserDAO;
import com.x.redis.model.User;

@Controller
@RequestMapping("user")
public class DemoController {

	@Autowired private UserDAO userDAO;
	
	@RequestMapping("showList")
	public String showList() {
		User user = userDAO.getUser(1);
		System.out.println(user);
		return "/index.jsp";
	}
}