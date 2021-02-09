package org.weather.basic;

import org.apache.log4j.Logger;

public class Log {
	 // Initial Log4j log
    private static Logger Log = Logger.getLogger(Log.class.getName());

    // log begins
    public static void startTestCase(String sTestCaseName) {
        Log.info("------------------ " + sTestCaseName + "  " +"Testing begins ------------------");
    }

    // log ends
    public static void endTestCase(String sTestCaseName) {
        Log.info("------------------ " + sTestCaseName + "  " +"Testing ends ---------------");

    }

    public static void info(String message) {
        Log.info(message);
    }

    public static void warn(String message) {
        Log.warn(message);
    }

    public static void error(String message) {
        Log.error(message);
    }

    public static void fatal(String message) {
        Log.fatal(message);
    }

    public static void debug(String message) {
        Log.debug(message);
    }
}
