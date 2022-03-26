package com.azevedoedison.userapi.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.azevedoedison.userapi.dto.UserDTO;
import com.azevedoedison.userapi.model.User;
import com.azevedoedison.userapi.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Integer id){	
										//pega um user e converte pra um DTO e retorna no body.
		return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDTO.class));
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){	
		List<User> list = userService.findAll();
		                        //pega cada objeto da lista, mapeia os atributos e o transforma em um dto.
		List<UserDTO> listDTO = list.stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO){
		var user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userService.create(user);   
     
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        		.buildAndExpand(user.getId()).toUri();
        
        return ResponseEntity.created(uri).build();
        //return ResponseEntity.created(uri).body(mapper.map(userService.create(user), UserDTO.class));       
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO userDTO){
		userDTO.setId(id);
		var user = new User();
        BeanUtils.copyProperties(userDTO, user);
		userService.update(user);
		return ResponseEntity.ok().body(userDTO);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable Integer id){
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
