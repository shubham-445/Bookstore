package com.app.store;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.store.service.IUserService;

@Component
public class StoreAppStartupRunner implements CommandLineRunner{

	@Autowired
	private IUserService userService;
	
	@Override
	public void run(String... args) throws Exception {
		userService.createUser("admin", "admin", "admin@admin.com", Arrays.asList("ROLE_USER", "ROLE_ADMIN"));	
	}
}

