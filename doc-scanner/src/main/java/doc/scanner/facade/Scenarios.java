package doc.scanner.facade;

import doc.scanner.page.ResultPage;
import doc.scanner.services.WebDriverProvider;
import doc.scanner.page.MainPage;
import doc.scanner.utils.PropertyFileReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public abstract class Scenarios {

    public boolean getStatus() {
        List<WebElement> elements = new LinkedList<>();
        Properties properties = PropertyFileReader.getInstance().getProperties();
        final long WAIT = Long.parseLong(properties.getProperty("wait"));
        final String URL = getUrl();
        final String ID = getId();

        System.out.println(getUrl());
        System.out.println(getId());

        WebDriverProvider.withDriver(driver -> {

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
            elements.forEach(element -> System.out.printf("Value: %s\n", new String(element.getText().getBytes(StandardCharsets.UTF_8))));
        });
        return elements.size() == 6;
    }

    abstract public String getUrl();

    abstract public String getId();
}
