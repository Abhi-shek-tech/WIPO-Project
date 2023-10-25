import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.MakeMyTripHomePage;
import pages.MakeMyTripHotelBookingPage;
import utilities.customUtilities;

public class TestSearchHotel extends TestSetup {

    @Test
    public void bookHotel(){

        test = extent.createTest("Validate search Flight");
        test.assignCategory("Sanity Test");

        MakeMyTripHomePage home = new MakeMyTripHomePage();

//        home.enterEmail();
//        test.log(Status.INFO, "Entered the email");
//
//        home.continueBtn();
//        test.log(Status.INFO, "Clicked on continue button");
//
//        home.enterPass();
//        test.log(Status.INFO, "Entered password");
//
//        home.clickLogin();
//        test.log(Status.INFO, "Clicked on login button");

        home.popup();
        test.log(Status.INFO, "Clicking on cross button");

        home.verifyMMTLogo();
        test.log(Status.INFO, "The pop up is closed, page is read for booking flights");

        home.clickHotels();
        test.log(Status.INFO, "clicked the hotels field");

        home.enterLocation();
        test.log(Status.INFO, "the required location was given");

        home.enterDepartureDate();
        test.log(Status.INFO, "departure date entered");

        home.enterReturnDate();
        test.log(Status.INFO, "return date entered");

        home.roomAndGuest();
        test.log(Status.INFO, "Clicked on room and guest");

        home.numRoom();
        test.log(Status.INFO, "selected the number of required room");

        home.numGuest();
        test.log(Status.INFO, "selected the number of required guests");

//        home.numChild();
//        test.log(Status.INFO, "selected the number of required child");
//
//        home.childAge();
//        test.log(Status.INFO, "selected the number of required child");

        home.clickApply();
        test.log(Status.INFO, "clicked apply button");

        home.clickSearch();
        test.log(Status.INFO, "clicked search button");

        MakeMyTripHotelBookingPage hotel = new MakeMyTripHotelBookingPage();

        hotel.numberOfProperties();
        test.log(Status.INFO, "The available properties are correctly displayed");

        hotel.searchHotelTextBox();
        test.log(Status.INFO,"The expected hotel is searched");

        hotel.clickFirst();
        test.log(Status.INFO,"clicked on the hotel");

        hotel.login();
        test.log(Status.INFO,"clicked on login to book the hotel");

        home.enterEmail();
        test.log(Status.INFO, "Entered the email");

//        home.continueBtn();
//        test.log(Status.INFO, "Clicked on continue button");
//
//        home.enterPass();
//        test.log(Status.INFO, "Entered password");
//
//        home.clickLogin();
//        test.log(Status.INFO, "Clicked on login button");
    }


    @AfterMethod
    public void checkResult(ITestResult result) throws Exception   {

        if (result.getStatus() == ITestResult.FAILURE) {

            test.log(Status.FAIL, result.getName()+" Test Failed.");
//			   test.log(Status.FAIL, "Test Case Failed is " + result.getThrowable());
//			   test.fail(MarkupHelper.createLabel(result.getName()+" Test case Failed", ExtentColor.RED));

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
