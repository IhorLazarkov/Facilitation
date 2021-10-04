package doc.scanner;

import doc.scanner.facade.Scenarios;

import static org.assertj.core.api.Assertions.*;

import doc.scanner.services.MailSender;
import org.junit.jupiter.api.Test;

public class DocScannerTest {

    @Test
    public void testRunScan() {
        boolean b = Scenarios.getStatus();
        String title = String.format("Passport status: %s", b ? "true" : "false");
        new MailSender("ilazarkov@gmail.com", title, title).send();
        assertThat(b).isTrue();
    }
}
