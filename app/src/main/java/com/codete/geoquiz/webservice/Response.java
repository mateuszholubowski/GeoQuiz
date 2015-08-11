package com.codete.geoquiz.webservice;

import com.google.gson.annotations.Expose;

/**
 * Created by Mateusz on 2015-08-08.
 */
public class Response {

    @Expose
    private boolean hasError;
    @Expose
    private int errorCode;
    @Expose
    private String message;

    /**
     * @return The hasError
     */
    public boolean hasError() {
        return hasError;
    }

    /**
     * @param hasError The hasError
     */
    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    /**
     * @return The errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode The errorCode
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
