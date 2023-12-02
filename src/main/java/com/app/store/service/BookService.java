package com.app.store.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.store.domain.Book;

public interface BookService {

	List<Book> findAllBooks();
	
	Page<Book> findBooksByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh,
			List<String> categories, List<String> authors, String search);
		
	List<Book> findFirstBooks();

	Book findBookById(Long id);
	
	Book saveBook(Book book);

	void deleteBookById(Long id);

	List<String> getAllCategories();

	List<String> getAllAuthors();

}
