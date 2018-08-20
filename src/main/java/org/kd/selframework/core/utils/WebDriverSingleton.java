package org.kd.selframework.core.utils;

import org.openqa.selenium.WebDriver;

public class WebDriverSingleton {
    private static WebDriver globalWebdriver = null;
    private static boolean closed = false;

    public static WebDriver getInstance() {
        if (globalWebdriver == null || closed) {
            globalWebdriver = WebDriverFactory.createDriverDefinedInConfig();
            closed = false;
        }
        return globalWebdriver;
    }

    public static void close() {
        globalWebdriver.close();
        closed = true;
    }
}

