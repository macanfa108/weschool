import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.jking.pageModel.Book;
import net.jking.service.BookSearchService;
import net.jking.service.BookService;

import org.junit.Test;


public class BookTest {

	@Test
	public void test() throws IOException{
		BookSearchService service = new BookSearchService();
		 
		List<Book> books = new ArrayList<Book>();
		Integer count = service.getBooks("java", 1, 20,books);
		System.out.println(count);
		for(Book book:books){
			System.out.println(book.toString());
		}
	}
	
	@Test
	public void bookinfo() throws IOException{
		BookService service = new BookService();
		service.getBookInfo("444552");
	}
}
