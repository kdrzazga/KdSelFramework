package org.kd.selframework.core.utils;

import org.openqa.selenium.WebDriver;

public class WindowUtils {

    public static void switchWindow(WebDriver driver, String url, boolean switchToGivenURL) {
        for (String winHandle : driver
                .getWindowHandles()) {
            driver
                    .switchTo()
                    .window(winHandle);
            String currentURL = driver
                    .getCurrentUrl();
            boolean isSwitchedToGivenURL = currentURL.contains(url);
            if (isSwitchedToGivenURL == switchToGivenURL) {
                return;
            }
        }
    }
}
