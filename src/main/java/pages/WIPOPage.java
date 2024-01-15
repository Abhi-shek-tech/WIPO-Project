package pages;
import base.BaseCapabilities;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.awaitility.Awaitility;

import java.time.Duration;

public class WIPOPage extends BaseCapabilities {
    private final WebDriverWait wait;
    @FindBy(xpath="//h2[text()='PAT-INFORMED']")
    WebElement patInformed;

    @FindBy(xpath="//input[@class='searchField']")
    WebElement searchField;

    @FindBy(xpath="//button[@class='margin-right']")
    WebElement searchButton;

    @FindBy(xpath="//h3[text()='DISCLAIMER']")
    WebElement disclaimer;

    @FindBy(xpath="//button[@class='green']")
    WebElement acceptButton;

    @FindBy(xpath="//div[@class='title cropper']")
    WebElement searchedTitle;

    @FindBy(xpath="(//td[contains(text(),'WIPO')]/parent::tr/following-sibling::tr/td)[2]")
    WebElement wipoPublicationNumber;

    @FindBy(xpath="(//td[contains(text(),'WIPO')]/parent::tr/following-sibling::tr)[2]")
    WebElement wipoPublicationDate;


    // constructor

    public WIPOPage(){
        super();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }


    // actions
    public void patInformedLogo() {
        patInformed.isDisplayed();
    }


    public void searchFieldTextBox() {
        searchField.click();
        searchField.sendKeys(prop.getProperty("Company"));
    }

    public void searchIcon() {
        Awaitility.await().atMost(Duration.ofSeconds(20))
                .until(() -> {
                    searchButton.click();
                    // Add your condition here, e.g., return true if the expected state is reached
                    return isSearchCompleted();
                });
    }

    private boolean isSearchCompleted() {
        return searchButton.isDisplayed();
    }

    public void disclaimerPopUp() {
        disclaimer.isDisplayed();
    }
    public void clickAcceptButton() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptButton));
        acceptButton.click();
    }
    public void searchTitle()
    {
        searchedTitle.click();
    }

    public void returnPublicationNumber() {
        Awaitility.await()
                .atMost(Duration.ofSeconds(20))
                .pollInterval(Duration.ofSeconds(1)) // Set your desired poll interval here
                .until(() -> {
        String publicationNumber = wipoPublicationNumber.getText();
        test.log(Status.INFO,"The publication date available :- "+ publicationNumber);
        System.out.println(publicationNumber);
        return isPublicationNumberAvailable();
});
        }

private boolean isPublicationNumberAvailable() {

        return wipoPublicationNumber.isDisplayed();
        }


public void returnPublicationDate(){
        String publicationDate = wipoPublicationDate.getText();
        test.log(Status.INFO,"The publication date is :- " + publicationDate);
        System.out.println(publicationDate);
    }
}