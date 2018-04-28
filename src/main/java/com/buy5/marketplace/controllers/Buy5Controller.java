package com.buy5.marketplace.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.buy5.marketplace.exceptions.NotFoundException;

public abstract class Buy5Controller {

	/**
	 * Exception Handler for all RESTful methods that throws NotFoundException
	 * 
	 * @param notFoundEx
	 * @param httpResponse
	 * 
	 * @throws IOException
	 */
	@ExceptionHandler
	void handleIllegalArgumentException(NotFoundException notFoundEx, HttpServletResponse response) throws IOException {
		 // http code: 204
	     response.sendError(HttpStatus.NO_CONTENT.value());
	}

}