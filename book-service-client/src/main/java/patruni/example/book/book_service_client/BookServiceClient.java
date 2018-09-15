package patruni.example.book.book_service_client;

import patruni.example.service_factory.ServiceFactory;

public class BookServiceClient {
	
	private BookServiceClient() {}
	
	private static BookServiceClient bookServiceClientInstance = new BookServiceClient();
	
	public static BookService bookService = (BookService)ServiceFactory.getService("BookService", new Class[] {BookService.class});
	
	public static BookServiceClient getInstance() {
		return bookServiceClientInstance;
	}
	
	public String getRecommendedBook() {
		return bookService.getRecommendedBook();
	}
}
