package patruni.example.service_factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import patruni.example.service_factory.BookServiceInvocationHandler;

public class ServiceFactory {

	private static Map<String , Object> serviceURI = new HashMap<String,Object>();
	
	static {
		serviceURI.put("BookService", new BookServiceInvocationHandler());
	}
	
	public static Object getService(String serviceName, Class[] targetInterface) {
		Object handler;
		if ((handler = serviceURI.get(serviceName)) != null) {
			Object service = Proxy.newProxyInstance(ServiceFactory.class.getClassLoader(), targetInterface, (InvocationHandler) handler);
			return service;
		}
		return null;
	}
}
