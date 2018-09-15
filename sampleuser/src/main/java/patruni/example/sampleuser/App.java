package patruni.example.sampleuser;

import patruni.example.book.book_service_client.BookService;
import patruni.example.book.book_service_client.BookServiceClient;

public class App 
{
    public static void main( String[] args )
    {
       BookServiceClient bookServiceClient = BookServiceClient.getInstance();
       System.out.println(bookServiceClient.getRecommendedBook());
    }
}
