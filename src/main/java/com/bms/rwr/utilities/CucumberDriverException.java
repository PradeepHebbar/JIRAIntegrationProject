package com.bms.rwr.utilities;

public class CucumberDriverException extends Exception {
	private static final long serialVersionUID = 1L;

	public CucumberDriverException() {
	}

	public CucumberDriverException(String message) {
		super(message);
	}

	public CucumberDriverException(Throwable cause) {
		super(cause);
	}

	public CucumberDriverException(String message, Throwable cause) {
		super(message, cause);
	}

	public CucumberDriverException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}


}
