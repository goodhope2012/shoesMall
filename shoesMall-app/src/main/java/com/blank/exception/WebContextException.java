package com.blank.exception;

/**
 * User: liuhm
 * Date: 11-9-21
 */
public class WebContextException extends RuntimeException{
    private static final long serialVersionUID = 5825150140545701365L;

    public WebContextException() {
        super();
    }

    public WebContextException(String message) {
        super(message);
    }

    public WebContextException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebContextException(Throwable cause) {
        super(cause);
    }
}
