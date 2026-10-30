// Author: haveela | Project: Route_Planning_Automation
package src.main.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Page {

    private WebDriver driver;
    private Actions action;

    public Page(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
    }

    public void elementDisplayed(WebElement ele,String msg){
        ele.isDisplayed();

    }

    public void hoverClickElement(WebElement ele){
        action.moveToElement(ele).click().build().perform();
    }
    public void hoverElement(WebElement ele){
        action.moveToElement(ele).build().perform();
    }

    public void isTextPresent(WebElement ele,String msg){
        String text = ele.getText();
        Assert.assertTrue(text.contentEquals(msg),"Expected : "+msg + " But Found :"+text);
    }

    public void click(WebElement ele,String msg){
        ele.click();
    }

    public void select(WebElement ele,String by,String value){
        Select dropdown = new Select(ele);
        if(by.equalsIgnoreCase("visibleText")){
            dropdown.selectByVisibleText(value);
        }
        else if(by.equalsIgnoreCase("Index")){
            dropdown.selectByIndex(Integer.parseInt(value));
        }
        else{
            dropdown.selectByValue(value);
        }

    }



}
