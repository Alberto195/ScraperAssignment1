package org.assignment1.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class MainFeaturePanel {

    protected WebDriver driver;

    protected WebDriverWait wait;

    public MainFeaturePanel(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String getProductUrl(int i) {
        return driver.findElement(By.xpath("//*[@id=\"srp-river-results\"]/ul/li[" + i + "]/div/div[1]/div/a")).getAttribute("href");
    }

    public String getProductImageUrl(int i) {
        return driver.findElement(By.xpath("//*[@id=\"srp-river-results\"]/ul/li[" + i + "]/div/div[1]/div/a/div/img")).getAttribute("src");
    }

    public String getHeading(int i) {
        return driver.findElement(By.xpath("//*[@id=\"srp-river-results\"]/ul/li[" + i + "]/div/div[2]/a/div/span")).getText();
    }

    public void clickOnProduct(int i) {
        driver.findElement(By.xpath("//*[@id=\"srp-river-results\"]/ul/li[" + i + "]/div/div[2]/a/div/span")).click();
        wait.until(numberOfWindowsToBe(2));
    }

    public String switchToNewWindow(String originalWindow) {
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                return windowHandle;
            }
        }
        return "";
    }
}
