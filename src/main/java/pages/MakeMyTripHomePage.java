package pages;
import base.BaseCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.customUtilities;

public class MakeMyTripHomePage extends BaseCapabilities {
    @FindBy(xpath="//div[@id='SW']//section/span")
    WebElement crossButton;

    @FindBy(xpath="//input[@id='username']")
    WebElement emailField;

    @FindBy(xpath="//button[@data-cy='continueBtn']")
    WebElement continueButton;

    @FindBy(xpath="//input[@id='password']")
    WebElement passwordField;

    @FindBy(xpath="//button[@type='submit']")
    WebElement login;

    @FindBy(xpath="//span[@class='mybizLoginClose']")
    WebElement numcrossButton;

    @FindBy(xpath="//img[@alt='Make My Trip']")
    WebElement mmtLogo;
    @FindBy(xpath="(//span[contains (@class, 'headerIconTextAlignment')])[2]")
    WebElement hotels;
    @FindBy(xpath="//span[text()= 'City, Property name or Location']")
    WebElement locationText;
    @FindBy(xpath="//input[@title='Where do you want to stay?']")
    WebElement locationField;

    @FindBy(xpath = "//div[contains(@class, 'searchedResult')]")
    WebElement firstOption;

    @FindBy(xpath="//input[@id='checkin']")
    WebElement checkIn;

    @FindBy(xpath="//input[@id='checkout']")
    WebElement checkOut;
    String departureDate = "//div[@aria-label='%replace%' and @aria-disabled='false']";
    String returnDate = "//div[@aria-label='%replace%' and @aria-disabled='false']";

    @FindBy(xpath="//input[@id='guest']")
    WebElement roomGuest;

    @FindBy(xpath="//span[@data-testid='room_count']")
    WebElement roomCount;
    @FindBy(xpath="//span[@data-testid='adult_count']")
    WebElement adultCount;
    @FindBy(xpath="//span[@data-testid='children_count']")
    WebElement childrenCount;

    String dropdowns= "//ul[@class='gstSlct__list']//li[text()='%replace%']";

    @FindBy(xpath="//button[text()='Apply']")
    WebElement apply;
    @FindBy(xpath="//button[text()='Search']")
    WebElement search;


    // constructor
    public MakeMyTripHomePage(){
        super();
        PageFactory.initElements(driver, this);
    }


    // actions
    public void popup() {
        crossButton.click();
    }

    public void enterEmail()
    {
        emailField.click();
        emailField.sendKeys(prop.getProperty("emailAddress"));
    }

    public void continueBtn() {
        customUtilities.ExpWait(continueButton);
        continueButton.click();
    }

    public void enterPass() {
        passwordField.click();
        passwordField.sendKeys(prop.getProperty("password"));
    }
    public void clickLogin() {
        login.click();
    }
    public void clickNumCross() {
        numcrossButton.click();
    }

    public void verifyMMTLogo() {
        mmtLogo.isDisplayed();
    }

    public void clickHotels() {
        hotels.click();
    }

    public void enterLocation(){
        locationText.click();
        locationField.sendKeys(prop.getProperty("From"));
        customUtilities.ExpWait(firstOption);
        firstOption.click();

    }
    public void enterDepartureDate() {
        checkIn.click();
        customUtilities date = customUtilities.getCurrentAndReturnDates();
        driver.findElement(customUtilities.customXpath(departureDate, customUtilities.departureDate)).click();
    }

    public void enterReturnDate() {
        checkOut.click();
        customUtilities date = customUtilities.getCurrentAndReturnDates();
        driver.findElement(customUtilities.customXpath(returnDate, customUtilities.returnDate)).click();
    }

    public void roomAndGuest() {
        roomGuest.click();
    }

    public void numRoom() {
        roomCount.click();
        driver.findElement(customUtilities.customXpath(dropdowns, prop.getProperty("NoOfRoom"))).click();
    }
    public void numGuest() {
        adultCount.click();
        driver.findElement(customUtilities.customXpath(dropdowns, prop.getProperty("NoOfGuests"))).click();
    }
    public void numChild() {
        childrenCount.click();
        driver.findElement(customUtilities.customXpath(dropdowns, prop.getProperty("NoOfChild"))).click();
    }

    public void clickApply() {
        apply.click();

    }

    public void clickSearch() {
        search.click();

    }
}
