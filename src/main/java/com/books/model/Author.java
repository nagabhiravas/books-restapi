package com.books.model;

import java.time.LocalDate;

public class Author {
	
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@Override
	public String toString() {
		return String.format("%s-%s-%tD", getFirstName(), getLastName(), getDateOfBirth());
	}
}