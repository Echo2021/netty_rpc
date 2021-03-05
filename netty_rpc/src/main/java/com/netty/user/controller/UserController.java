package com.netty.user.controller;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.netty.netty_rpc.util.Response;
import com.netty.netty_rpc.util.ResponseUtil;
import com.netty.user.bean.User;
import com.netty.user.service.UserService;

@Controller
public class UserController {
	
	@Resource
	private UserService userService;

	public Response saveUser(User user) {
		
		userService.saveUser(user);
		
		return ResponseUtil.createSuccessResult(user);
		
	}
	
	public Response saveUsers(List<User> users) {
		
		userService.saveList(users);
		
		return ResponseUtil.createSuccessResult(users);
		
	}
}
