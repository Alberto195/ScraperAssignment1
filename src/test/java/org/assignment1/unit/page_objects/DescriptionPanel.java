package org.assignment1.unit.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DescriptionPanel {

    protected WebDriver driver;

    public DescriptionPanel(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
    }

    public String getPrice(int i) {
        return driver.findElement(By.xpath("//*[@id=\"prcIsum\"]")).getText();
    }

    public String getDescriptionLink(int i) {
        return driver.findElement(By.xpath("//*[@id=\"desc_ifr\"]")).getAttribute("src");
    }

    public void switchToOriginalWindow(String windowToCloseHandle, String originalWindow) {
        driver.switchTo().window(windowToCloseHandle).close();
        driver.switchTo().window(originalWindow);
    }

}
