package com.automationframework.core.reporter;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/24/18
 *
 * Transfers message to necessary end point
 */

public final class Report {

    public static Logger log = LogManager.getLogger(Report.class);

    private Report() {
    }

    public static void info(String message) {

        log.info(message);
    }

    public static void info(String message, Throwable throwable) {

        log.info(message + "\n" + ExceptionUtils.getStackTrace(throwable));
    }

    public static void debug(String message) {

        log.debug(message);
    }

    public static void debug(String message, Throwable throwable) {

        log.debug(message + "\n" + ExceptionUtils.getStackTrace(throwable));
    }

    public static void error(String message) {

        log.error(message);
    }

    public static void error(String message, Throwable throwable) {

        log.error(message + "\n" + ExceptionUtils.getStackTrace(throwable));
    }
}
