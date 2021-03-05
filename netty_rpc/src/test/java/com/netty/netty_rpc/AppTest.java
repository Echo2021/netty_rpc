package com.netty.netty_rpc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.netty.netty_rpc.client.ClientRequest;
import com.netty.netty_rpc.client.TcpClient;
import com.netty.netty_rpc.util.Response;
import com.netty.user.bean.User;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
   
	@Test
	public void test_netty() {
		
		ClientRequest request = new ClientRequest();
		
		request.setContent("tcp 长连接");
		
		Response response = TcpClient.send(request);
		
		System.out.println("response "+ JSONObject.toJSONString(response));
	}
	
	@Test
	public void testSaveUser1() {
		
		ClientRequest request = new ClientRequest();
		
		User user = new User();
		
		user.setId(1);
		
		user.setName("张三");
		
		request.setCommond("com.netty.user.controller.UserController.saveUser");
		
		request.setContent(user);
		
		Response response = TcpClient.send(request);
		
		System.out.println(response.getResult());
		
		
	}
	
	@Test
	public void testSaveUser() {
		
		ClientRequest request = new ClientRequest();
		
		List<User> users = new ArrayList<User>();
		
		User user = new User();
		
		user.setId(1);
		
		user.setName("张三");
		
		users.add(user);
		
		request.setCommond("com.netty.user.controller.UserController.saveUsers");
		
		request.setContent(users);
		
		Response response = TcpClient.send(request);
		
		System.out.println(response.getResult());
		
		
		
	}
	
}
