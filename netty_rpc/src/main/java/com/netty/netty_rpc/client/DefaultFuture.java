package com.netty.netty_rpc.client;

import java.awt.DefaultKeyboardFocusManager;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.netty.netty_rpc.util.Response;

public class DefaultFuture {

	public static ConcurrentHashMap<Long, DefaultFuture> allDefaultFuture = new ConcurrentHashMap<Long, DefaultFuture>();

	// 锁
	final Lock lock = new ReentrantLock();

	private Condition condition = lock.newCondition();
	
	private Response response;

	public DefaultFuture(ClientRequest request) {

		allDefaultFuture.put(request.getId(), this);
	}

	// 主线程获取数据,要先等结果
	public Response get() {

		lock.lock();

		try {
			while(!done()) {
				//如果没有收到信息，当前线程等待
				condition.await(10,TimeUnit.SECONDS);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			lock.unlock();
		}

		return this.response;
	}
	
	public static void receive(Response response) {
		
		DefaultFuture df = allDefaultFuture.get(response.getId());
		
		if(df !=null) {
			
			Lock lock = df.lock;
			
			lock.lock();
			
			try {
				
				df.setResponse(response);
				
				//唤醒下一个线程
				df.condition.signal();
				
				allDefaultFuture.remove(df);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		}
	}
	
	
	
	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	private boolean done() {
		if(this.response!=null) {
			return true;
		}
		return false;
	}

}
