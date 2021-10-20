package doc.scanner;

import doc.scanner.facade.Scenarios;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AppTest extends Scenarios {

    private String URL;
    private String ID;

    @Test
    public void test(){

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

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
