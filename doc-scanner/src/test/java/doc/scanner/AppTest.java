package doc.scanner;

import doc.scanner.facade.Scenarios;

import static org.assertj.core.api.Assertions.*;

import doc.scanner.rules.DefaultRule;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Predicate;

public class AppTest extends Scenarios {

    private String URL;
    private String ID;

    @Test
    public void test() {

        AppTest appTest = new AppTest();
        appTest.setURL(System.getProperty("doc-scanner-url"));
        appTest.setID(System.getProperty("doc-scanner-id"));

        assertThat(appTest.getUrl().isEmpty()).isFalse();
        assertThat(appTest.getId().isEmpty()).isFalse();

        assertThat(appTest.getStatus()).isTrue();
    }

    @Override
    public String getUrl() {
        return this.URL;
    }

    @Override
    public String getId() {
        return this.ID;
    }

    @Override
    public Predicate<List<WebElement>> rule() {
        return DefaultRule::rule;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
