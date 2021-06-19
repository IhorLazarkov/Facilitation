package info.facilitator.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LegoWelcomePageBanner {

    private final WebDriverWait wait;
    private final WebDriver driver;

    @FindBy(css = "div[class^=AgeGatestyles__Container]")
    private WebElement bannerMain;
    @FindBy(css = "div[class^=AgeGatestyles__ContentContainer] button[class^=Button__Base]")
    private WebElement btnContinue;

    public LegoWelcomePageBanner(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 0);
    }

    public boolean isBannerAppeared() {
        return wait.withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofMillis(10))
                .until(ExpectedConditions.visibilityOf(bannerMain)) != null;
    }

    public void closeTheBanner() {
        btnContinue.click();
    }
}