package com.automationframework.api.exceptions;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/24/18
 */

public class SwitchToWindowException extends RuntimeException {

    public SwitchToWindowException() {
    }

    public SwitchToWindowException(String message) {

        super(message);
    }

    public SwitchToWindowException(String message, Throwable cause) {

        super(message, cause);
    }
}