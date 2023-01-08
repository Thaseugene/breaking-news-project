package by.itacademy.news.data;

public class UserDataException extends Exception{

	private static final long serialVersionUID = 1L;

	public UserDataException() {
		super();
	}

	public UserDataException(String message) {
		super(message);
	}

	public UserDataException(Exception e) {
		super(e);
	}

	public UserDataException(String message, Exception e) {
		super(message, e);
	}

}
