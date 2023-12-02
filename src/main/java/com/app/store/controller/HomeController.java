package com.app.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.store.domain.Book;
import com.app.store.service.BookService;

@Controller
public class HomeController {
		
	@Autowired
	private BookService bookService;
	
	
	@RequestMapping("/")
	public String index(Model model) {		
		List<Book> books = bookService.findFirstBooks();
		model.addAttribute("books", books);
		return "index";
	}

	
}
