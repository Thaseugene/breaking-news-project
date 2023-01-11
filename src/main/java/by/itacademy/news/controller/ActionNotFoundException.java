package by.itacademy.news.controller;

public class ActionNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public ActionNotFoundException() {
		super();
	}

	public ActionNotFoundException(String message) {
		super(message);
	}

	public ActionNotFoundException(Exception e) {
		super(e);
	}

	public ActionNotFoundException(String message, Exception e) {
		super(message, e);
	}
	

}
