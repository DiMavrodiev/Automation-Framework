package com.automationframework.api.exceptions;

import com.automationframework.core.reporter.Report;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/25/18
 */

public class WrapElementException extends RuntimeException
{

    public WrapElementException(Throwable cause) {

            super(cause.getClass().getName() + "Cannot create instance of Element.", cause);
            Report.info("Cannot create instance of Element."
                    + cause.getClass().getName(), cause);
    }
}