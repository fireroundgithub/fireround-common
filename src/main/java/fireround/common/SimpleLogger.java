package fireround.common;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class SimpleLogger {

    private static final String LEVEL_DEBUG = "DEBUG";

    private static final String LEVEL_INFO = "INFO";

    private static final String LEVEL_WARN = "WARN";

    private static final String LEVEL_ERROR = "ERROR";

    private static final PrintStream infoLogger = System.out;

    private static final PrintStream errLogger = System.err;

    public static void info(String message) {
        infoLogger.println(format(LEVEL_INFO, message));
    }

    public static void warn(String message) {
        infoLogger.println(format(LEVEL_WARN, message));
    }

    public static void error(String message) {
        errLogger.println(format(LEVEL_ERROR, message));
    }

    public static void debug(String message) {
        if (Boolean.getBoolean(Logger.class.getPackage() + ".debug")) {
            infoLogger.println(format(LEVEL_DEBUG, message));
        }
    }

    private static String format(String loggerLevel, String message) {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())
                + " [" + Thread.currentThread().getName() + "]  "
                + " [" + loggerLevel + "]  "
                + ": " + message;
    }
}