package doc.scanner.services;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class WebDriverProvider {

    public static void withDriver(WebDriverProviderInterface<WebDriver> f){

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        try{
            f.apply(driver);
        }finally {
            driver.quit();
        }
    }
}
