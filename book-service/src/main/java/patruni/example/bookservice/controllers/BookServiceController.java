package patruni.example.bookservice.controllers;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookServiceController {
	
	@RequestMapping("/recommendedBook")
	public @ResponseBody ResponseEntity<String> getRecommendedBook() {
		ResponseEntity<String> response;
		JSONObject responseJson = new JSONObject();
		responseJson.put("book", "Sample Book");
		response = new ResponseEntity<String>(responseJson.toString(), HttpStatus.OK);
		return response;
	}
}
