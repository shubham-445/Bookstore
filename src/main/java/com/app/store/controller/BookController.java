package com.app.store.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.store.domain.Author;
import com.app.store.domain.Book;
import com.app.store.domain.BookBuilder;
import com.app.store.domain.Category;
import com.app.store.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping("/add")
	public String addBook(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		model.addAttribute("allAuthors", bookService.getAllAuthors());
		model.addAttribute("allCategories", bookService.getAllCategories());
		
		return "addBook";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) {
		Book newBook = new BookBuilder()
				.withTitle(book.getTitle())
				.stockAvailable(book.getStock())
				.withPrice(book.getPrice())
				.imageLink(book.getPicture())
				.ofCategories(Arrays.asList(request.getParameter("category").split("\\s*,\\s*")))
				.ofAuthor(Arrays.asList(request.getParameter("author").split("\\s*,\\s*")))
				.build();		
		bookService.saveBook(newBook);	
		return "redirect:book-list";
	}
	
	@RequestMapping("/book-list")
	public String bookList(Model model) {
		List<Book> books = bookService.findAllBooks();
		model.addAttribute("books", books);
		return "bookList";
	}
	
	@RequestMapping("/edit")
	public String editBook(@RequestParam("id") Long id, Model model) {
		Book book = bookService.findBookById(id);
		
		String preselectedAuthors = "";
		for (Author author : book.getAuthors()) {
			preselectedAuthors += (author.getName() + ",");
		}
		String preselectedCategories = "";
		for (Category category : book.getCategories()) {
			preselectedCategories += (category.getName() + ",");
		}		
		model.addAttribute("book", book);
		model.addAttribute("preselectedAuthors", preselectedAuthors);
		model.addAttribute("preselectedCategories", preselectedCategories);
		model.addAttribute("allAuthors", bookService.getAllAuthors());
		model.addAttribute("allCategories", bookService.getAllCategories());
		return "editBook";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) {		
		Book newBook = new BookBuilder()
				.withTitle(book.getTitle())
				.stockAvailable(book.getStock())
				.withPrice(book.getPrice())
				.imageLink(book.getPicture())
				.ofCategories(Arrays.asList(request.getParameter("category").split("\\s*,\\s*")))
				.ofAuthor(Arrays.asList(request.getParameter("author").split("\\s*,\\s*")))
				.build();
		newBook.setId(book.getId());
		bookService.saveBook(newBook);	
		return "redirect:book-list";
	}
	
	@RequestMapping("/delete")
	public String deleteBook(@RequestParam("id") Long id) {
		bookService.deleteBookById(id);
		return "redirect:book-list";
	}
	
}
