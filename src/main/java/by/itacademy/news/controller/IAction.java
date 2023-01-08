package by.itacademy.news.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IAction {
	
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ;

}
