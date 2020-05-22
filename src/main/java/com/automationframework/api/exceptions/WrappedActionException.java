package com.automationframework.api.exceptions;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 1/18/19
 */

public class WrappedActionException extends RuntimeException {

    public WrappedActionException(String message) {

        super(message);
    }
}