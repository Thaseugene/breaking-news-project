package by.itacademy.news.util.validation;

public class PermissionDeniedException extends Exception{

	private static final long serialVersionUID = 1L;

	public PermissionDeniedException() {
		super();
	}

	public PermissionDeniedException(String message) {
		super(message);
	}

	public PermissionDeniedException(Exception e) {
		super(e);
	}

	public PermissionDeniedException(String message, Exception e) {
		super(message, e);
	}
	

}
