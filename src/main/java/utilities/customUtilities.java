package utilities;

import base.BaseCapabilities;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;


public class customUtilities extends BaseCapabilities {

        /*
         * 1. ExpWait()
         * 2. getExcelData()
         * 3. getCurrentAndReturnDates()
         * 4. customXpath()
         * 5. getScreenshot()
         */


        /**************************************
         * Description: Explicit wait for a web element
         **************************************/
        public static void ExpWait(WebElement element) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }

        /**************************************
         * Description: Data provider for the test cases
         **************************************/
    public static String EXCEL_SHEET_PATH = prop.getProperty("excelSheetPath");

    public FileInputStream Input;
    public FileOutputStream output;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;

    String path = System.getProperty("user.dir") + "/src/test/resources/DatasetsforQTrip.xlsx";
    public int getRowCount(String sheetName) throws IOException
    {
        Input=new FileInputStream(path);
        workbook=new XSSFWorkbook(Input);
        sheet = workbook.getSheet(sheetName);
        int row =sheet.getLastRowNum();
        workbook.close();
        Input.close();
        return row;
    }

    public int getColumnCount(String sheetName, int row_num) throws IOException
    {
        Input=new FileInputStream(path);
        workbook=new XSSFWorkbook(Input);
        sheet = workbook.getSheet(sheetName);
        row=sheet.getRow(row_num);
        int cell_count =row.getLastCellNum();
        workbook.close();
        Input.close();
        return cell_count;
    }

    public String getCellData(String sheetName, int row_num, int column) throws IOException
    {
        Input=new FileInputStream(path);
        workbook=new XSSFWorkbook(Input);
        sheet = workbook.getSheet(sheetName);
        row=sheet.getRow(row_num);
        cell=row.getCell(column);
        DataFormatter formatter = new DataFormatter();
        String data;
        try
        {
            data =formatter.formatCellValue(cell);
        }
        catch(Exception e)
        {
            data="";
        }
        workbook.close();
        Input.close();
        return data;
    }

    public Object[][] dpMethod (Method m) throws IOException{
        System.out.println("DP executed for test case : "+m.getName());
        int total_rows=getRowCount(m.getName());
        int total_columns=getColumnCount(m.getName(), 1);

        String[][] testcaseData = new String[total_rows][total_columns-1];

        for(int i=1 ; i<=total_rows; i++){
            for(int j=1; j<total_columns; j++){
                testcaseData[i-1][j-1]= getCellData(m.getName(), i, j);
            }
        }
        System.out.println(Arrays.deepToString(testcaseData));
        return testcaseData;
    }

    /**************************************
     * Description: Dynamic Xpath
     **************************************/
    public static By customXpath(String xpath, String param) {
        String rawPath = xpath.replaceAll("%replace%", param);
        return By.xpath(rawPath);
    }

    public static String departureDate;
    public static String returnDate;

    public static customUtilities getCurrentAndReturnDates() {
        customUtilities date = new customUtilities();
        Calendar cal=Calendar.getInstance();

        cal.add(Calendar.DATE, 1); // please delete this line. added for test the tomorrow date.

        String[] dateArr=cal.getTime().toString().split(" ");
        customUtilities.departureDate=dateArr[0]+" "+dateArr[1]+" "+dateArr[2]+" "+dateArr[5];
        cal.add(Calendar.DATE, Integer.parseInt(prop.getProperty("NoOfdays")));
        dateArr=cal.getTime().toString().split(" ");
        customUtilities.returnDate=dateArr[0]+" "+dateArr[1]+" "+dateArr[2]+" "+dateArr[5];
        return date;
    }

    public static String getScreenshot(String imageName) {

        String currentDate = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String destination = System.getProperty("user.dir") + "/Screenshots/" + imageName + currentDate + ".png";
        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (IOException e) {
            System.out.println("Failed to capture the screen "+ e.getMessage());
        }

        return destination;
    }
}



