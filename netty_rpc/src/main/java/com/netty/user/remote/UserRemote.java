package com.netty.user.remote;

import java.util.List;

import com.netty.netty_rpc.util.Response;
import com.netty.user.bean.User;

public interface UserRemote {

	public Response saveUser(User user);
	
	public Response saveUsers(List<User> users);
}
