package info.facilitator.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AcceptCookiePage {

    private final WebDriverWait wait;
    private final WebDriver driver;

    @FindBy(css = "div[class*=ModalContent]")
    private WebElement modal;
    @FindBy(css = "button[data-test='cookie-accept-all']")
    private WebElement btnAcceptAll;

    public AcceptCookiePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 0);
    }

    public boolean isCookieModalAppear() {
        return wait.until(ExpectedConditions.visibilityOf(modal)) != null;
    }

    public void closeModal() {
        btnAcceptAll.click();
    }
}
