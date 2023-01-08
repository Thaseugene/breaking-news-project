package by.itacademy.news.data;

public class DataException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public DataException() {
		super();
	}
	
	public DataException(String message) {
		super(message);
	}
	
	public DataException(Exception e) {
		super(e);
	}
	
	public DataException(String message, Exception e) {
		super(message, e);
	}
	

}
