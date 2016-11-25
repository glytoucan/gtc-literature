package org.glycoinfo.rdf.service.exception;

public class LiteratureException extends Exception {

	/*
   * 
   * eclipse generated serial id
   * 
   */
  private static final long serialVersionUID = -8669688150635152658L;

  public LiteratureException() {
	}

	/**
	 * @param message
	 */
	public LiteratureException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public LiteratureException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public LiteratureException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public LiteratureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
