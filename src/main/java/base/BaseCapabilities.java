package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseCapabilities {
        public static Properties prop;
        public static WebDriver driver;

        public static ExtentHtmlReporter htmlReporter;
        public static ExtentReports extent;
        public static ExtentTest test;
        public static ExtentTest childTest;


        // Initialize the driver
        public void driverInit() {
            readPropertyFile(); // Initialize the 'prop' object
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            // Create a new ChromeDriver object.
            driver = new ChromeDriver(options); // Assign to the class-level 'driver' variable

            driver.get(prop.getProperty("url"));
            Duration pageLoadTimeout = Duration.ofSeconds(Integer.parseInt(prop.getProperty("pageLoadTime")));
            Duration implicitlyWaitTimeout = Duration.ofSeconds(30);

            driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout);
            driver.manage().timeouts().implicitlyWait(implicitlyWaitTimeout);

            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
        }

        // get the data from property file to 'prop' object.
        public void readPropertyFile() {
            try {

                FileInputStream propFile = new FileInputStream("src/main/java/configuration/test.properties");
                prop = new Properties();
                prop.load(propFile);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }


        //Setup extend report v4
        public void setExtentReport() {

            htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/extentReports/MMTFunctionalTestReport.html");
            htmlReporter.config().setDocumentTitle("Makemytrip Automation Report"); // Title
            htmlReporter.config().setReportName("Functional Testing"); // Report Name
            htmlReporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);

            extent.setSystemInfo("Host name", "localhost");
            extent.setSystemInfo("Environemnt", "QA");
            extent.setSystemInfo("user", "Abhishek");
        }
    }
