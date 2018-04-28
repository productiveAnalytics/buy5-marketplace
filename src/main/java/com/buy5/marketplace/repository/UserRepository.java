package com.buy5.marketplace.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.buy5.marketplace.model.Person;

@Repository
public class UserRepository {
	Person[] pers = {
						new Person(0, "admin", "admin"),
						new Person(1, "LalitC", "lchawathe"),
						new Person(2, "larry", "larry")
					};
	private List<Person> users = Arrays.asList(pers);
	
	public List<Person> findAll() {
		return users;
	}
	
	public Optional<Person> findByUsermame(String userName) {
		return users.stream()
					.filter(person -> person.getUserName().equalsIgnoreCase(userName))
					.findFirst();
	}
}
