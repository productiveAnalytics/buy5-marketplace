package com.buy5.marketplace.exceptions;

import com.buy5.marketplace.model.Buy5Entity;

public class NotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2720517281675912619L;

	public NotFoundException(Class<? extends Buy5Entity> cls, String msg) {
		super("Class "+ cls.getName() +" not found..."+msg);
	}
}
