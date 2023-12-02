package com.app.store.service;

import java.util.List;

import com.app.store.domain.Order;
import com.app.store.domain.Payment;
import com.app.store.domain.Shipping;
import com.app.store.domain.ShoppingCart;
import com.app.store.domain.User;

public interface IOrderService {

	Order createOrder(ShoppingCart shoppingCart, Shipping shippingAddress, Payment payment, User user);
	
	List<Order> findByUser(User user);
	
	Order findOrderWithDetails(Long id);
}
