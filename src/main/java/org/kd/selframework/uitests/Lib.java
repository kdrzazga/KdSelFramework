package org.kd.selframework.uitests;

import org.kd.selframework.core.utils.TestLogger;
import org.kd.selframework.core.utils.TestLoggerSingleton;
import org.openqa.selenium.WebElement;

public class Lib {

    public static void waitForTextChange(WebElement element, String desiredText) {
        waitForTextChange(element, desiredText, 20, 2, TestLoggerSingleton.getInstance());
    }

    public static void waitForTextChange(WebElement element, String desiredText, int timeout, int polling, TestLogger logger) {
        int currentPolling = 0;

        while (currentPolling < timeout) {
            try {
                Thread.sleep(polling * 1000);

                if (element.getText().equals(desiredText))
                    return;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentPolling += timeout;
        }
        logger.error("Message didn't change.");
    }


}
