package com.netty.netty_rpc.handler.param;

public class ServerRequest {

	private long id;
	
	private Object content;
	
	private String commond;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public String getCommond() {
		return commond;
	}

	public void setCommond(String commond) {
		this.commond = commond;
	}
	
	  
}
