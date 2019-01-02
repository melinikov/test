package pages.com.flysas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageObjects.com.flysas.ChoiceLocation;
import pageObjects.com.flysas.LangLocationAirport;
import pageObjects.com.flysas.PanelFlight;
import pageObjects.com.flysas.SelectFlight;
import shell.ShellTests;

public class HomePageTest extends ShellTests {
    private ChoiceLocation choiceLocation;
    private PanelFlight panelFlight;
    private LangLocationAirport langLocationAirport;
    private SelectFlight selectFlight;

    @Before
    public void init() {
        driver.get("https://www.flysas.com");
        choiceLocation = new ChoiceLocation(driver);
        panelFlight = new PanelFlight(driver);
        langLocationAirport = new LangLocationAirport(driver);
        selectFlight=new SelectFlight(driver);
    }

    @Test
    public void Test1() {
        choiceLocation.close_choice_page();
        panelFlight.enableOneTrip();
        panelFlight.set_city_to("AMS");
        panelFlight.select_date_from(2, 2, 5);
        panelFlight.click_button_search();

        String second_page = "https://www.flysas.com/en/book/flights?search=OW_EWR-AMS-20190129_a1c0i0y0&view=upsell&bookingFlow=revenue";
        Assert.assertEquals(driver.getCurrentUrl(), second_page);
    }

    @Test
    public void Test2() {
        choiceLocation.close_choice_page();
        panelFlight.set_city_to("LAX");
        panelFlight.select_date_from(2, 5, 2);
        panelFlight.select_date_return(2, 3, 2);

        String date_expected = "Wed, 09 jan";
        String date_real = panelFlight.get_date_from();
        Assert.assertEquals(date_expected, date_real);
    }

    @Test
    public void Test3() {
        choiceLocation.select_german_language();

        String language_expected = "de";
        String language_real = langLocationAirport.get_language();
        Assert.assertEquals(language_expected, language_real);
    }

    @Test
    public void Test4() {
        choiceLocation.close_choice_page();
        panelFlight.enableOneTrip();
        panelFlight.set_city_to("AMS");
        panelFlight.select_date_from(2, 5, 2);

        boolean passengers_panel_status = panelFlight.get_status_passengers_menu();
        Assert.assertTrue(passengers_panel_status);
    }

    @Test
    public void Test5() {
        choiceLocation.close_choice_page();
        panelFlight.enableOneTrip();
        panelFlight.set_city_to("AMS");
        panelFlight.select_date_from(2, 5, 2);

        int max_number_adults_expected = 9;
        Assert.assertEquals(max_number_adults_expected, panelFlight.get_max_adults());
    }

    @Test
    public void Test6() {
        choiceLocation.close_choice_page();
        panelFlight.enableOneTrip();
        panelFlight.set_city_to("AMS");
        panelFlight.select_date_from(2, 5, 2);
        panelFlight.add_child();
        panelFlight.remove_adult();

        int number_adults_expected = 0;
        Assert.assertEquals(number_adults_expected, panelFlight.get_adults_number());
    }

    @Test
    public void Test7(){
        choiceLocation.close_choice_page();
        panelFlight.click_button_search();

        String message_expected="Please enter a valid city, airport or country you're traveling to.";
        Assert.assertEquals(message_expected,panelFlight.get_message_alert());
    }

    @Test
    public void Test8(){
        choiceLocation.close_choice_page();
        panelFlight.enableOneTrip();
        panelFlight.set_city_to("EWR");
        panelFlight.select_date_from(2, 2, 5);
        panelFlight.click_button_search();

        driver.get("https://www.flysas.com/en/book/flights?search=OW_EWR-EWR-20190129_a1c0i0y0&view=upsell&bookingFlow=revenue");
        String message_expected="Please check your selected cities.";
        Assert.assertEquals(message_expected,selectFlight.get_message_alert());
    }

    @Test
    public void Test9() {
        choiceLocation.close_choice_page();
        panelFlight.enableOneTrip();
        panelFlight.set_city_to("AMS");
        panelFlight.select_date_from(2, 5, 2);

        int number_children_expected = 4;
        for (int i = 0; i < number_children_expected; i++) {
            panelFlight.add_child();
        }
        int number_adults_expected = 5;
        Assert.assertEquals(number_adults_expected + number_children_expected, panelFlight.get_max_adults() + panelFlight.get_child_number());
    }

    @Test
    public void Test10() {
        choiceLocation.close_choice_page();
        panelFlight.enableOneTrip();
        panelFlight.set_city_to("AMS");
        panelFlight.select_date_from(2, 5, 2);

        int number_adult = 5;
        for (int i = 0; i < number_adult; i++) {
            panelFlight.add_adult();
        }
        Assert.assertEquals(number_adult, panelFlight.get_max_infants());
    }
}
