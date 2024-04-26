package com.example;


import java.io.FileInputStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest 
{
    public static Logger log = LogManager.getLogger(AppTest.class);
    WebDriver driver;
    ExtentReports reports;
    ExtentTest test;

    @BeforeMethod
    public void mybfre() throws Exception
    {
        driver=new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        test=reports.createTest("test started");
        log.info("URL is being opened!!!");
        driver.get("https://www.barnesandnoble.com/");
        log.info("URL opened Successfully!!!!!");
         PropertyConfigurator.configure("C:\\Users\\srini\\OneDrive\\Pictures\\demo\\src\\resources\\log4j.properties");

    }
    
    @Test(priority = 0)
    public void Test1() throws Exception
    {
        FileInputStream fs = new FileInputStream("D:\\mysheet.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheet("CC2");
        XSSFRow row = sheet.getRow(0);
        String name = row.getCell(0).getStringCellValue(); 
        driver.findElement(By.xpath("//*[@id=\'rhf_header_element\']/nav/div/div[3]/form/div/div[1]/a")).click();
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[1]/div/a[2]")).click();
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[2]/div/input[1]")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/span/button")).click();
        WebElement check1=driver.findElement(By.xpath("//*[@id='searchGrid']/div/section[1]/section[1]/div/div[1]/div[1]/h1"));
        Assert.assertTrue(check1.getText().contains("chetan bhagat"), "Name not found!");
        log.info("TestCase 1 passed Successfully!");
    }


    @Test(priority = 1)
    public void Test2() throws InterruptedException {
        WebElement audiobooks = driver.findElement(By.linkText("Audiobooks"));
        Thread.sleep(2000);
        Actions action = new Actions(driver);
        action.moveToElement(audiobooks).perform();
        driver.findElement(By.linkText("Audiobooks Top 100")).click();
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
        driver.findElement(By.linkText("Funny Story")).click();
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,500)");
        driver.findElement(By.xpath("//*[@id=\"commerce-zone\"]/div[2]/ul/li[2]/div/div/label/span")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"find-radio-checked\"]/div[1]/form/input[5]")).click();
        Thread.sleep(2000);
        WebElement cart = driver.findElement(By.xpath("//*[@id='add-to-bag-main']/div[1]"));
        String add = cart.getText();
        if (add.equals("Item Successfully Added To Your cart")) log.info("TestCase 2 Executed Successfully!");
        else
            log.error("Error Occurred!");
        Thread.sleep(2000);
    }


    @Test(priority = 2)
    public void Test3() throws Exception{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("windows.scrollBy(0,80)");
        driver.findElement(By.xpath("//*[@id='footer']/div/dd/div/div/div[1]/div/a[5]")).click();
        Thread.sleep(2000);
        js.executeScript("windows.scrollBy(0,100)");
        driver.findElement(By.xpath("//*[@id='rewards-modal-link']"));
        WebElement check2=driver.findElement(By.xpath("//*[@id=\"dialog-title\"]"));
        Assert.assertTrue(check2.getText().contains("Sign in or Create an Account"), "Sign in first!!");
        log.info("TestCase 3 passed Successfully!");
    }


    @AfterMethod
    public void myaftrmthd() throws Exception{
        log.info("Tests Executed!!!");
        driver.quit();
    }
}
