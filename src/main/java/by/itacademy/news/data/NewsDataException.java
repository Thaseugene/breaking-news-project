package by.itacademy.news.data;

public class NewsDataException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public NewsDataException() {
		super();
	}
	
	public NewsDataException(String message) {
		super(message);
	}
	
	public NewsDataException(Exception e) {
		super(e);
	}
	
	public NewsDataException(String message, Exception e) {
		super(message, e);
	}

}
