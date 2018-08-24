package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.pageobjects.Page;
import org.kd.selframework.core.pageobjects.QuietSelect;
import org.kd.selframework.core.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;

import static org.kd.selframework.core.pageobjects.LocatorHelper.quietlyFindElement;

public class PO_SelectDropdownListPage extends Page {

    private static QuietSelect weekdayDropdownList;
    private static WebElement selectedWeekdayMessage;
    private static QuietSelect statesMultiDropdownList;
    private static WebElement multiSelectMessagePara;
    private static WebElement firstSelectedButton;
    private static WebElement getAllSelectedButton;

    private final By weekdayDropdownListSelector = By.id("select-demo");
    private final By selectedWeekdayMessageSelector = By.className("selected-value");
    private final By statesMultiDropdownListSelector = By.id("multi-select");
    private final By multiSelectMessageParaSelector = By.className("getall-selected");
    private final By firstSelectedButtonSelector = By.id("printMe");
    private final By getAllSelectedButtonSelector = By.id("printAll");

    //private final Map<String, WebElement> captionButtonMap;

    public PO_SelectDropdownListPage(WebDriver driver) {
        super(driver, PropertiesReader.readFromConfig("app-under-test.selectdropdownlist.url"));
        //captionButtonMap = new Hashtable<>(2);
    }

    @Override
    public void findElements() {
        weekdayDropdownList = new QuietSelect(quietlyFindElement(driver, weekdayDropdownListSelector));
        selectedWeekdayMessage = quietlyFindElement(driver, selectedWeekdayMessageSelector);

        statesMultiDropdownList = new QuietSelect(quietlyFindElement(driver, statesMultiDropdownListSelector));
        multiSelectMessagePara = quietlyFindElement(driver, multiSelectMessageParaSelector);
        firstSelectedButton = quietlyFindElement(driver, firstSelectedButtonSelector);
        getAllSelectedButton = quietlyFindElement(driver, getAllSelectedButtonSelector);

        //initCaptionButtonMap();
    }

    /*
        private void initCaptionButtonMap() {
            captionButtonMap.put("First Selected", firstSelectedButton);
            captionButtonMap.put("Get All Selected", getAllSelectedButton);
        }
    */

    public void selectWeekday(String weekday) {
        weekdayDropdownList.selectByValue(weekday);
    }

    public void clickButtononMultiSelectListDemoDiv(String buttonCaption) {
        /*Optional<WebElement> button = Optional
                .ofNullable(captionButtonMap.get(buttonCaption));

        button.ifPresent(WebElement::click);*/

        if (buttonCaption.equals("First Selected"))
            firstSelectedButton.click();
        else getAllSelectedButton.click();
    }

    public String readSelectedDayMessage() {
        return Optional
                .ofNullable(selectedWeekdayMessage.getText())
                .orElse("");
    }

    public String readSelectedDay() {
        return readSelectedDayMessage().replace("Day selected :- ", "");
    }

    public void selectStatesInMultiSelectList(List<String> states) {
        statesMultiDropdownList.deselectAll();
        states.forEach(state -> statesMultiDropdownList.selectByValue(state.trim()));
    }

    public String readSelectedStatesMesage() {
        return Optional
                .ofNullable(multiSelectMessagePara.getText())
                .orElse("");
    }
}
