import info.facilitator.bean.LegoBean;
import info.facilitator.bean.LegoBeanBuilder;
import info.facilitator.bean.dao.LegoDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.util.List;

public class LegoPersistenceTest {

    private LegoBean bean;
    private static Session session;
    private static SessionFactory sessionFactory;

    @BeforeAll
    public static void setUpAll() {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
    }

    @AfterAll
    public static void tearDownAll() {
        session.close();
        sessionFactory.close();
    }

    @BeforeEach
    public void setUp() {
        bean = new LegoBeanBuilder().createBean()
                .setLegoName("TestLego")
                .setPrice("100")
                .setSalePrice("50")
                .build();

        session.beginTransaction();
        session.save(bean);
        session.getTransaction().commit();
    }

    @AfterEach
    public void tearDown(){
        session.beginTransaction();
        session.delete(bean);
        session.getTransaction().commit();
    }

    @Test
    public void testPersistence() {
        Query<LegoBean> query = session.createQuery("From LegoBean Order By id desc", LegoBean.class);
        List<LegoBean> resultList = query.getResultList();
        assertTrue(bean.equals(resultList.get(0)));
    }

    @Test
    public void testUpdate() {

        final String TEST_TEXT = "Test Lego Update";

        bean.setLegoName(TEST_TEXT);
        session.beginTransaction();
        if (LegoDAO.isToday().test(bean))
            session.update(bean);
        else
            session.save(bean);
        session.getTransaction().commit();

        List<LegoBean> result = session.createQuery("From LegoBean", LegoBean.class).getResultList();
        assertEquals(1, result.size());
        assertEquals(TEST_TEXT, result.get(0).getLegoName());
    }
}
