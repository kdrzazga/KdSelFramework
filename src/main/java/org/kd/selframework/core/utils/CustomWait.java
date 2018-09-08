package org.kd.selframework.core.utils;

import org.openqa.selenium.WebElement;

public class CustomWait {

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

    public static void waitForElementVisible(WebElement element) {
        waitForElementVisibility(true, element, 5, 1, TestLoggerSingleton.getInstance());
    }

    public static void waitForElementHidden(WebElement element) {
        waitForElementVisibility(false, element, 5, 1, TestLoggerSingleton.getInstance());
    }

    public static void waitForElementVisibility(boolean isVisible, WebElement element, int timeout, int polling, TestLogger logger) {
        int currentPolling = 0;

        String expectedStylePrefix = "visibility: ";
        String visibility = isVisible ? "visible" : "hidden";

        while (currentPolling < timeout) {
            try {
                Thread.sleep(polling * 1000);

                if (element.getAttribute("style").contains(expectedStylePrefix + "visible" + visibility))
                    return;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentPolling += timeout;
        }
        logger.error("Element didn't appear.");
    }

    public static void wait(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
