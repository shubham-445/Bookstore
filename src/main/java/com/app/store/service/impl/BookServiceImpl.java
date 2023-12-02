package com.app.store.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.store.domain.Book;
import com.app.store.repository.BookRepository;
import com.app.store.repository.BookSpecification;
import com.app.store.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Value("${bookservice.featured-items-number}")
	private int featuredBooksNumber;

	@Override
	public List<Book> findAllBooks() {
		return (List<Book>) bookRepository.findAllEagerBy();
	}
	
	@Override
	public Page<Book> findBooksByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, 
										 List<String> categories, List<String> authors, String search) {		
		Page<Book> page = bookRepository.findAll(BookSpecification.filterBy(priceLow, priceHigh, categories, authors, search), pageable);
        return page;		
	}	
	
	@Override
	public List<Book> findFirstBooks() {
		return bookRepository.findAll(PageRequest.of(0,featuredBooksNumber)).getContent(); 
	}
	
	@Override
	public Book findBookById(Long id) {
		Optional<Book> opt = bookRepository.findById(id);
		return opt.get();
	}

	@Override
	@CacheEvict(value = { "categories", "authors" }, allEntries = true)
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}
	
	@Override
	@CacheEvict(value = { "categories", "authors" }, allEntries = true)
	public void deleteBookById(Long id) {
		bookRepository.deleteById(id);		
	}


	@Override
	@Cacheable("categories")
	public List<String> getAllCategories() {
		return bookRepository.findAllCategories();
	}

	@Override
	@Cacheable("authors")
	public List<String> getAllAuthors() {
		return bookRepository.findAllAuthors();
	}
}
