// Author: haveela | Project: Route_Planning_Automation
package src.main.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private WebDriver driver;
    List<String> listOfInitialColumnData = new ArrayList<>();
    private List<String> tablesWithNoTotalRowHeader;
    List<String> listOfSortedDataInAscendingOrder = new ArrayList<>();
    List<String> listOfSortedDataInDescendingOrder = new ArrayList<>();
    List<Integer> listOfInitialColumnsDataNumber = new ArrayList<>();
    List<Integer> listOfSortedDataInAscendingOrderForNumber = new ArrayList<>();
    List<Integer> listOfSortedDataInDescendingOrderForNumber = new ArrayList<>();

    private int sizeOfTable;
    private By datePickerIcon = By.xpath("//label[text()='Start Date ']//following-sibling::div//span//i");
    private By monthBtn = By.xpath("//button[@type='button'][2]//span");
    private By yearBtn = By.xpath("//button[@type='button'][3]//span");
    private By dataPicker = By.xpath("//div//table[@role='grid']//tr//td//span");
    private By outsideClick = By.xpath("//div//span[@class='header-title']");
    private By selectAll_btn = By.xpath("//button[text()=' Select All ']");
    private By clearAll_btn = By.xpath("//button[text()=' Clear All ']");
    private By nodata_text = By.xpath("//li//span[text()='No Data to Display']");
    private By table = By.xpath("//table");
    private By totalRow = By.xpath("//table//tr//td//span[text()='Total']");
    private By classNext = By.xpath("//*[text()=' Next ']//parent::li");
    private By classPrevious = By.xpath("//*[text()=' Previous ']//parent::li");
    private By nextBtn = By.xpath("//*[text()=' Next ']");
    private By previousBtn = By.xpath("//*[text()=' Previous ']");
    private By nextButtonPath = By.xpath("//*[text()=' Next ']");
    private By pageNavigationPath = By.xpath("//nav[@role='navigation']//li[@class='small-screen']");
    private By firstPageLinkPath = By.xpath("//pagination-template//li[3]");
    private By columnSearchField = By.xpath("//div//input[@placeholder='Search']");


    //Storing All the Column Data of Table in a List on a Page
    public void storeAllTheColumnDataInAListOnAPage(WebDriver driver, List<String> listName, By columnDataPath) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        if (driver.findElements(totalRow).size() == 0) {
            sizeOfTable = driver.findElements(columnDataPath).size();
        } else {
            sizeOfTable = driver.findElements(columnDataPath).size() - 1;
        }
        for (int i = 0; i < sizeOfTable; i++) {
            String eachColumnData = driver.findElements(columnDataPath).get(i).getText().split(" ")[0];
            listName.add(eachColumnData);
        }
    }

    public void storeAllTheColumnDataInAListOnAPageNumbers(WebDriver driver, List<Integer> listNumber, By columnDataPath) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        if (driver.findElements(totalRow).size() == 0) {
            sizeOfTable = driver.findElements(columnDataPath).size();
        } else {
            sizeOfTable = driver.findElements(columnDataPath).size() - 1;
        }
        for (int i = 0; i < sizeOfTable; i++) {
            int eachColumnData = Integer.parseInt(driver.findElements(columnDataPath).get(i).getText().replaceAll(",", ""));
            listNumber.add(eachColumnData);
        }
    }

    public void storeAllTheNumberColumnDataInAListThroughPagination(WebDriver driver, List<Integer> listNumber, By columnDataPath) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        if (driver.findElements(nextButtonPath).size() == 0) {
            storeAllTheColumnDataInAListOnAPageNumbers(driver, listNumber, columnDataPath);
        } else if (driver.findElements(nextButtonPath).size() > 0) {
            String pageCount = driver.findElement(pageNavigationPath).getAttribute("textContent").split("/")[1].trim();
            int count = Integer.parseInt(pageCount);
            for (int i = 1; i <= count; i++) {
                storeAllTheColumnDataInAListOnAPageNumbers(driver, listNumber, columnDataPath);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                if (driver.findElements(nextButtonPath).size() > 0) {
                    if (driver.findElement(nextButtonPath).isEnabled()) {
                        driver.findElement(nextButtonPath).click();
                    } else {
                        Assert.fail("Next Button is Disabled at Page Number : " + i);
                    }
                }
            }
        }
    }

    //Storing All the Column Data of Table in a List through Pagination
    public void storeAllTheColumnDataInAListThroughPagination(WebDriver driver, List<String> listName, By columnDataPath) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        if (driver.findElements(nextButtonPath).size() == 0) {
            storeAllTheColumnDataInAListOnAPage(driver, listName, columnDataPath);
        } else {
            String pageCount = driver.findElement(pageNavigationPath).getAttribute("textContent").split("/")[1].trim();
            int count = Integer.parseInt(pageCount);
            for (int i = 1; i <= count; i++) {
                storeAllTheColumnDataInAListOnAPage(driver, listName, columnDataPath);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                if (driver.findElements(nextButtonPath).size() > 0) {
                    if (driver.findElement(nextButtonPath).isEnabled()) {
                        driver.findElement(nextButtonPath).click();
                    } else {
                        Assert.fail("Next Button is Disabled at Page Number : " + i);
                    }
                }
            }
        }
    }

    //Clearing All the Data stored in the Lists
    public void clearAllTheDataStoredInTheLists() {
        listOfInitialColumnData.clear();
        listOfSortedDataInAscendingOrder.clear();
        listOfSortedDataInDescendingOrder.clear();
    }

    public void clearAllTheNumberColumnDataStoredInTheLists() {
        listOfInitialColumnsDataNumber.clear();
        listOfSortedDataInAscendingOrderForNumber.clear();
        listOfSortedDataInDescendingOrderForNumber.clear();
    }

    //Clicking on the Column Sorting icon to get either the Ascending or Descending Order Data
    public void clickOnTheDesiredColumnSortingIcon(WebDriver driver, String columnName) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.xpath("//strong[text()='" + columnName + "']//parent::span//preceding-sibling::span//i")).isDisplayed();
        driver.findElement(By.xpath("//strong[text()='" + columnName + "']//parent::span//preceding-sibling::span//i")).click();
    }

    public void clickOnTheFirstPageLink(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        if (driver.findElements(pageNavigationPath).size() > 0) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.findElement(firstPageLinkPath).isDisplayed();
            driver.findElement(firstPageLinkPath).click();
            Thread.sleep(1000);
        }
    }

    //Validate the Column Data Sorting in Ascending Order and Descending Order
    public void validateTheColumnDataSortingInAscendingAndDescendingOrders(WebDriver driver, By columnDataPath, String columnName) throws InterruptedException {

        //Storing All the Column Data in a List initially
        storeAllTheColumnDataInAListThroughPagination(driver, listOfInitialColumnData, columnDataPath);

        // Sort the strings ignoring case sensitivity
        listOfInitialColumnData.sort(String.CASE_INSENSITIVE_ORDER);

        clickOnTheFirstPageLink(driver);

        //Clicking on the Column Sorting icon to get the Ascending Order Data
        clickOnTheDesiredColumnSortingIcon(driver, columnName);

        //Storing All the Ascended Column Data of Table in a List
        storeAllTheColumnDataInAListThroughPagination(driver, listOfSortedDataInAscendingOrder, columnDataPath);

        //Validating the Ascended Column Data of Table with the Ascending Order List
        Assert.assertEquals(listOfSortedDataInAscendingOrder, listOfInitialColumnData,
                "Expected Ascending order Column Data : " + listOfInitialColumnData + "\nBut found : " + listOfSortedDataInAscendingOrder + "\n");

        // Reverse the sorted list
        Collections.reverse(listOfInitialColumnData);

        clickOnTheFirstPageLink(driver);

        //Clicking on the Column Sorting icon to get the Descending Order Data
        clickOnTheDesiredColumnSortingIcon(driver, columnName);

        //Storing All the Descended Column Data of Table in a List
        storeAllTheColumnDataInAListThroughPagination(driver, listOfSortedDataInDescendingOrder, columnDataPath);

        //Validating the Descended Column Data of Table with the Descending Order List
        Assert.assertEquals(listOfInitialColumnData, listOfSortedDataInDescendingOrder,
                "Expected Descending order Column Data : " + listOfInitialColumnData + "\nBut found : " + listOfSortedDataInDescendingOrder + "\n");

        clearAllTheDataStoredInTheLists();
        clickOnTheFirstPageLink(driver);
    }

    public void validateThePercentageColumnDataSortingInAscendingAndDescendingOrders(WebDriver driver, By columnDataPath, String columnName) throws InterruptedException {
        //Storing All the Column Data in a List initially
        storeAllTheColumnDataInAListThroughPagination(driver, listOfInitialColumnData, columnDataPath);

        // Sort the strings ignoring case sensitivity
        Collections.sort(listOfInitialColumnData, new RangeComparator.NumberStringComparator());
        clickOnTheFirstPageLink(driver);

        //Clicking on the Column Sorting icon to get the Ascending Order Data
        clickOnTheDesiredColumnSortingIcon(driver, columnName);

        //Storing All the Ascended Column Data of Table in a List
        storeAllTheColumnDataInAListThroughPagination(driver, listOfSortedDataInAscendingOrder, columnDataPath);

        //Validating the Ascended Column Data of Table with the Ascending Order List
        Assert.assertEquals(listOfSortedDataInAscendingOrder, listOfInitialColumnData,
                "Expected Ascending order Column Data : " + listOfInitialColumnData + "\nBut found : " + listOfSortedDataInAscendingOrder + "\n");

        // Reverse the sorted list
        Collections.reverse(listOfInitialColumnData);

        clickOnTheFirstPageLink(driver);

        //Clicking on the Column Sorting icon to get the Descending Order Data
        clickOnTheDesiredColumnSortingIcon(driver, columnName);

        //Storing All the Descended Column Data of Table in a List
        storeAllTheColumnDataInAListThroughPagination(driver, listOfSortedDataInDescendingOrder, columnDataPath);

        //Validating the Descended Column Data of Table with the Descending Order List
        Assert.assertEquals(listOfInitialColumnData, listOfSortedDataInDescendingOrder,
                "Expected Descending order Column Data : " + listOfInitialColumnData + "\nBut found : " + listOfSortedDataInDescendingOrder + "\n");

        clearAllTheDataStoredInTheLists();
    }

    public void validateTheNumberColumnDataSortingInAscendingAndDescendingOrders(WebDriver driver, By columnDataPath, String columnName) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        if (driver.findElements(totalRow).size() == 0) {
            sizeOfTable = driver.findElements(columnDataPath).size();
        } else {
            sizeOfTable = driver.findElements(columnDataPath).size() - 1;
        }
        //Storing All the Column Data in a List initially
        storeAllTheNumberColumnDataInAListThroughPagination(driver, listOfInitialColumnsDataNumber, columnDataPath);

        // Sort the strings ignoring case sensitivity
        Collections.sort(listOfInitialColumnsDataNumber, Comparator.naturalOrder());

        clickOnTheFirstPageLink(driver);

        //Clicking on the Column Sorting icon to get the Ascending Order Data
        clickOnTheDesiredColumnSortingIcon(driver, columnName);

        //Storing All the Ascended Column Data of Table in a List
        storeAllTheNumberColumnDataInAListThroughPagination(driver, listOfSortedDataInAscendingOrderForNumber, columnDataPath);

        //Validating the Ascended Column Data of Table with the Ascending Order List
        Assert.assertEquals(listOfSortedDataInAscendingOrderForNumber, listOfInitialColumnsDataNumber,
                "Expected Ascending order Column Data : " + listOfInitialColumnsDataNumber + "\nBut found : " + listOfSortedDataInAscendingOrderForNumber + "\n");

        // Reverse the sorted list
        Collections.reverse(listOfInitialColumnsDataNumber);

        clickOnTheFirstPageLink(driver);

        //Clicking on the Column Sorting icon to get the Descending Order Data
        clickOnTheDesiredColumnSortingIcon(driver, columnName);

        //Storing All the Descended Column Data of Table in a List
        storeAllTheNumberColumnDataInAListThroughPagination(driver, listOfSortedDataInDescendingOrderForNumber, columnDataPath);

        //Validating the Descended Column Data of Table with the Descending Order List
        Assert.assertEquals(listOfInitialColumnsDataNumber, listOfSortedDataInDescendingOrderForNumber,
                "Expected Descending order Column Data : " + listOfInitialColumnsDataNumber + "\nBut found : " + listOfSortedDataInDescendingOrderForNumber + "\n");

        clearAllTheNumberColumnDataStoredInTheLists();
    }

    //Validate the Column Data Sorting in Ascending Order and Descending Order for Location Type column
    public void validateTheColumnDataSortingInAscendingAndDescendingOrdersForLocationType(WebDriver driver, By columnDataPath, String columnName) throws InterruptedException {

        //Storing All the Column Data in a List initially
        storeAllTheColumnDataInAListThroughPagination(driver, listOfInitialColumnData, columnDataPath);


        // Define a custom comparator
        Comparator<String> customComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                char firstChar1 = s1.charAt(0);
                char firstChar2 = s2.charAt(0);

                boolean isLetter1 = Character.isLetter(firstChar1);
                boolean isLetter2 = Character.isLetter(firstChar2);

                if (isLetter1 && !isLetter2) {
                    return -1; // s1 starts with a letter, s2 starts with a number, so s1 comes before s2
                } else if (!isLetter1 && isLetter2) {
                    return 1; // s1 starts with a number, s2 starts with a letter, so s1 comes after s2
                } else {
                    return s1.compareTo(s2); // Compare lexicographically
                }
            }
        };

        // Sort the list using the custom comparator
        listOfInitialColumnData.sort(customComparator);

        clickOnTheFirstPageLink(driver);

        //Clicking on the Column Sorting icon to get the Ascending Order Data
        clickOnTheDesiredColumnSortingIcon(driver, columnName);

        //Storing All the Ascended Column Data of Table in a List
        storeAllTheColumnDataInAListThroughPagination(driver, listOfSortedDataInAscendingOrder, columnDataPath);

        //Validating the Ascended Column Data of Table with the Ascending Order List
        Assert.assertEquals(listOfSortedDataInAscendingOrder, listOfInitialColumnData,
                "Expected Ascending order Column Data : " + listOfInitialColumnData + "\nBut found : " + listOfSortedDataInAscendingOrder + "\n");

        // Reverse the sorted list
        Collections.reverse(listOfInitialColumnData);

        clickOnTheFirstPageLink(driver);

        //Clicking on the Column Sorting icon to get the Descending Order Data
        clickOnTheDesiredColumnSortingIcon(driver, columnName);

        //Storing All the Descended Column Data of Table in a List
        storeAllTheColumnDataInAListThroughPagination(driver, listOfSortedDataInDescendingOrder, columnDataPath);

        //Validating the Descended Column Data of Table with the Descending Order List
        Assert.assertEquals(listOfInitialColumnData, listOfSortedDataInDescendingOrder,
                "Expected Descending order Column Data : " + listOfInitialColumnData + "\nBut found : " + listOfSortedDataInDescendingOrder + "\n");

        clearAllTheDataStoredInTheLists();
    }


    public void validateTheRangeColumnDataSortingInAscendingAndDescendingOrders(WebDriver driver, By columnDataPath, String columnName) throws InterruptedException {

        sizeOfTable = driver.findElements(columnDataPath).size();

        //Storing All the Column Data in a List initially
        storeAllTheColumnDataInAListThroughPagination(driver, listOfInitialColumnData, columnDataPath);

        // Sort the ranges based on the start value
        listOfInitialColumnData.sort(new RangeComparator());

        //Clicking on the Column Sorting icon to get the Ascending Order Data
        clickOnTheDesiredColumnSortingIcon(driver, columnName);

        //Storing All the Ascended Column Data of Table in a List
        storeAllTheColumnDataInAListThroughPagination(driver, listOfSortedDataInAscendingOrder, columnDataPath);

        //Validating the Ascended Column Data of Table with the Ascending Order List
        Assert.assertEquals(listOfSortedDataInAscendingOrder, listOfInitialColumnData,
                "Expected Ascending order Column Data : " + listOfInitialColumnData + "\nBut found : " + listOfSortedDataInAscendingOrder + "\n");

        // Reverse the sorted list
        Collections.reverse(listOfInitialColumnData);

        //Clicking on the Column Sorting icon to get the Descending Order Data
        clickOnTheDesiredColumnSortingIcon(driver, columnName);

        //Storing All the Descended Column Data of Table in a List
        storeAllTheColumnDataInAListThroughPagination(driver, listOfSortedDataInDescendingOrder, columnDataPath);

        //Validating the Descended Column Data of Table with the Descending Order List
        Assert.assertEquals(listOfInitialColumnData, listOfSortedDataInDescendingOrder,
                "Expected Descending order Column Data : " + listOfInitialColumnData + "\nBut found : " + listOfSortedDataInDescendingOrder + "\n");

        clearAllTheDataStoredInTheLists();
    }

    //Validate the Phone Number Column Data Sorting in Ascending Order and Descending Order
    public void validateThePhoneNumberColumnDataSortingInAscendingAndDescendingOrders(WebDriver driver, By columnDataPath, String columnName) throws InterruptedException {

        sizeOfTable = driver.findElements(columnDataPath).size();

        //Storing All the Column Data in a List initially
        storeAllTheColumnDataInAListThroughPagination(driver, listOfInitialColumnData, columnDataPath);

        // Sort the phone numbers
        listOfInitialColumnData.sort(new PhoneNumberComparator());

        clickOnTheFirstPageLink(driver);

        //Clicking on the Column Sorting icon to get the Ascending Order Data
        clickOnTheDesiredColumnSortingIcon(driver, columnName);

        //Storing All the Ascended Column Data of Table in a List
        storeAllTheColumnDataInAListThroughPagination(driver, listOfSortedDataInAscendingOrder, columnDataPath);

        //Validating the Ascended Column Data of Table with the Ascending Order List
        Assert.assertEquals(listOfSortedDataInAscendingOrder, listOfInitialColumnData,
                "Expected Ascending order Column Data : " + listOfInitialColumnData + "\nBut found : " + listOfSortedDataInAscendingOrder + "\n");


        // Reverse the sorted list
        Collections.reverse(listOfInitialColumnData);

        clickOnTheFirstPageLink(driver);

        //Clicking on the Column Sorting icon to get the Descending Order Data
        clickOnTheDesiredColumnSortingIcon(driver, columnName);

        //Storing All the Descended Column Data of Table in a List
        storeAllTheColumnDataInAListThroughPagination(driver, listOfSortedDataInDescendingOrder, columnDataPath);

        //Validating the Descended Column Data of Table with the Descending Order List
        Assert.assertEquals(listOfInitialColumnData, listOfSortedDataInDescendingOrder,
                "Expected Descending order Column Data : " + listOfInitialColumnData + "\nBut found : " + listOfSortedDataInDescendingOrder + "\n");

        clearAllTheDataStoredInTheLists();

        clickOnTheFirstPageLink(driver);
    }

    private static class PhoneNumberComparator implements Comparator<String> {
        @Override
        public int compare(String phoneNumber1, String phoneNumber2) {
            if (phoneNumber1.equals("-") && phoneNumber2.equals("-")) {
                return 0;
            } else if (phoneNumber1.equals("-")) {
                return -1;
            } else if (phoneNumber2.equals("-")) {
                return 1;
            }

            String numericPhoneNumber1 = phoneNumber1.replaceAll("[^0-9]", "");
            String numericPhoneNumber2 = phoneNumber2.replaceAll("[^0-9]", "");

            return numericPhoneNumber1.compareTo(numericPhoneNumber2);
        }
    }

    //Click on a Web Element using the Implicitly Wait
    public void clickOnAWebElementUsingImplicitlyWait(WebDriver driver, By elementXpath) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.findElement(elementXpath).isDisplayed();
        driver.findElement(elementXpath).click();
        Thread.sleep(1000);
    }

    public boolean verifyTheEmailInputIsValidOrNot(WebDriver driver, By emailIDFieldPath, String email) {
        WebElement emailInput = driver.findElement(emailIDFieldPath);
        emailInput.sendKeys(email);

        // Retrieve the entered value

        String enteredValue = emailInput.getAttribute("value");

        // Define the regular expression pattern for email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);

        // Validate the entered email using the regex pattern
        Matcher matcher = pattern.matcher(enteredValue);
        boolean isValidEmail = matcher.matches();
        return isValidEmail;
    }

    public boolean implicitWait(WebDriver driver, By path) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
            WebElement element = driver.findElement(path);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void executePythonScript(String pythonExePath, String pythonScriptPath, String inputJsonPath, String outputJsonPath, String type, String uuid) throws IOException, InterruptedException {
//        LOGGER.info("In ModelServiceImpl :: execute {} PythonScript",type);
        System.out.println("Executing Started");
        ProcessBuilder pb = new ProcessBuilder(pythonExePath, pythonScriptPath, type, inputJsonPath, outputJsonPath, uuid);
        System.out.println(pythonExePath + pythonScriptPath + type + inputJsonPath + outputJsonPath + uuid + "  Python terminal command");
        pb.redirectErrorStream(true);
        Process p = pb.start();
        p.waitFor();
        System.out.println("Execution Ended");
    }


    //    public void tableswithandwithouttotalrow{
