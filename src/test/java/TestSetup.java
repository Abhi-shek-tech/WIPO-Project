import base.BaseCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class TestSetup extends BaseCapabilities {

    @BeforeSuite
    public void setUp() {
        driverInit();
        readPropertyFile();
        setExtentReport();
    }



    @AfterSuite
    public void tearDown() {
        extent.flush();
        driver.quit();
    }




}