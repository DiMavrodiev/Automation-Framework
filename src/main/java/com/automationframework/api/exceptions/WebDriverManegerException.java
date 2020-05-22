package com.automationframework.api.exceptions;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 1/11/19
 */

public class WebDriverManegerException extends RuntimeException {

    public WebDriverManegerException(String message) {
        super(message);
    }

    public WebDriverManegerException(Throwable cause) {
        super(cause);
    }
}