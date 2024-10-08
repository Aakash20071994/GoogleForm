package demo.wrappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

    public static void Text(WebElement element,String text){
        try{
            element.clear();
            element.sendKeys(text);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void radioButton(WebDriver driver,String radioButtonText){
        try{
            WebElement radioButton=driver.findElement(By.xpath("//span[contains(@class,'OvPDhc') and contains(text(),'"+radioButtonText+"')]/../../..//div[@class='vd3tt']"));
            radioButton.click();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public static void checkBox(WebDriver driver,String checkBoxText){
        try{
        WebElement checkbox=driver.findElement(By.xpath("//span[contains(@class,'snByac') and contains(text(),'"+checkBoxText+"')]/../../..//div[contains(@id,'i')]"));
        checkbox.click();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
     public static void dropDown(WebDriver driver,String dropDownText){
        try{
            WebElement dropdown=driver.findElement(By.xpath("//div[contains(@class,'QXL7Te')]//span[text()='"+dropDownText+"']"));
            dropdown.click();
        }
        catch(Exception e){
            e.printStackTrace();
        }
     }
     public static String datesevenDaysAgo(){
        LocalDate currentDate=LocalDate.now();
        LocalDate date7daysago=currentDate.minusDays(7);
        DateTimeFormatter dateFormate=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formateDate=date7daysago.format(dateFormate);
        return formateDate;
     }
     public static String epochTime(){
        long epochTime=System.currentTimeMillis()/1000;
        String epochTimeString=String.valueOf(epochTime);
        return epochTimeString;
     }
     public static void clickOnElement(ChromeDriver driver,WebElement element){
        try{
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",element);
        }
        catch(Exception e){
            e.printStackTrace();

        }
     }
     public static boolean handleAlert(ChromeDriver driver){
       boolean status=false;
       driver.switchTo().alert().dismiss();
       status=true;
       return status;
        
     }
}
