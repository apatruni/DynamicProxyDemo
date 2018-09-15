package patruni.example.service_factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class BookServiceInvocationHandler implements InvocationHandler {
	
	private static String URI = "http://localhost:8080/book-service";
	private static String _bookController = "/book";
	private static String _recommended = "/recommendedBook";
	private RestTemplate restInvoker = new RestTemplate();
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (method.getName().equals("getRecommendedBook")) {
			ResponseEntity<String> response = 
					restInvoker.getForEntity(URI + _bookController + _recommended, String.class);
			return new JSONObject(response.getBody()).get("book");
		}
		
		return null;
	}
	
	
}
