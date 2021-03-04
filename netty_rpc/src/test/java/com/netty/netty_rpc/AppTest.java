package com.netty.netty_rpc;

import static org.junit.Assert.assertTrue;


import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.netty.netty_rpc.client.ClientRequest;
import com.netty.netty_rpc.client.Response;
import com.netty.netty_rpc.client.TcpClient;

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
}
