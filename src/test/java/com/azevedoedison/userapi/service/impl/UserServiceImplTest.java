package com.azevedoedison.userapi.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.azevedoedison.userapi.model.User;
import com.azevedoedison.userapi.repository.UserRepository;
import com.azevedoedison.userapi.service.exception.DataIntegrityViolationException;
import com.azevedoedison.userapi.service.exception.ObjectNotFoundException;

@SpringBootTest
public class UserServiceImplTest {
	
	private static final Integer ID = 1;
	private static final String NAME = "Valdir";
	private static final String EMAIL = "valdir@gmail.com";
	private static final String PASSWORD = "123";
	
	@InjectMocks
	private UserServiceImpl service;
	
	@Mock
	private UserRepository repository;
	
	@Mock
	private ModelMapper mapper;
	private User user;
	private Optional<User> optionalUser;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}
	
	private void startUser() {
		user = new User(ID,NAME,EMAIL, PASSWORD);
		optionalUser = Optional.of(new User(ID,NAME,EMAIL, PASSWORD));		
	}
	
	@Test  //Quando procurar por ID e não encontrar retorne uma exceção.
	void whenFindByIdThenReturnAnObjectNotFoundException() {
		Mockito.when(repository.findById(Mockito.anyInt()))
			.thenThrow(new ObjectNotFoundException("Objeto não encontrado"));
		
		try {
			service.findById(ID);
		}catch (Exception e) {
			/*Assegure pra mim que a Classe de Excessão é a classe correta*/
			assertEquals(ObjectNotFoundException.class, e.getClass());
			
			/*Assegure pra mim que a mensagem de excessão é a mensagem correta*/
			assertEquals("Objeto não encontrado",e.getMessage());
		}
		
	}
	
	@Test //retorne uma Lista de Usuários
	void whenFindAllThenReturnAnListOfUserInstance() {
		/*Mock consulta e Retorne uma lista de 1 usuário*/
		Mockito.when(repository.findAll()).thenReturn(List.of(user));
		
		List<User> response = service.findAll();   
		
		/*Assegure pra mim que a lista de usuários não é nula*/
		Assertions.assertNotNull(response);
		
		/*Assegure pra mim que a lista de usuário só tem 1 registro (porque eu moquei ela somente com 1) */
		Assertions.assertEquals(1, response.size());
		
		
		/*Assegure pra mim que o registro contido na lista seja de uma classe de usuário*/
		Assertions.assertEquals(User.class, response.get(0).getClass());
		
		Assertions.assertEquals(ID, response.get(0).getId());
		Assertions.assertEquals(EMAIL, response.get(0).getEmail());
		Assertions.assertEquals(NAME, response.get(0).getName());
		Assertions.assertEquals(PASSWORD, response.get(0).getPassword());		
		
	}
	
	
	@Test //Quando procurar por ID retorne uma instância de usuário
	void whenFindByIdThenReturnAnUserInstance() {
		
		
		Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);		
		User response = service.findById(ID);
		
		/*Assegure pra mim que o meu response não será nulo*/
		Assertions.assertNotNull(response);
		
		/*Assegure pra mim que a resposta será uma Classe do tipo User */
		Assertions.assertEquals(User.class, response.getClass());	
		
		/*Assegure pra mim que o usuário retornado tem o mesmo ID que eu solicitei*/
		Assertions.assertEquals(ID, response.getId());
		
		/*Assegure pra mim que o nome retornado tem o mesmo nome que eu solicitei*/
		Assertions.assertEquals(NAME, response.getName());
		
		/*Assegure pra mim que o email retornado tem o mesmo email que eu solicitei*/
		Assertions.assertEquals(EMAIL, response.getEmail());
					
		
	}
			
	
	@Test //Quando salvar o usuário retorne sucesso.
	void whenCreateThenReturnSuccess() {
		/*Moquei o método salvar retornando um usuário*/
		Mockito.when(repository.save(Mockito.any())).thenReturn(user);
		
		User response = service.create(user);
		
		/*Assegure pra mim que a resposta não será nula*/
		Assertions.assertNotNull(response);
		
		/*Assegure pra mim que a resposta será uma Classe do tipo User */
		Assertions.assertEquals(User.class, response.getClass());				
		
		/*Assegure pra mim que o nome retornado tem o mesmo nome que eu gravei*/
		Assertions.assertEquals(NAME, response.getName());
		
		/*Assegure pra mim que o email retornado tem o mesmo email que eu gravei*/
		Assertions.assertEquals(EMAIL, response.getEmail());
		
		/*Assegure pra mim que o password retornado tem o mesmo password que eu gravei*/
		Assertions.assertEquals(PASSWORD, response.getPassword());
	}
	
	@Test //Quando salvar com um email já cadastrado retorne exceção.
	void whenCreateThenReturnAnDataIntegrityViolationException() {
		Mockito.when(repository.findByEmail(Mockito.anyString()))
			.thenReturn(optionalUser);
		
		try {
			optionalUser.get().setId(2); //Estou criando um usuário id 2 com o mesmo email do usuário 1.
			service.create(user);
		}catch (Exception e) {
			/*Assegure pra mim que a Classe de Excessão é a classe correta*/
			assertEquals(DataIntegrityViolationException.class, e.getClass());
		}
	}
	
	@Test //Quando salvar o usuário retorne sucesso.
	void whenUpdateThenReturnSuccess() {
		/*Moquei o método salvar retornando um usuário*/
		Mockito.when(repository.save(Mockito.any())).thenReturn(user);
		
		User response = service.update(user);
		
		/*Assegure pra mim que a resposta não será nula*/
		Assertions.assertNotNull(response);
		
		/*Assegure pra mim que a resposta será uma Classe do tipo User */
		Assertions.assertEquals(User.class, response.getClass());				
		
		/*Assegure pra mim que o nome retornado tem o mesmo nome que eu gravei*/
		Assertions.assertEquals(NAME, response.getName());
		
		/*Assegure pra mim que o email retornado tem o mesmo email que eu gravei*/
		Assertions.assertEquals(EMAIL, response.getEmail());
		
		/*Assegure pra mim que o password retornado tem o mesmo password que eu gravei*/
		Assertions.assertEquals(PASSWORD, response.getPassword());
	}
	
	@Test //Quando atualizar com um email já cadastrado retorne exceção.
	void whenUpdateThenReturnAnDataIntegrityViolationException() {
		Mockito.when(repository.findByEmail(Mockito.anyString()))
			.thenReturn(optionalUser);
		
		try {
			optionalUser.get().setId(2); //Estou criando um usuário id 2 com o mesmo email do usuário 1.
			service.update(user);
		}catch (Exception e) {
			/*Assegure pra mim que a Classe de Excessão é a classe correta*/
			assertEquals(DataIntegrityViolationException.class, e.getClass());
		}
	}
		
	@Test //Quando pesquisar por e-mail já existente retorne erro de integridade (ex: cadastro com dois emails iguais).
	void whenFindByEmailThenReturnDataIntegrityViolationException() {
		
		/*Moquei o método Pesquisar por email retornando uma exceção de integridade de dados.*/	
		Mockito.when(repository.findByEmail(Mockito.any()))
		.thenThrow(new DataIntegrityViolationException("E-mail já cadastrado no sistema"));
							
		try {
			service.findByEmail(user);
		}catch (Exception e) {
			/*Assegure pra mim que a Classe de Excessão é a classe correta*/
			assertEquals(DataIntegrityViolationException.class, e.getClass());
			
			/*Assegure pra mim que a mensagem de excessão é a mensagem correta*/
			assertEquals("E-mail já cadastrado no sistema",e.getMessage());
		}		
	}
	
	@Test
	void deleteWithSuccess() {
		Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
		//para métodos void  
		//Não faça nada quando o repository for chamado no método delete passando um inteiro.
		Mockito.doNothing().when(repository).deleteById(Mockito.anyInt());

		service.delete(ID);
		
		/*Assegure pra mim que o meu repository só foi chamado 1 vez no método deleteById*/
		Mockito.verify(repository,Mockito.times(1)).deleteById(Mockito.anyInt());
	}
	
	
	@Test //Teste de deleção sem sucesso
	void deleteWithoutSuccess() {
		Mockito.when(repository.findById(Mockito.anyInt()))
		.thenThrow(new ObjectNotFoundException("Objeto não encontrado"));
		try {
			service.delete(ID);
		}catch (Exception e) {
			/*Assegure pra mim que a Classe de Excessão é a classe correta*/
			assertEquals(ObjectNotFoundException.class, e.getClass());
			
			/*Assegure pra mim que a mensagem de excessão é a mensagem correta*/
			assertEquals("Objeto não encontrado",e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
		
}
