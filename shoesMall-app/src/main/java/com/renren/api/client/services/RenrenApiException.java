package com.renren.api.client.services;

/**
 * @author 李勇(yong.li@opi-corp.com) 2011-2-17
 */
public class RenrenApiException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int errorCode;

    public RenrenApiException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
