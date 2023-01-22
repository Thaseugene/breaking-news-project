package by.itacademy.news.util.parsing;

public class ParsingParamException extends Exception{

	private static final long serialVersionUID = 1L;

	public ParsingParamException() {
		super();
	}

	public ParsingParamException(String message) {
		super(message);
	}

	public ParsingParamException(Exception e) {
		super(e);
	}

	public ParsingParamException(String message, Exception e) {
		super(message, e);
	}
	

}
