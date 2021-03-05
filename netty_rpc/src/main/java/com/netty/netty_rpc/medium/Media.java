package com.netty.netty_rpc.medium;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.zookeeper.server.Request;

import com.alibaba.fastjson.JSONObject;
import com.netty.netty_rpc.handler.param.ServerRequest;
import com.netty.netty_rpc.util.Response;

public class Media {

	public static Map<String, BeanMethod> beanMap;

	static {
		beanMap = new HashMap<String, BeanMethod>();
	}

	private static Media m = null;

	private Media() {

	}

	public static Media newInstance() {

		if (m == null) {

			m = new Media();
		}

		return m;
	}

	// 反射处理业务代码
	public Response process(ServerRequest serverRequest) {

		Response result = null;
		
		try {
			String commond = serverRequest.getCommond();

			BeanMethod beanMethod = beanMap.get(commond);

			if (beanMethod == null) {
				return null;
			}

			Object bean = beanMethod.getBean();

			Method m = beanMethod.getMethod();

			Class paramType = m.getParameterTypes()[0];

			Object content = serverRequest.getContent();

			Object args = JSONObject.parseObject(JSONObject.toJSONString(content), paramType);

			 result=(Response)m.invoke(bean, args);
			 
			 result.setId(serverRequest.getId());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
