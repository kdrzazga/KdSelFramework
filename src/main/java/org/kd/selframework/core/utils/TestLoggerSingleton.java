package org.kd.selframework.core.lib;

public class TestLoggerSingleton {
    private static TestLogger globalLogger = null;

    public static TestLogger getInstance() {
        if (globalLogger == null) {
            globalLogger = new TestLogger();
        }
        return globalLogger;
    }

}
