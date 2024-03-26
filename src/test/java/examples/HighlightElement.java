package examples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HighlightElement {
    WebDriver driver;
    JavascriptExecutor js;

    @BeforeClass//don't work here
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
    }
    private void highlightElement(WebElement element, int duration) throws InterruptedException {
        String original_style = element.getAttribute("style");

        js.executeScript(
                "arguments[0].setAttribute(arguments[1], arguments[2])",
                element,
                "style",
                "border: 2px solid red; border-style: dashed;");

        if (duration > 0) {
            Thread.sleep(duration * 1000);
            js.executeScript(
                    "arguments[0].setAttribute(arguments[1], arguments[2])",
                    element,
                    "style",
                    original_style);
        }
    }

    @Test
    public void highlightElementTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.get("http://the-internet.herokuapp.com/large");
        WebElement element = driver.findElement(By.id("sibling-2.3"));
        highlightElement(element, 3);
    }

    @AfterEach
    public void tearDown() throws Exception {
        driver.quit();
    }
}
