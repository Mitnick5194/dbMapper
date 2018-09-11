package com.ajie.dao.redis;

/**
 * @author niezhenjie
 */
public class JedisException extends Exception {

	private static final long serialVersionUID = 1L;

	public JedisException() {
		super();
	}

	public JedisException(String message) {
		super(message);
	}

	public JedisException(Throwable e) {
		super(e);
	}

	public JedisException(String messge, Throwable e) {
		super(messge, e);
	}

}
