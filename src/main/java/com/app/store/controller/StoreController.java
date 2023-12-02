package com.app.store.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.store.domain.Book;
import com.app.store.form.BookFilterForm;
import com.app.store.service.BookService;
import com.app.store.type.SortFilter;

@Controller
public class StoreController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/store")
	public String store(@ModelAttribute("filters") BookFilterForm filters, Model model) {
		Integer page = filters.getPage();			
		int pagenumber = (page == null ||  page <= 0) ? 0 : page-1;
		SortFilter sortFilter = new SortFilter(filters.getSort());	
		Page<Book> pageresult = bookService.findBooksByCriteria(PageRequest.of(pagenumber,9, sortFilter.getSortType()), 
																filters.getPricelow(), filters.getPricehigh(), 
																 filters.getCategory(), filters.getAuthor(), filters.getSearch());	
		model.addAttribute("allCategories", bookService.getAllCategories());
		model.addAttribute("allAuthors", bookService.getAllAuthors());
		
		model.addAttribute("books", pageresult.getContent());
		model.addAttribute("totalitems", pageresult.getTotalElements());
		model.addAttribute("itemsperpage", 9);
		return "store";
	}
	
	
	@RequestMapping("/book-detail")
	public String bookDetail(@PathParam("id") Long id, Model model) {
		Book book = bookService.findBookById(id);
		model.addAttribute("book", book);
		model.addAttribute("notEnoughStock", model.asMap().get("notEnoughStock"));
		model.addAttribute("addBookSuccess", model.asMap().get("addBookSuccess"));
		return "bookDetail";
	}
	

}
