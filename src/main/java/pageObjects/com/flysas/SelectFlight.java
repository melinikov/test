package pageObjects.com.flysas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SelectFlight implements pageObjects.com.flysas.FindElem {
    WebDriver driver;

    private String message_alert_path;

    public SelectFlight(WebDriver driver) {
        this.driver = driver;

        this.message_alert_path = "//*[@id=\"lpc-error\"]/p";
    }

    public String get_message_alert() {
        sleep(6.2);
        WebElement alert = findElem(this.message_alert_path);
        return alert.getText();
    }

    //----------------------------------------------------------------------------------------------------------------
    public WebElement findElem(String path) {
        return driver.findElement(By.xpath(path));
    }

    private void sleep(double sec) {
        try {
            Thread.sleep((long) (1000 * sec));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
