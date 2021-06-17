package info.facilitator.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UbsItAnywherePage {

    private final WebDriverWait wait;
    private final WebDriver driver;

    @FindBy(css = "a[style*='images/option_bg_unitedstates.png']")
    private WebElement btnUS;

    public UbsItAnywherePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 3);
    }

    public void selectUnitedStates() {
        wait.until(ExpectedConditions.visibilityOf(btnUS));
        btnUS.click();
    }
}
