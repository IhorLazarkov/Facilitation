package doc.scanner;

import doc.scanner.facade.Scenarios;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DocScannerTest {

    @Test
    public void testRunScan(){
        boolean b = Scenarios.getStatus();
        assertThat(b).isTrue();
    }
}
