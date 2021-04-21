package com.alkemy.blog.challenge.controller;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javassist.NotFoundException;

@ControllerAdvice
public class ExceptionController {
	
	private Log logger = LogFactory.getLog(ExceptionController.class);
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchElementException.class, NotFoundException.class})
    public String error404(HttpServletRequest req, Exception e){
		logger.error("Request: " + req.getRequestURL() + " raised " + e);
        return "views/error404";
    }
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public String error400(HttpServletRequest req, Exception e){
		logger.error("Request: " + req.getRequestURL() + " raised " + e);
        return "views/error400";
    }
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IOException.class)
    public String error500(HttpServletRequest req, Exception e){
		logger.error("Request: " + req.getRequestURL() + " raised " + e);
        return "views/error400";
    }
}