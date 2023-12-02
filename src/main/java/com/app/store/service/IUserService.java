package com.app.store.service;


import java.util.List;

import com.app.store.domain.User;

public interface IUserService {
	
	User findById(Long id);
	
	User findByUsername(String username);
		
	User findByEmail(String email);
		
	void save(User user);
	
	User createUser(String username, String email,  String password, List<String> roles);

}
