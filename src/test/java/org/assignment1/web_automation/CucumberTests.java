package org.assignment1.web_automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import okhttp3.OkHttpClient;
import org.assignment1.api.HttpClient;
import org.assignment1.models.Entry;
import org.assignment1.scraper.Scraper;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CucumberTests {

    WebDriver driver;

    WebDriver scraperDriver;
    Scraper scraper;
    HttpClient client;

    int alertLength;

    List<WebElement> alerts;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/albert/Downloads/chromedriver_linux64/chromedriver");
        driver = new ChromeDriver();
        scraperDriver = new ChromeDriver();
        client = new HttpClient(new OkHttpClient());
    }

    @After
    public void tearDown() {
        client.purgeAlerts();
        client = null;
        driver.quit();
        driver = null;
        scraperDriver.quit();
        scraperDriver = null;
        alertLength = 0;
    }

    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum() {
        driver.get("https://www.marketalertum.com");
    }

    @When("I login using valid credentials")
    public void iLoginUsingValidCredentials() {
        driver.findElement(By.xpath("/html/body/header/nav/div/div/ul/li[3]/a")).click();
        WebElement insert = driver.findElement(By.xpath("//*[@id=\"UserId\"]"));
        insert.sendKeys("a338083d-1742-421a-be40-5978848942df");
        driver.findElement(By.xpath("/html/body/div/main/form/input[2]")).submit();
    }

    @Then("I should see my alerts")
    public void iShouldSeeMyAlerts() {
        boolean main = !driver.findElements(By.xpath("/html/body/div/main")).isEmpty();
        Assertions.assertTrue(main);
    }

    @When("I login using invalid credentials")
    public void iLoginUsingInvalidCredentials() {
        driver.findElement(By.xpath("/html/body/header/nav/div/div/ul/li[3]/a")).click();
        WebElement insert = driver.findElement(By.xpath("//*[@id=\"UserId\"]"));
        insert.sendKeys("test");
        driver.findElement(By.xpath("/html/body/div/main/form/input[2]")).submit();
    }

    @Then("I should see the login screen again")
    public void iShouldSeeTheLoginScreenAgain() {
        WebElement header = driver.findElement(By.xpath("/html/body/div/main/form/b"));
        Assertions.assertEquals("User ID:", header.getText());
    }

    @Given("I am an administrator of the website and I upload {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int arg0) {
        alertLength = arg0;
        scraper = new Scraper(scraperDriver, new WebDriverWait(scraperDriver, Duration.ofSeconds(1)));
        ArrayList<Entry> entries = scraper.getEntries(arg0);
        for (Entry ent : entries) {
            client.addAlert(ent);
        }
    }

    @When("I view a list of alerts")
    public void iViewAListOfAlerts() {
        iLoginUsingValidCredentials();
        iShouldSeeMyAlerts();
        alerts = driver.findElements(By.tagName("table"));
    }

    @Then("each alert should contain an icon")
    public void eachAlertShouldContainAnIcon() {
        for (WebElement alert : alerts) {
            boolean icon = !alert.findElements(By.xpath("/html/body/div/main/table/tbody/tr[1]/td/h4/img")).isEmpty();
            Assertions.assertTrue(icon);
        }
    }

    @And("each alert should contain a heading")
    public void eachAlertShouldContainAHeading() {
        for (WebElement alert : alerts) {
            String heading = alert.findElement(By.xpath("/html/body/div/main/table/tbody/tr[1]/td/h4")).getText();
            Assertions.assertNotEquals("", heading);
        }
    }

    @And("each alert should contain a description")
    public void eachAlertShouldContainADescription() {
        for (WebElement alert : alerts) {
            boolean description = !alert.findElements(By.xpath("/html/body/div/main/table/tbody/tr[3]/td")).isEmpty();
            Assertions.assertTrue(description);
        }
    }

    @And("each alert should contain an image")
    public void eachAlertShouldContainAnImage() {
        for (WebElement alert : alerts) {
            boolean image = !alert.findElements(By.xpath("/html/body/div/main/table/tbody/tr[2]/td/img")).isEmpty();
            Assertions.assertTrue(image);
        }
    }

    @And("each alert should contain a price")
    public void eachAlertShouldContainAPrice() {
        for (WebElement alert : alerts) {
            boolean price = !alert.findElements(By.xpath("/html/body/div/main/table/tbody/tr[4]/td/b")).isEmpty();
            Assertions.assertTrue(price);
        }
    }

    @And("each alert should contain a link to the original product website")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsite() {
        for (WebElement alert : alerts) {
            boolean website = !alert.findElements(By.xpath("/html/body/div/main/table/tbody/tr[5]/td/a")).isEmpty();
            Assertions.assertTrue(website);
        }
    }

    @Given("I am an administrator of the website and I upload more than {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlerts(int arg0) {
        alertLength = arg0;
        scraper = new Scraper(scraperDriver, new WebDriverWait(scraperDriver, Duration.ofSeconds(1)));
        ArrayList<Entry> entries = scraper.getEntries(arg0+1);
        for (Entry ent : entries) {
            client.addAlert(ent);
        }
    }

    @Then("I should see {int} alerts")
    public void iShouldSeeAlerts(int arg0) {
        alerts = driver.findElements(By.tagName("table"));
        Assertions.assertEquals(arg0, alerts.size());
    }

    @Given("I am an administrator of the website and I upload an alert of type {int}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfType(int arg0) {
        alertLength = 1;
        scraper = new Scraper(scraperDriver, new WebDriverWait(scraperDriver, Duration.ofSeconds(1)));
        ArrayList<Entry> entries = scraper.getEntries(alertLength);
        for (Entry ent : entries) {
            client.addAlert(ent);
        }
    }

    @And("the icon displayed should be icon-electronics.png")
    public void theIconDisplayedShouldBeIconElectronicsPng() {
        for (WebElement alert : alerts) {
            WebElement icon = alert.findElement(By.xpath("/html/body/div/main/table/tbody/tr[1]/td/h4/img"));
            Assertions.assertTrue(icon.getAttribute("src").contains("icon-electronics.png"));
        }
    }
}
