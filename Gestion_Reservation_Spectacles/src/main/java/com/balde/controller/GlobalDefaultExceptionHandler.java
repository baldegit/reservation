package com.balde.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	public static final String DEFAULT_ERROR_VIEW = "error";
	
	@ExceptionHandler(Exception.class)
	public String defaultErrorHandler(HttpServletRequest req, Exception e,Model model) throws Exception {
		
		model.addAttribute("exception", e);
		model.addAttribute("url", req.getRequestURL());
		model.addAttribute("timestamp", new Date().toString());
		model.addAttribute("status", 500);
		
		return this.DEFAULT_ERROR_VIEW;
	}
}
