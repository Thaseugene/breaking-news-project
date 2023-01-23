package by.itacademy.news.model;

import by.itacademy.news.model.constants.Role;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(registerDate, user.registerDate) && role == user.role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, surname, email, login, password, registerDate, role);
	}
}
