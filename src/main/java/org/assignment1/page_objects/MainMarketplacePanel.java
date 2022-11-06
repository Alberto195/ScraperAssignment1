package org.assignment1.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMarketplacePanel {

    protected WebDriver driver;

    protected WebDriverWait wait;

    public MainMarketplacePanel(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void goToProducts(String search) {
        driver.get("https://www.ebay.com/");
        String findButtonXpath = "//*[@id=\"gh-ac\"]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(findButtonXpath)));

        WebElement searchField = driver.findElement(By.xpath(findButtonXpath));
        searchField.sendKeys(search);
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"gh-btn\"]"));
        searchButton.submit();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"mainContent\"]"))));
    }

    public String getOriginalWindow() {
        return driver.getWindowHandle();
    }

}
