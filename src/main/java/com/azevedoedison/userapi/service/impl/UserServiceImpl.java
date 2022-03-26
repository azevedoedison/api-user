package com.azevedoedison.userapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azevedoedison.userapi.model.User;
import com.azevedoedison.userapi.repository.UserRepository;
import com.azevedoedison.userapi.service.UserService;
import com.azevedoedison.userapi.service.exception.DataIntegratyViolationException;
import com.azevedoedison.userapi.service.exception.ObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findById(Integer id) {		
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	@Override
	public List<User> findAll() {		
		return userRepository.findAll();
	}

	@Override
	public User create(User user) {
		findByEmail(user);
		return userRepository.save(user);
	}
	@Override
	public void findByEmail(User user) {
		Optional<User> resposta = userRepository.findByEmail(user.getEmail());
		if(resposta.isPresent() && !resposta.get().getId().equals(user.getId())) {
			throw new DataIntegratyViolationException("E-mail já cadastrado no sistema");
		}
	}

	@Override
	public User update(User user) {	
		findByEmail(user);
		return userRepository.save(user);
	}

	@Override
	public void delete(Integer id) {
		findById(id);
		userRepository.deleteById(id);					
	}

	
		
}
