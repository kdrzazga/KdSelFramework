package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.pageobjects.Page;
import org.kd.selframework.core.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.kd.selframework.core.pageobjects.LocatorHelper.quietlyFindElement;

public final class PO_BootstrapDatePicker extends Page {

    private static WebElement selectDateButton;
    private static WebElement datePickerTextBox;
    private static WebElement todayButton;

    private final By selectDateButtonSelector = By.xpath("//span[contains(@class, 'input-group-addon')]/i");
    private final By todayButtonSelector = By.xpath("//th[@class='today' and @style='display: table-cell;' and contains(text(), 'Today')]");
    private final By datePickerTextBoxSelector = By.xpath("//div[@id='sandbox-container1']/div/input[@type='text']");

    public PO_BootstrapDatePicker(WebDriver driver) {
        super(driver, PropertiesReader.readFromConfig("app-under-test.bootstrapdatepicker.url"));
    }

    @Override
    public void findElements() {
        selectDateButton = quietlyFindElement(driver, selectDateButtonSelector);
        datePickerTextBox = quietlyFindElement(driver, datePickerTextBoxSelector);
    }

    public void clickTodayButton(){
        selectDateButton.click();
        findCalendarItems();
        todayButton.click();
    }

    public String getSelectedDate(){
        return datePickerTextBox.getText();
    }

    private void findCalendarItems(){
        todayButton = quietlyFindElement(driver, todayButtonSelector);
    }

}
