package com.bulletproof.ecommerce.core.dao.exception;

public class PersistentException extends Exception {
	public PersistentException(String msg, Exception e) {
		super(msg, e);
	}
}
