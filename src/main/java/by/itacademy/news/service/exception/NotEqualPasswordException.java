package by.itacademy.news.service.exception;

public class NotEqualPasswordException extends Exception{

	private static final long serialVersionUID = 1L;

	public NotEqualPasswordException() {
		super();
	}

	public NotEqualPasswordException(String message) {
		super(message);
	}

	public NotEqualPasswordException(Exception e) {
		super(e);
	}

	public NotEqualPasswordException(String message, Exception e) {
		super(message, e);
	}
	

}
