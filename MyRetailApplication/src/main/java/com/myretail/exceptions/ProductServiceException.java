package com.myretail.exceptions;

/**
 * Generic Exception class for the exceptions
 * 
 * @author Sreehari
 *
 */
public class ProductServiceException extends Exception {

	/**
	 * Generated serial Id
	 */
	private static final long serialVersionUID = 935945124352791688L;

	private String errorCode = "PRODUCT_SERVICE_EXCEPTION";

	public ProductServiceException() {

	}

	public ProductServiceException(String erroMessage) {
		super(erroMessage);
	}

	public ProductServiceException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
	}

	public ProductServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
