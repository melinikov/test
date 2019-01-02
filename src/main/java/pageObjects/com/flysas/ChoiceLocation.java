package pageObjects.com.flysas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChoiceLocation implements pageObjects.com.flysas.FindElem {
    private WebDriver driver;

    public ChoiceLocation(WebDriver driver) {
        this.driver = driver;
    }

    public void close_choice_page() {
        String path = "//*[@id=\"ms-close\"]";
        WebElement cross = findElem(path);
        cross.click();
    }

    public void select_german_language() {
        String path = "//*[@id=\"market_DE\"]/a";
        WebElement temp = findElem(path);
        temp.click();
        path = "//*[@id=\"submit-market\"]";
        temp = findElem(path);
        temp.click();
    }

    public WebElement findElem(String path) {
        return driver.findElement(By.xpath(path));
    }
}
