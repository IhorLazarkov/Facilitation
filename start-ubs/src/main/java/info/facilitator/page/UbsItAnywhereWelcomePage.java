package info.facilitator.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UbsItAnywhereWelcomePage {

    private final WebDriverWait wait;
    private final WebDriver driver;

    @FindBy(css = "header h1")
    private WebElement header;
    @FindBy(css = "div.mobile-pass a[href*=logInUsingMobilePass]")
    private WebElement btnLogin;

    public UbsItAnywhereWelcomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 3);
    }

    public boolean ensureWelcomePageAppear(){
        wait.until(ExpectedConditions.visibilityOf(header));
        return !header.getText().isEmpty() && header.getText().equalsIgnoreCase("welcome");
    }

    public void createQRCode(){
        btnLogin.click();
    }
}