//        tablesWithNoTotalRowHeader = new ArrayList<>(Arrays.asList(
//                "//table[@id='firmware_update_table']//tbody",
//                "//table[@id='alerts_table']//tbody",
//                "//table[@id='location_type_table']//tbody",
//                "//table[@id='locations_table']//tbody",
//                "//table[@id='hierarchy_table']//tbody",
//                "//table[@id='device_type_table']//tbody",
//                "//table[@id='devices_table']//tbody",
//                "//table[@id='device_profile_table']//tbody",
//                "//table[@id='revoke_certificates_table']//tbody",
//                "//table[@id='report_table']//tbody"
//        ));
//    }
    public void dateRange(String date) throws InterruptedException {
        String[] option = date.split("-");
        String year = option[0];
        String month = option[1];
        String day = option[2];
        System.out.println(year + " " + month + " " + day);
        driver.findElement(datePickerIcon).isDisplayed();
        driver.findElement(datePickerIcon).click();
        String monthText = driver.findElement(monthBtn).getText();
        System.out.println(monthText);
        String yearText = driver.findElement(yearBtn).getText();
        System.out.println(yearText);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(2000);
        while (!month.equals(monthText) || !year.equals(yearText)) {
            Thread.sleep(3000);
            month = driver.findElement(monthBtn).getText();
            year = driver.findElement(yearBtn).getText();
            // driver.findElement(By.xpath("//bs-datepicker-navigation-view/button[4]/span")).click();
        }
        int size = driver.findElements(dataPicker).size();
        for (int i = 0; i < size; i++) {
            String name = driver.findElements(dataPicker).get(i).getAttribute("class");
            if (!name.equals("disabled ng-star-inserted") && !name.equals("is-other-month ng-star-inserted")) {
                String ele = driver.findElements(dataPicker).get(i).getText();
                System.out.println(ele + " number");
                if (ele.equals(day)) {
                    driver.findElements(dataPicker).get(i).click();
                    break;
                }
            }
        }
    }
    public void verifyingtheColumnFilterForeachTable(WebDriver driver, String ColumnDropdown, String Entertext, String Columnindex) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.findElement(By.xpath("//strong[text()='" + ColumnDropdown + "']//ancestor::th//i[@triggers='manual']")).isDisplayed();
        driver.findElement(By.xpath("//strong[text()='" + ColumnDropdown + "']//ancestor::th//i[@triggers='manual']")).click();
        Thread.sleep(1000);
        driver.findElement(columnSearchField).isDisplayed();
        driver.findElement(columnSearchField).click();

        if (driver.findElements(By.xpath("//input[@name='" + Entertext + "']")).size() > 0) {
            driver.findElement(columnSearchField).sendKeys(Entertext);
            driver.findElement(By.xpath("//input[@name='" + Entertext + "']")).click();
            String ActualEntertext = driver.findElement(By.xpath("//input[@name='" + Entertext + "']")).getAttribute("value");

            Assert.assertEquals(ActualEntertext, Entertext, "Expected: " + Entertext + " But found: " + ActualEntertext);
            driver.findElement(By.xpath("//strong[text()='" + ColumnDropdown + "']//ancestor::th//i[@triggers='manual']")).click();
            driver.findElement(table).isDisplayed();

            if (driver.findElements(totalRow).size() == 0) {
                sizeOfTable = driver.findElements(By.xpath("//tr//td[" + Columnindex + "]")).size();
            } else {
                sizeOfTable = driver.findElements(By.xpath("//tr//td[" + Columnindex + "]")).size() - 1;
            }
            for (int i = 0; i < sizeOfTable; i++) {
                String eachColumnData = driver.findElements(By.xpath("//tr//td[" + Columnindex + "]")).get(i).getText().trim();
                System.out.println(".............." + eachColumnData);
                Assert.assertEquals(eachColumnData, Entertext);
            }

//            List<WebElement> cells = driver.findElements(By.xpath("//tr//td[" + Columnindex + "]"));
//            System.out.println("list---------------------------xxxx" + cells);
//            for (WebElement cell : cells) {
//
//                System.out.println("foreach cell------------------xxxx" + cell.getText());
//                Assert.assertEquals(cell.getText().trim(), Entertext);
//            }

            if (driver.findElements(classNext).size() > 0) {
                String nextbutton = driver.findElement(classNext).getAttribute("class");
                JavascriptExecutor js;
                while (!nextbutton.contains("disabled")) {
                    WebElement Element = driver.findElement(nextBtn);
                    // Scrolling down the page till the element is found
                    js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].scrollIntoView();", Element);
                    driver.findElement(nextBtn).click();

                    List<WebElement> nextCells = driver.findElements(By.xpath("//tr//td[" + Columnindex + "]"));
                    for (WebElement cell : nextCells) {
                        System.out.println("foreach cell------------------xxxx" + cell.getText());
                        Assert.assertEquals(cell.getText().trim(), Entertext);
                    }

                    nextbutton = driver.findElement(classNext).getAttribute("class");
                }

                String previousbtn = driver.findElement(classPrevious).getAttribute("class");
                System.out.println("---------------------------------------------" + previousbtn);
                while (!previousbtn.contains("disabled")) {
                    WebElement Element = driver.findElement(previousBtn);
                    // Scrolling down the page till the element is found
                    js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].scrollIntoView();", Element);
                    driver.findElement(previousBtn).click();

//                List<WebElement> prevCells = driver.findElements(By.xpath("//tr//td[" + Columnindex + "]"));
//                for (WebElement cell : prevCells) {
//                    Assert.assertEquals(cell.getText().trim(), Entertext);
//                }

                    previousbtn = driver.findElement(classPrevious).getAttribute("class");
                }
            }
        } else {
            driver.findElement(nodata_text).isDisplayed();
        }

        driver.findElement(outsideClick).click();
        driver.findElement(By.xpath("//strong[text()='" + ColumnDropdown + "']//ancestor::th//i[@triggers='manual']")).isDisplayed();
        driver.findElement(By.xpath("//strong[text()='" + ColumnDropdown + "']//ancestor::th//i[@triggers='manual']")).click();
        Thread.sleep(1000);
        driver.findElement(clearAll_btn).isDisplayed();
        driver.findElement(selectAll_btn).click();
        driver.findElement(clearAll_btn).click();
        Thread.sleep(1000);
        driver.findElement(outsideClick).click();


    }

}

