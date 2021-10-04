package doc.scanner.facade;

import doc.scanner.page.ResultPage;
import doc.scanner.services.WebDriverProvider;
import doc.scanner.page.MainPage;
import doc.scanner.utils.PropertyFileReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public abstract class Scenarios {

    public static boolean getStatus() {
        List<WebElement> elements = new LinkedList<>();
        WebDriverProvider.withDriver(driver -> {

            Properties properties = PropertyFileReader.getInstance().getProperties();
            final long WAIT = Long.parseLong(properties.getProperty("wait"));
            final String URL = properties.getProperty("url");
            final String ID = properties.getProperty("id");

            WebDriverWait wait = new WebDriverWait(driver, WAIT);
            driver.get(URL);

            MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
            WebElement txtId = mainPage.getTxtId();
            WebElement btnSearch = mainPage.getBtnSearch();
            wait.until(ExpectedConditions.visibilityOf(txtId));

            txtId.sendKeys(ID);
            btnSearch.click();

            ResultPage resultPage = PageFactory.initElements(driver, ResultPage.class);
            wait.withTimeout(Duration.ofSeconds(1))
                    .pollingEvery(Duration.ofMillis(10))
                    .until(ExpectedConditions.visibilityOfAllElements(resultPage.getRecords()));

            List<WebElement> records = resultPage.getRecords();
            elements.addAll(records);
            elements.forEach(element -> System.out.printf("Value: %s\n", element.getText()));
        });
        return elements.size() == 6;
    }
}
