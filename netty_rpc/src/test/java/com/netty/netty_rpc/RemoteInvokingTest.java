package com.netty.netty_rpc;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RemoteInvokingTest.class) 
@ComponentScan("com.netty")
public class RemoteInvokingTest {

	/*
	 * @RemoteInvoke private UserRemote userRemote;
	 * 
	 * @Test public void testSaveUser1() {
	 * 
	 * 
	 * User user = new User();
	 * 
	 * user.setId(1);
	 * 
	 * user.setName("张三");
	 * 
	 * Response response = userRemote.saveUser(user);
	 * 
	 * System.out.println(response.getResult());
	 * 
	 * 
	 * }
	 * 
	 * @Test public void testSaveUser() {
	 * 
	 * 
	 * List<User> users = new ArrayList<User>();
	 * 
	 * User user = new User();
	 * 
	 * user.setId(1);
	 * 
	 * user.setName("张三");
	 * 
	 * users.add(user);
	 * 
	 * Response response = userRemote.saveUsers(users);
	 * 
	 * System.out.println(response.getResult());
	 * 
	 * 
	 * 
	 * }
	 */
}
