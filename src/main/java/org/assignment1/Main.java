package org.assignment1;

import okhttp3.OkHttpClient;
import org.assignment1.api.HttpClient;
import org.assignment1.models.Entry;
import org.assignment1.scraper.Scraper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/home/albert/Downloads/chromedriver_linux64/chromedriver");
        System.out.println("the world has been set up");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

        Scraper scraper = new Scraper(driver, wait);
        HttpClient client = new HttpClient(new OkHttpClient());

        ArrayList<Entry> entries = scraper.getEntries(1);
        for (Entry ent : entries) {
            client.addAlert(ent);
        }
        System.out.println(entries.size() + " alert added to website");
    }
}
