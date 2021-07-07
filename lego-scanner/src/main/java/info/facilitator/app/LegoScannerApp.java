package info.facilitator.app;

import info.facilitator.bean.LegoBean;
import info.facilitator.bean.dao.LegoDAO;
import info.facilitator.page.LegoSalesPage;
import info.facilitator.page.LegoWelcomePage;
import info.facilitator.persister.SessionProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.hibernate.query.Query;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Optional;

public class LegoScannerApp {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        final WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.lego.com/en-us/page/lego-offers-promotions");

            LegoWelcomePage page = PageFactory.initElements(driver, LegoWelcomePage.class);
            page.checkSales();

            LegoSalesPage legoSalesPage = PageFactory.initElements(driver, LegoSalesPage.class);
            legoSalesPage.openSalesPage();

            List<LegoBean> legos = legoSalesPage.getLegos();

            SessionProvider.withSession(session -> {

                legos.forEach(lego -> {

                    Query<LegoBean> query = session.createQuery("from LegoBean Where legoName =:name", LegoBean.class);
                    query.setParameter("name", lego.getLegoName());
                    Optional<LegoBean> first = query.getResultStream().filter(LegoDAO.isToday()).findFirst();

                    if (first.isEmpty()) {
                        LegoDAO.persist(session, lego);
                    } else {
                        first.get().setPrice(lego.getPrice());
                        first.get().setPriceForSale(lego.getPriceForSale());
                        first.get().setDate(lego.getDate());
                        LegoDAO.update(session, first.get());
                    }
                });
            });

        } finally {
            driver.quit();
        }
    }
}