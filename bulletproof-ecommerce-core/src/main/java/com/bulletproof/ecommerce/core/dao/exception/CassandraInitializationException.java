/**
 * 
 */
package com.bulletproof.ecommerce.core.dao.exception;

/**
 * @author a276247
 *
 */
public class CassandraInitializationException extends RuntimeException {

	public CassandraInitializationException(String msg, Exception e) {
		super(msg, e);
	}

}
