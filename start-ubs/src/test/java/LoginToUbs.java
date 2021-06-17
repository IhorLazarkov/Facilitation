import info.facilitator.page.UbsItAnywherePage;
import info.facilitator.page.UbsItAnywhereWelcomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginToUbs {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void test() throws InterruptedException {

        driver.get("https://workspace-stm5.ra.ubs.com/logon/choose-your-location/index.html");

        UbsItAnywherePage loginPage = PageFactory.initElements(driver, UbsItAnywherePage.class);
        loginPage.selectUnitedStates();

        UbsItAnywhereWelcomePage welcomePage = PageFactory.initElements(driver, UbsItAnywhereWelcomePage.class);
        Assertions.assertTrue(welcomePage.ensureWelcomePageAppear());
        welcomePage.createQRCode();

        Thread.sleep(3000);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
