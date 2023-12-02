package com.app.store.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.store.domain.Book;
import com.app.store.domain.BookBuilder;
import com.app.store.repository.BookRepository;
import com.app.store.repository.BookSpecification;
/*
@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	 
	@Autowired
	private BookRepository repository;
		 	
	@Before
	public void setUp() {
		Book book = new BookBuilder()
				.withTitle("book1")
				.withPrice(50)
				.ofCategories(Arrays.asList("educational"))
				.ofAuthor(Arrays.asList("shivani"))
				.build();	
        entityManager.persist(book);            
        
        Book book2 = new BookBuilder()
				.withTitle("book2")
				.withPrice(100)
				.ofCategories(Arrays.asList("comics"))
				.ofAuthor(Arrays.asList("prajot"))
				.build();     
        entityManager.persist(book2);    
        
        Book book3 = new BookBuilder()
				.withTitle("book3")
				.withPrice(200)
				.ofCategories(Arrays.asList("food"))
				.ofAuthor(Arrays.asList("saurabh"))
				.build();     
        entityManager.persist(book3); 
        
        Book book4 = new BookBuilder()
				.withTitle("book4")
				.ofAuthor(Arrays.asList("udhav"))
				.withPrice(300)
				.build();     
        entityManager.persist(book4); 
        
        Book book5 = new BookBuilder()
				.withTitle("book5")
				.withPrice(500)
				.ofAuthor(Arrays.asList("udhav"))
				.build();     
        entityManager.persist(book5); 
	}
		
	
	@Test
	public void should_find_all_distinct_brands() {		
        assertThat(repository.findAllAuthors()).hasSize(3).contains("shivani", "prajot", "saurabh");        
	}
	
	@Test
	public void should_find_all_distinct_categories() {		
        assertThat(repository.findAllCategories()).hasSize(3).contains("educational", "comics", "food");	
	}
	
	@Test
	public void should_filter_books_between_prices() {
		int low = 100;
		int high = 300;
		List<Book> results = repository.findAll(BookSpecification.filterBy(low, high, null, null,  null));
		assertThat(results).hasSize(3).extracting("title").contains("book2", "book3", "book4");
	}
	
	@Test
	public void should_filter_books_with_price_higher_than_number() {
		int low = 300;
		List<Book> results = repository.findAll(BookSpecification.filterBy(low, null, null, null, null));
		assertThat(results).hasSize(2).extracting("title").contains("book4", "book5");
	}
	
	@Test
	public void should_filter_books_with_price_lower_than_number() {
		int high = 200;
		List<Book> results = repository.findAll(BookSpecification.filterBy(null, high, null, null, null));
		assertThat(results).hasSize(3).extracting("title").contains("book1", "book2", "book3");
	}
	
	
	@Test
	public void should_filter_books_by_category() {
		List<Book> results = repository.findAll(BookSpecification.filterBy(null, null, Arrays.asList("educational", "comics"), null, null));
		List<Book> results2 = repository.findAll(BookSpecification.filterBy(null, null, Arrays.asList("food", "notARealCategory"), null, null));
		assertThat(results).hasSize(2).extracting("title").contains("book1", "book2");
		assertThat(results2).hasSize(1).extracting("title").contains("book3");
	}
	
	@Test
	public void should_filter_books_by_author() {
		List<Book> results = repository.findAll(BookSpecification.filterBy(null, null, null, Arrays.asList("shivani"), null));
		List<Book> results2 = repository.findAll(BookSpecification.filterBy(null, null, null, Arrays.asList("adidas", "notARealCategory"), null));
		assertThat(results).hasSize(2).extracting("title").contains("book1", "book2");
		assertThat(results2).hasSize(1).extracting("title").contains("book3");
	}
	
	@Test
	public void should_filter_books_by_search_term() {
		List<Book> results = repository.findAll(BookSpecification.filterBy(null, null, null, null, "book"));
		List<Book> results2 = repository.findAll(BookSpecification.filterBy(null, null, null, null, "cle4"));
		List<Book> results3 = repository.findAll(BookSpecification.filterBy(null, null, null, null, "unmatchingterm"));
		assertThat(results).hasSize(5).extracting("title").contains("book1", "book2", "book3", "book4", "book5");
		assertThat(results2).hasSize(1).extracting("title").contains("book4");
		assertThat(results3).isEmpty();
	}
	
	@Test
	public void should_find_all_if_all_filters_are_null() {
		List<Book> results = repository.findAll(BookSpecification.filterBy(null, null, null, null, null));
		assertThat(results).hasSize(5).extracting("title").contains("book1", "book2", "book3", "book4", "book5");
	}

}*/
