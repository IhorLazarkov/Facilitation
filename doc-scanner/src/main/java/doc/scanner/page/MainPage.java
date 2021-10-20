package doc.scanner.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {

    @FindBy(name = "userId")
    private WebElement txtId;

    @FindBy(className = "myButton")
    private WebElement btnSearch;

    public WebElement getTxtId() {
        return txtId;
    }

    public WebElement getBtnSearch() {
        return btnSearch;
    }
}
