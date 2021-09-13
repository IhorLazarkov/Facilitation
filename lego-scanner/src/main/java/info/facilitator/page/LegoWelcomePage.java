package info.facilitator.page;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LegoWelcomePage {

    private final WebDriverWait wait;
    private final WebDriver driver;

    @FindBy(css = "div a[href$=sales-and-deals]")
    private WebElement btnSales;

    public LegoWelcomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 2);
//        this.driver.manage().window().setSize(new Dimension(3100, 1500));
    }

    public void checkSales() {
        LegoWelcomePageBanner pageBanner = PageFactory.initElements(driver, LegoWelcomePageBanner.class);
        if (pageBanner.isBannerAppeared())
            pageBanner.closeTheBanner();
        AcceptCookiePage acceptCookiePage = PageFactory.initElements(this.driver, AcceptCookiePage.class);
        if (acceptCookiePage.isCookieModalAppear())
            acceptCookiePage.closeModal();
        wait.withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofMillis(10))
                .until(ExpectedConditions.visibilityOf(btnSales));
        btnSales.click();
    }

}
