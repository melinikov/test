package pageObjects.com.flysas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LangLocationAirport implements pageObjects.com.flysas.FindElem {
    private WebDriver driver;

    private String language_path;
    private String location_path;
    private String airport_path;
    private String save_change_button_path;

    public LangLocationAirport(WebDriver driver) {
        this.driver = driver;

        this.language_path = "//*[@id=\"language\"]";
        this.location_path = "//*[@id=\"location\"]";
        this.airport_path = "//*[@id=\"airport\"]";
        this.save_change_button_path = "//*[@id=\"market-selectors\"]/div[4]/button";
    }

    public String get_language() {
        WebElement language = findElem(this.language_path);
        return language.getAttribute("value");
    }

    public void save_changes() {
        WebElement button_save = findElem(this.save_change_button_path);
        button_save.click();
    }

    public WebElement findElem(String path) {
        return driver.findElement(By.xpath(path));
    }
}
