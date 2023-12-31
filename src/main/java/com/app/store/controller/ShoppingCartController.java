package com.app.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.store.domain.Book;
import com.app.store.domain.CartItem;
import com.app.store.domain.ShoppingCart;
import com.app.store.domain.User;
import com.app.store.service.BookService;
import com.app.store.service.IShoppingCartService;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
		
	@Autowired
	private BookService bookService;
	
	@Autowired
	private IShoppingCartService shoppingCartService;
	
	@RequestMapping("/cart")
	public String shoppingCart(Model model, Authentication authentication) {		
		User user = (User) authentication.getPrincipal();		
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(user);		
		model.addAttribute("cartItemList", shoppingCart.getCartItems());
		model.addAttribute("shoppingCart", shoppingCart);		
		return "shoppingCart";
	}

	@RequestMapping("/add-item")
	public String addItem(@ModelAttribute("book") Book book, @RequestParam("qty") String qty,
						   RedirectAttributes attributes, Model model, Authentication authentication) {
		book = bookService.findBookById(book.getId());				
		if (!book.hasStock(Integer.parseInt(qty))) {
			attributes.addFlashAttribute("notEnoughStock", true);
			return "redirect:/book-detail?id="+book.getId();
		}		
		User user = (User) authentication.getPrincipal();		
		shoppingCartService.addBookToShoppingCart(book, user, Integer.parseInt(qty));
		attributes.addFlashAttribute("addBookSuccess", true);
		return "redirect:/book-detail?id="+book.getId();
	}
	
	@RequestMapping("/update-item")
	public String updateItemQuantity(@RequestParam("id") Long cartItemId,
									 @RequestParam("qty") Integer qty, Model model) {		
		CartItem cartItem = shoppingCartService.findCartItemById(cartItemId);
		if (cartItem.canUpdateQty(qty)) {
			shoppingCartService.updateCartItem(cartItem, qty);
		}
		return "redirect:/shopping-cart/cart";
	}
	
	@RequestMapping("/remove-item")
	public String removeItem(@RequestParam("id") Long id) {		
		shoppingCartService.removeCartItem(shoppingCartService.findCartItemById(id));		
		return "redirect:/shopping-cart/cart";
	}
}
