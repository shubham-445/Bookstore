package com.app.store.service;

import com.app.store.domain.Book;
import com.app.store.domain.CartItem;
import com.app.store.domain.ShoppingCart;
import com.app.store.domain.User;


public interface IShoppingCartService {

	ShoppingCart getShoppingCart(User user);
	
	int getItemsNumber(User user);
	
	CartItem findCartItemById(Long cartItemId);
	
	CartItem addBookToShoppingCart(Book book, User user, int qty);
		
	void clearShoppingCart(User user);
	
	void updateCartItem(CartItem cartItem, Integer qty);

	void removeCartItem(CartItem cartItem);
	
}
