package com.app.store.domain;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
	private int stock;	
	private double price;
	private String picture;
	
	
	
	@OneToMany(mappedBy="book", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Author> authors;
	
	@OneToMany(mappedBy="book", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Category> categories;

	public Book() {
	}
	
	public boolean hasStock(int amount) {
		return (this.getStock() > 0) && (amount <= this.getStock());
	}
	
	public void decreaseStock(int amount) {
		this.stock -= amount;
	}
	
	public void addCategory(Category category) {
        categories.add(category);
        category.setBook(this);
    }
 
    public void removeCategory(Category category) {
    	categories.remove(category);
        category.setBook(null);
    }
    
	public void addSize(Author author) {
        authors.add(author);
        author.setBook(this);
    }
 
    public void removeSize(Author author) {
    	authors.remove(author);
        author.setBook(null);
    }
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public Set<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
	public Set<Category> getCategories() {
		return categories;
	}
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	

}
