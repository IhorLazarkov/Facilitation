package doc.scanner.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ResultPage {

    @FindAll(@FindBy(css = "div[id=statusResultId] tr"))
    List<WebElement> records;

    public List<WebElement> getRecords() {
        return records;
    }
}
