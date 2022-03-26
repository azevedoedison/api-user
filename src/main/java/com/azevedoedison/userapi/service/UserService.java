package com.azevedoedison.userapi.service;

import java.util.List;

import com.azevedoedison.userapi.model.User;

public interface UserService {

	User findById(Integer id);
	List<User> findAll();
	User create(User user);
	User update(User user);
	void delete(Integer id);
	void findByEmail(User user);
}
