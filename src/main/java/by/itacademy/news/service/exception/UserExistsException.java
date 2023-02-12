package by.itacademy.news.service.exception;

public class UserExistsException extends Exception{

	private static final long serialVersionUID = 1L;

	public UserExistsException() {
		super();
	}

	public UserExistsException(String message) {
		super(message);
	}

	public UserExistsException(Exception e) {
		super(e);
	}

	public UserExistsException(String message, Exception e) {
		super(message, e);
	}
	

}
