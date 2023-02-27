package by.itacademy.news.service.exception;

public class FieldsEmptyException extends Exception{

	private static final long serialVersionUID = 1L;

	public FieldsEmptyException() {
		super();
	}

	public FieldsEmptyException(String message) {
		super(message);
	}

	public FieldsEmptyException(Exception e) {
		super(e);
	}

	public FieldsEmptyException(String message, Exception e) {
		super(message, e);
	}
	

}
