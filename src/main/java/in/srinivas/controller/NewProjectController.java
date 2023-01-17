package in.srinivas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.srinivas.model.Book;

@RestController
public class NewProjectController {

	Map<Integer, Book> books = new HashMap<>();

	private static Logger logger = LoggerFactory.getLogger(NewProjectController.class);

	// insertBook - post - requestBody
	// updateBook - put - requestBody
	// getBookDetails - get - pathVariable(bookID)
	// deleteBooke - delete - pathVariable(bookID)
	// getAllBooksList - get - return books list

	@PostMapping("/book")
	public ResponseEntity<String> saveBook(@RequestBody Book book) {
		logger.info("book is about to save : {}", book);
		if (books.containsKey(book.getBookId())) {
			logger.error("book is already saved.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			books.put(book.getBookId(), book);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}

	@PutMapping("/book")
	public Book updateBook(@RequestBody Book book) {
		logger.info("book infomation is going to be update : {}", book);
		books.put(book.getBookId(), book);
		return books.get(book.getBookId());
	}

	@GetMapping("/book/{bookId}")
	public Book getBook(@PathVariable("bookId") int bookId) {
		logger.info("bookId Received : {}", bookId);
		return books.get(bookId);
	}

	@DeleteMapping("/book/{bookId}")
	public String deleteBook(@PathVariable int bookId) {
		logger.info("requested book id info deleted : {}", bookId);
		books.remove(bookId);
		return "Successfully book deleted";
	}
	
	@GetMapping("/books")
	public List<Book> getBooks(){
		return books.values().stream().collect(Collectors.toList());		
	}

}
