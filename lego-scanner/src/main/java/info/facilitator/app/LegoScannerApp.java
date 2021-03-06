package info.facilitator.app;

import info.facilitator.bean.LegoBean;
import info.facilitator.bean.dao.LegoDAO;
import info.facilitator.page.LegoSalesPage;
import info.facilitator.page.LegoWelcomePage;
import info.facilitator.persister.SessionProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class LegoScannerApp {

    public static void main(String[] args) {

        final BiConsumer<Session, LegoBean> update = LegoDAO.update();
        final BiConsumer<Session, LegoBean> persist = LegoDAO.persist();
        final Predicate<LegoBean> isToday = LegoDAO.isToday();

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
                    Optional<LegoBean> first = query.getResultStream().filter(isToday).findFirst();

                    if (first.isEmpty()) {
                        persist.accept(session,lego);
                    } else {
                        first.get().setPrice(lego.getPrice());
                        first.get().setPriceForSale(lego.getPriceForSale());
                        first.get().setDate(lego.getDate());
                        update.accept(session, first.get());
                    }
                });
            });

        } finally {
            driver.quit();
        }
    }
}
