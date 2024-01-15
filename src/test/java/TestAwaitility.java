import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.WIPOPage;
import utilities.customUtilities;

public class TestAwaitility extends TestSetup{

        @Test
        public void searchIBU(){

            test = extent.createTest("Validate Publication Date and Number");
            test.assignCategory("Sanity Test");

            WIPOPage wipo = new WIPOPage();

            wipo.patInformedLogo();
            test.log(Status.INFO, "Pat Informed Logo is displayed");

            wipo.searchFieldTextBox();
            test.log(Status.INFO, "The search field text box is displayed");

            wipo.clickAcceptButton();
            test.log(Status.INFO,"Click is performed on the accept button" );

            wipo.searchTitle();
            test.log(Status.INFO,"The searched title is displayed" );

            wipo.returnPublicationNumber();
            test.log(Status.INFO,"The publication date available");

            wipo.returnPublicationDate();
            test.log(Status.INFO,"The publication number available " );


//            System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
//            WebDriver driver = new ChromeDriver();
//            driver.get("https://www.wipo.int/patinformed/");
//            driver.quit();
        }

    @AfterMethod
    public void checkResult(ITestResult result) throws Exception   {

        if (result.getStatus() == ITestResult.FAILURE) {

            test.log(Status.FAIL, result.getName()+" Test Failed.");
            String screenshotPath = customUtilities.getScreenshot(result.getName());
            test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            test.addScreenCaptureFromPath(screenshotPath);

        } else if (result.getStatus() == ITestResult.SKIP) {
            //test.log(Status.SKIP, result.getName()+ " Test Case Skipped");
            test.skip(MarkupHelper.createLabel(result.getName()+" Test case Skipped", ExtentColor.YELLOW));
            test.skip(result.getThrowable());

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            //test.log(Status.PASS, result.getName()+ " Test Case Passed");
            test.log(Status.PASS, MarkupHelper.createLabel("Test passed", ExtentColor.GREEN));
            test.pass(MarkupHelper.createLabel("Test passed", ExtentColor.GREEN));
        }

    }

}



