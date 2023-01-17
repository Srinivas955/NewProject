package in.srinivas.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
	
	private int bookId;
	private String bookName;
	private Double price;
	private String author;

}
