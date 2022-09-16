package edu.sru.thangiah.example3.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

/*
 * Structure for the database to hold the information on the user
 * 
 * @Entity, @Id and @Generated value should be from the javax.persistence library
 * @NonNull is from the org.springwork library 
 * 
 * As the User class is designated as a @Entity, the JPA (Java Persistence API), which is Hibernate, will be able to perform CRUD 
 * (Create, Read, Update, Delete) operations on the domain entities.
 * 
 * The name and e-mail have been constrained to @NoNull values and allows the Hibernate Validator for validating the constrained
 * fields before persisting or updating an entity to the database.
 */


@Entity
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NonNull
    private String name;
    
    @NonNull
    private String email;
    

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    // standard constructors / setters / getters / toString
}
