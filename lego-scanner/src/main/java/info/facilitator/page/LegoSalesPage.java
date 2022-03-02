package info.facilitator.page;

import info.facilitator.bean.LegoBean;
import info.facilitator.bean.LegoBeanBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LegoSalesPage {

    private final WebDriverWait wait;
    private final WebDriver driver;

    @FindBy(css = "section a[href$=sales-and-deals]")
    private WebElement btnSales;
    @FindAll(@FindBy(css = "ul[class^=ProductGridstyles__Grid] li[class^=ProductGridstyles__Item]"))
    private List<WebElement> legos;
    @FindAll(@FindBy(css = "a[data-test^=pagination-page]"))
    private List<WebElement> pages;

    public LegoSalesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 0);
    }

    public void openSalesPage() {
        wait.withTimeout(Duration.ofSeconds(2))
                .pollingEvery(Duration.ofMillis(10))
                .until(ExpectedConditions.elementToBeClickable(btnSales));
        btnSales.click();
    }

    public List<LegoBean> getLegos() {
        List<LegoBean> legoBeans = new ArrayList<>();
        wait.until(ExpectedConditions.visibilityOfAllElements(legos));

        for (WebElement page : pages) {

            wait.until(ExpectedConditions.visibilityOf(page));
            page.click();

            for (WebElement webElement : legos) {

                WebElement price = null;
                WebElement salesPrice = null;
                WebElement legoName = null;

                try {
                    legoName = webElement.findElement(By.cssSelector("h2[data-test='product-leaf-title'] span"));
                    price = webElement.findElement(By.cssSelector("div[class^=ProductPricestyles__Wrapper] span[data-test='product-price']"));
                    salesPrice = webElement.findElement(By.cssSelector("div[class^=ProductPricestyles__Wrapper] span[data-test='product-price-sale']"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (legoName != null && price != null && salesPrice != null) {
                    LegoBean legoBean = new LegoBeanBuilder().createBean()
                            .setLegoName(legoName.getText())
                            .setPrice(getPrice(price))
                            .setSalePrice(getPrice(salesPrice))
                            .build();
                    legoBeans.add(legoBean);
                }
            }
        }
        return legoBeans;
    }

    private String getPrice(WebElement element) {
        return element.getText().split("\n")[1].replaceFirst("\\W", "");
    }
}
