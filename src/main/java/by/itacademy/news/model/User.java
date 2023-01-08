package by.itacademy.news.model;

import by.itacademy.news.model.enums.Role;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {
	private String name;
	private String surname;
	private String email;
    private String login;
    private String password;
	private final LocalDateTime registerDate;
    private Role role;


	public User(String name, String surname, String email, String login, String password, Role role) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.login = login;
		this.password = password;
		this.role = role;
		this.registerDate = LocalDateTime.now();
	}

	public String getLogin() {
        return login;
    }


    public String getPassword() {
        return password;
    }


    public Role getRole() {
        return role;
    }


    public void setRole(Role role) {
        this.role = role;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getRegisterDate() {
		return registerDate;
	}

}
