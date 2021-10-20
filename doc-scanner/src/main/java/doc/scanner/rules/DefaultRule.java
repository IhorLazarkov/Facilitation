package doc.scanner.rules;

import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class DefaultRule {

    public static boolean rule(List<WebElement> list) {
        return list.size() <= 5;
    }
}
