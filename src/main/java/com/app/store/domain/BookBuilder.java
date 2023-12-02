package com.app.store.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookBuilder {
		
	private String title;
	private int stock;	
	private double price;
	private String picture;
	private List<String> categories;
	private List<String> authors;
	
	public BookBuilder() {
	}
	
	public BookBuilder withTitle(String title) {
		this.title = title;
		return this;
	}
	
	public BookBuilder stockAvailable(int stock) {
		this.stock = stock;
		return this;
	}
	
	public BookBuilder withPrice(double price) {
		this.price = price;
		return this;
	}
	
	public BookBuilder imageLink(String picture) {
		this.picture = picture;
		return this;
	}
	
	
	
	public BookBuilder ofCategories(List<String> categories) {
		this.categories = categories;
		return this;
	}
	
	public BookBuilder ofAuthor(List<String> authors) {
		this.authors = authors;
		return this;
	}
	
	public Book build() {
		Book book = new Book();
		book.setTitle(this.title);
		book.setPrice(this.price);
		book.setStock(this.stock);
		book.setPicture(this.picture);		
		
		
		if (this.categories != null && !this.categories.isEmpty() ) {
			Set<Category> catElements = new HashSet<>();
			for (String val : this.categories) {
				catElements.add(new Category(val,book));
			}
			book.setCategories(catElements);
		}		
		if (this.authors != null && !this.authors.isEmpty() ) {
			Set<Author> authorelements = new HashSet<>();
			for (String val : this.authors) {
				authorelements.add(new Author(val,book));
			}
			book.setAuthors(authorelements);
		}		
		
		
		return book;
	}
	
}