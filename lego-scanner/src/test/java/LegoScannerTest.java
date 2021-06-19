import info.facilitator.bean.dao.LegoDAO;
import info.facilitator.persister.SessionProvider;
import info.facilitator.page.LegoSalesPage;
import info.facilitator.page.LegoWelcomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class LegoScannerTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test1() {
        driver.get("https://www.lego.com/en-us");

        LegoWelcomePage page = PageFactory.initElements(driver, LegoWelcomePage.class);
        page.checkSales();

        LegoSalesPage legoSalesPage = PageFactory.initElements(driver, LegoSalesPage.class);
        legoSalesPage.openSalesPage();
        SessionProvider.withSession(session -> {
            legoSalesPage.getLegos().forEach(item -> LegoDAO.persist(session, item));
        });
    }
}
