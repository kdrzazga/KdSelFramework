package org.kd.selframework.core.lib;

/*
    Simplified version of log4j
 */
public class TestLogger {

    //constants stolen form org.apache.log4j.ANSIConsoleAppender
    private static final int NORMAL = 0;
    private static final int BRIGHT = 1;

    private static final int FOREGROUND_BLACK = 30;
    private static final int FOREGROUND_RED = 31;
    private static final int FOREGROUND_GREEN = 32;
    private static final int FOREGROUND_YELLOW = 33;
    private static final int FOREGROUND_BLUE = 34;
    private static final int FOREGROUND_MAGENTA = 35;

    private static final String PREFIX = "\u001b[";
    private static final String SUFFIX = "m";
    private static final char SEPARATOR = ';';
    private static final String END_COLOUR = PREFIX + SUFFIX;

    private static final String FATAL_COLOUR = PREFIX
            + BRIGHT + SEPARATOR + FOREGROUND_RED + SUFFIX;
    private static final String ERROR_COLOUR = PREFIX
            + NORMAL + SEPARATOR + FOREGROUND_RED + SUFFIX;
    private static final String WARN_COLOUR = PREFIX
            + NORMAL + SEPARATOR + FOREGROUND_YELLOW + SUFFIX;
    private static final String INFO_COLOUR = PREFIX
            + NORMAL+ SEPARATOR + FOREGROUND_BLUE + SUFFIX;

    public void log(String message){
        System.out.println(INFO_COLOUR + message);
    }

    public void warn(String message){
        System.out.println(WARN_COLOUR + message);
    }

}
