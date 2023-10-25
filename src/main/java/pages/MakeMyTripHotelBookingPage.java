package pages;

import base.BaseCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.customUtilities;

public class MakeMyTripHotelBookingPage extends BaseCapabilities {
    @FindBy(xpath="//h1[contains(text(),'Properties in')]")
    WebElement properties;

    @FindBy(xpath="//input[contains(@aria-label,'hotel name')]")
    WebElement searchHotel;

    @FindBy(xpath="//li[@role='option']")
    WebElement listOptions;

    @FindBy(xpath="(//div[@class='persuasion ']//span)[2]")
    WebElement clickLogin;

    @FindBy(xpath="//input[@id='username']")
    WebElement emailField;

    @FindBy(xpath="//button[@data-cy='continueBtn']")
    WebElement continueButton;


    // constructor

    public MakeMyTripHotelBookingPage(){
        super();
        PageFactory.initElements(driver, this);
    }


    // actions
    public void numberOfProperties() {
        properties.isDisplayed();
    }

    public void searchHotelTextBox() {
        searchHotel.click();
        searchHotel.sendKeys(prop.getProperty("HotelName"));
    }

    public void clickFirst()
    {
        customUtilities.ExpWait(listOptions);
        listOptions.click();
    }
    public void login()
    {
        clickLogin.click();
    }
}

