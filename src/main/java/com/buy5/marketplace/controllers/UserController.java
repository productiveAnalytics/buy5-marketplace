package com.buy5.marketplace.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.buy5.marketplace.exceptions.NotFoundException;
import com.buy5.marketplace.model.Person;
import com.buy5.marketplace.repository.UserRepository;

@RestController
public class UserController extends Buy5Controller {
	@Autowired
	private UserRepository usersRepo; 
	
	@GetMapping(path="/users", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Person> getAll() {
		return usersRepo.findAll();
	}
	
	/**
	 * @see Buy5Controller#handleIllegalArgumentException(NotFoundException, javax.servlet.http.HttpServletResponse)
	 */
	@GetMapping(path="/user/{user_name}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Person getUserByUserName (@PathVariable(name="user_name") String userName) 
					throws NotFoundException 
	{
		Optional<Person> optionalPerson = usersRepo.findByUsermame(userName);
		if (optionalPerson.isPresent())
			return optionalPerson.get();
		else 
			throw new NotFoundException(Person.class, "User by userName "+ userName + " not found.");
	}
}
