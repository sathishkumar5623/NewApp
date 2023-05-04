package com.psa.demo;

 
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.psa.demo.DAO.UserRepository;
import com.psa.demo.model.User;
import com.psa.demo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class NewappApplicationTests {
	@Autowired
	private UserService service;
	
	@MockBean
	private UserRepository repository;

	/*@Test
	void contextLoads() {
	}*/
	@Test
	public void getUsersTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new User(375, "kumara", 31, "india"), 
				new User(958, "manish", 35, "singapore")).collect(Collectors.toList()));
		assertEquals(2, service.getUsers().size());
	}

	@Test
	public void getUserbyAddressTest() {
		String address = "Bangalore";
		when(repository.findByAddress(address))
				.thenReturn(Stream.of(new User(375, "nyamali", 31, "malylapore")).collect(Collectors.toList()));
		assertEquals(1, service.getUserbyAddress(address).size());
	}

	@Test
	public void saveUserTest() {
		User user = new User(999, "vijayan", 33, "kerala"); 
		when(repository.save(user)).thenReturn(user);
		assertEquals(user, service.addUser(user));
	}

	@Test
	public void deleteUserTest() {
		User user = new User(999, "vijayan", 33, "kerala");
		service.deleteUser(user);
		verify(repository, times(1)).delete(user);
	}

}

	
