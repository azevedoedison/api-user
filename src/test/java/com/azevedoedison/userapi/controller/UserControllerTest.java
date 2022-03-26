package com.azevedoedison.userapi.controller;

import static org.mockito.Mockito.times;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.azevedoedison.userapi.dto.UserDTO;
import com.azevedoedison.userapi.model.User;
import com.azevedoedison.userapi.service.impl.UserServiceImpl;

@SpringBootTest
public class UserControllerTest {
	
	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserServiceImpl service;
	
	@Mock
	private ModelMapper mapper;
	
	private User user;
	private UserDTO userDTO;
	
	private static final Integer ID = 1;
	private static final String NAME = "Valdir";
	private static final String EMAIL = "valdir@gmail.com";
	private static final String PASSWORD = "123";
	
	@BeforeEach //antes de tudo rode:
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}
	
	private void startUser() {
		user =    new User(ID,NAME,EMAIL, PASSWORD);
		userDTO = new UserDTO(ID,NAME,EMAIL, PASSWORD);
	}
	
	@Test
	void whenFindByIdThenReturnSuccess(){	
		//mocado retornando um usuário quando procurar por ID.
		Mockito.when(service.findById(Mockito.anyInt())).thenReturn(user);
		//mocado quando converter uma entidade para um userDTO.
		Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(userDTO);
		
		ResponseEntity<UserDTO> response = userController.findById(ID);
		
		//Assegure pra mim que o retorno não seja nulo.
		Assertions.assertNotNull(response);
		//Assegure pra mim que o corpo da resposta não pode ser null (porque tem que vir um userDTO).
		Assertions.assertNotNull(response.getBody());
		//Assegure pra mim que a resposta do endpoint vai ter a classe do tipo ResponseEntity
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		//Assegure pra mim que UserDTO seja a mesma classe do corpo do nosso response.
		Assertions.assertEquals(UserDTO.class, response.getBody().getClass());		
		//Assegure pra mim que o ID que retornará no response será o mesmo ID que eu passei na pesquisa
		Assertions.assertEquals(ID, response.getBody().getId());		
		
	}
	
	@Test  //quando utilizar o método findall retorne uma lista de UserDTO
	void whenFindAllThenReturnAListOfUserDTO(){	
		//mocando para retornar uma lista de usuário
		Mockito.when(service.findAll()).thenReturn(List.of(user));
		//mocando o mapeamento que coleta a entidade e converte pra uma lista de dto.
		Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(userDTO);
		
		ResponseEntity<List<UserDTO>> response = userController.findAll();
		
		//Assegure pra mim que o meu Response não será nulo		
		Assertions.assertNotNull(response);
		//Assegure pra mim que o body do meu Response não será nulo
		Assertions.assertNotNull(response.getBody());
		//Assegure pra mim que eu recebei um HTTPStatusCode OK
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		//Assegure pra mim que a resposta do endpoint vai ter a classe do tipo ResponseEntity
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		//Assegure pra mim que eu receberei um Arraylist.
		Assertions.assertEquals(ArrayList.class, response.getBody().getClass());
		//Assegure pra mim que o objeto dentro da Lista seja do tipo UserDTO
		Assertions.assertEquals(UserDTO.class, response.getBody().get(0).getClass());
		
		//Testando o conteúdo do primeiro registro da lista é igual ao mocado.
		Assertions.assertEquals(ID, response.getBody().get(0).getId());
		Assertions.assertEquals(EMAIL, response.getBody().get(0).getEmail());
		Assertions.assertEquals(NAME, response.getBody().get(0).getName());
		Assertions.assertEquals(PASSWORD, response.getBody().get(0).getPassword());
		
		
	}
	@Test  //Quando Salvar retorna um http status 201 - created
	void whenCreateThenReturnCreated(){		
		//mocando para retornar o usuário ao salvar
		Mockito.when(service.create(Mockito.any())).thenReturn(user);					
		//mocando o mapeamento que coleta a entidade e converte pra uma lista de dto.
		///Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(userDTO);
		
		ResponseEntity<UserDTO> response =  userController.create(userDTO);
	
		//Assegure pra mim que o status HTTP retornará 201 - Created
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		//Assegure pra mim que o URI Location não é nulo (porque ele redireciona pra uma uri do registro criado).
		Assertions.assertNotNull(response.getHeaders().get("Location"));
		//Assegure pra mim que a resposta do endpoint vai ter a classe do tipo ResponseEntity
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		
	}
	
	@Test  //Quando Salvar retorna um http status 200 - ok.
	void whenUpdateThenReturnSuccess(){
		Mockito.when(service.update(user)).thenReturn(user);
		//Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(userDTO);
		
		ResponseEntity<UserDTO> response = userController.update(ID, userDTO);
		
		//Assegure pra mim que o meu Response não será nulo		
		Assertions.assertNotNull(response);
		//Assegure pra mim que o body do meu Response não será nulo
		Assertions.assertNotNull(response.getBody());
		//Assegure pra mim que UserDTO seja a mesma classe do corpo do nosso response.
		Assertions.assertEquals(UserDTO.class, response.getBody().getClass());
		//Assegure pra mim que o status HTTP retornará 200 - ok
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		//Assegure pra mim que o ID que retornará no response será o mesmo ID que eu passei na pesquisa
		Assertions.assertEquals(ID, response.getBody().getId());		
		
		
		
	}
			
	@Test
	void whenDeleteReturnSuccess(){
		Mockito.doNothing().when(service).delete(Mockito.anyInt());
		
		ResponseEntity<UserDTO> response = userController.delete(ID);
		
		//Assegure pra mim que o meu Response não será nulo		
		Assertions.assertNotNull(response);
		//Assegure pra mim que a resposta do endpoint vai ter a classe do tipo ResponseEntity
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		//Assegure pra mim quantas vezes o delete foi chamado (tem que ter sido só uma vez)
		Mockito.verify(service, times(1)).delete(Mockito.anyInt());
		//Assegure pra mim que o stauts HTTP será 204 - No Content
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		
		
		
	}

}
