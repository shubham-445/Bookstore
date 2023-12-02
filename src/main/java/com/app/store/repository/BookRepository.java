package com.app.store.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.app.store.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
	
	@EntityGraph(attributePaths = { "categories", "authors" })
	List<Book> findAllEagerBy();	
		
	@EntityGraph(attributePaths = { "categories", "authors" })
	Optional<Book> findById(Long id);
	
	@Query("SELECT DISTINCT c.name FROM Category c")
	List<String> findAllCategories();
	
	@Query("SELECT DISTINCT a.name FROM Author a")
	List<String> findAllAuthors();
	
}
