package pageObjects.com.flysas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;

public class PanelFlight implements pageObjects.com.flysas.FindElem {
    private WebDriver driver;

    private boolean two_way;
    private boolean date_return_is_clicked;
    private boolean date_from_is_clicked;
    private String city_to_path;
    private String city_from_path;
    private String date_return_path;
    private String date_from_path;
    private String button_search_path;
    private String passengers_menu_path;
    private String passengers_menu_add_adults_path;
    private String passengers_menu_number_adults_path;
    private String passengers_menu_add_child_path;
    private String passengers_menu_remove_adult_path;
    private String passengers_menu_add_infant_path;
    private String passengers_menu_number_children_path;
    private String passengers_menu_number_infant_path;
    private String message_alert_path;

    public PanelFlight(WebDriver driver) {
        this.driver = driver;
        this.two_way = true;
        this.date_return_is_clicked = false;
        this.date_from_is_clicked = false;
        this.city_from_path = "//*[@id=\"origin\"]";
        this.city_to_path = "//*[@id=\"destination\"]";
        this.date_return_path = "//*[@id=\"Inbound\"]";
        this.date_from_path = "//*[@id=\"Outbound\"]";
        this.button_search_path = "//*[@id=\"search-button\"]";
        this.passengers_menu_path = "//*[@id=\"passengers\"]";
        this.passengers_menu_add_adults_path = "//*[@id=\"cepAddAdult\"]";
        this.passengers_menu_number_adults_path = "//*[@id=\"cepAdultPaxCount\"]";
        this.passengers_menu_add_child_path = "//*[@id=\"cepAddChild\"]";
        this.passengers_menu_remove_adult_path = "//*[@id=\"cepRemoveAdult\"]";
        this.passengers_menu_add_infant_path = "//*[@id=\"cepAddInfant\"]";
        this.passengers_menu_number_children_path = "//*[@id=\"cepChildPaxCount\"]";
        this.passengers_menu_number_infant_path = "//*[@id=\"cepInfantPaxCount\"]";
        this.message_alert_path = "//*[@id=\"message\"]";
    }

    public void enableOneTrip() {
        if (this.two_way) {
            this.two_way = false;
            this.date_return_is_clicked = false;
            String path = "//*[@id=\"OW\"]";
            findElem(path).click();
        }
    }

    public void enableTwoWay() {
        if (!this.two_way) {
            this.two_way = true;
            String path = "//*[@id=\"RT\"]";
            driver.findElement(By.xpath(path)).click();
        }
    }

    public void set_city_to(String city) {
        WebElement city_to = findElem(this.city_to_path);
        city_to.sendKeys(city);
    }

    public void set_city_from(String city) {
        WebElement city_to = findElem(this.city_from_path);
        city_to.sendKeys(city);
    }

    public void select_date_return(int tab, int tr, int td) {
        if (!this.date_return_is_clicked) {
            this.date_return_is_clicked = true;
            WebElement date_to_list = findElem(this.date_return_path);
            date_to_list.click();
        }
        select_date(tab, td, tr);
    }

    public void select_date_from(int tab, int tr, int td) {
        if (!this.date_from_is_clicked) {
            this.date_from_is_clicked = true;
            WebElement date_from_list = findElem(this.date_from_path);
            date_from_list.click();
        }
        select_date(tab, td, tr);
    }

    public String get_date_from() {
        WebElement date_from = findElem(this.date_from_path);
        return date_from.getAttribute("value");
    }

    public void click_button_search() {
        sleep(0.2);
        WebElement button_search = findElem(this.button_search_path);
        button_search.click();
    }

    public boolean get_status_passengers_menu() {
        WebElement passengers_menu = findElem(this.passengers_menu_path);
        sleep(1);
        return Boolean.parseBoolean(passengers_menu.getAttribute("aria-expanded"));
    }

    public int get_max_adults() {
        WebElement adult_add_button = findElem(this.passengers_menu_add_adults_path);
        sleep(1);
        added(adult_add_button);
        return get_adults_number();
    }

    public int get_adults_number() {
        WebElement adult_number = findElem(this.passengers_menu_number_adults_path);
        return Integer.parseInt(adult_number.getText());
    }

    public int get_infant_number() {
        WebElement infant_number = findElem(this.passengers_menu_number_infant_path);
        return Integer.parseInt(infant_number.getText());
    }

    public int get_child_number() {
        WebElement child_number = findElem(this.passengers_menu_number_children_path);
        return Integer.parseInt(child_number.getText());
    }

    public void add_child() {
        sleep(0.8);
        WebElement child_add = findElem(this.passengers_menu_add_child_path);
        child_add.click();
    }

    public void add_adult() {
        sleep(0.7);
        WebElement adult_add_button = findElem(this.passengers_menu_add_adults_path);
        adult_add_button.click();
    }

    public void remove_adult() {
        WebElement adult_remove = findElem(this.passengers_menu_remove_adult_path);
        adult_remove.click();
    }

    public int get_max_infants() {
        WebElement infant_add_button = findElem(this.passengers_menu_add_infant_path);
        sleep(0.8);
        added(infant_add_button);
        return get_infant_number();
    }

    public String get_message_alert() {
        WebElement msg = findElem(this.message_alert_path);
        return msg.getText();
    }

    //-----------------------------------------------------------------------------------------------------------------
    private void select_date(int tab, int tr, int td) {
        String date_path = "//*[@id=\"tab-1\"]/div/div[" + tab + "]/table/tbody/tr[" + tr + "]/td[" + td + "]";
        WebElement date = findElem(date_path);
        date.click();
    }

    private void sleep(double sec) {
        try {
            Thread.sleep((long) (1000 * sec));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void added(WebElement element) {
        boolean flag = true;
        while (flag) {
            element.click();
            String classes = element.getAttribute("class");
            if (Arrays.asList(classes.split(" ")).contains("disabled")) {
                flag = false;
            }
        }
    }

    public WebElement findElem(String path) {
        return driver.findElement(By.xpath(path));
    }
}