// Sorting elements of any type in Ascending or Descending Order, while ignoring case sensitivity, within collections.
        class IgnoreCaseComparator implements Comparator<Object> {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof String && o2 instanceof String) {
                    return ((String) o1).compareToIgnoreCase((String) o2);
                } else {
                    return String.valueOf(o1).compareToIgnoreCase(String.valueOf(o2));
                }
            }
        }

// Sort the ranges based on the start value and end value
        class RangeComparator implements Comparator<String> {
            @Override
            public int compare(String range1, String range2) {
                double start1 = parseStart(range1);
                double start2 = parseStart(range2);
                int startComparison = Double.compare(start1, start2);
                if (startComparison != 0) {
                    return startComparison;
                }

                double end1 = parseEnd(range1);
                double end2 = parseEnd(range2);
                return Double.compare(end1, end2);
            }

            private double parseStart(String range) {
                String startStr = range.split("-")[0].trim();
                return Double.parseDouble(startStr);
            }

            private double parseEnd(String range) {
                String endStr = range.split("-")[1].trim();
                return Double.parseDouble(endStr);
            }

            static class NumberStringComparator implements Comparator<String> {
                @Override
                public int compare(String str1, String str2) {
                    Integer num1 = Integer.parseInt(str1);
                    Integer num2 = Integer.parseInt(str2);
                    return num1.compareTo(num2);
                }
            }


        }


