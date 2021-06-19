import info.facilitator.bean.LegoBean;
import info.facilitator.bean.LegoBeanBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LegoPersistenceTest {

    private LegoBean bean;

    @BeforeEach
    public void setUp(){
        bean = new LegoBeanBuilder().createBean()
                .setLegoName("TestLego")
                .setPrice("100")
                .setSalePrice("50")
                .build();
    }

    @Test
    public void testPersistence(){
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.save(bean);
//        session.getTransaction().commit();

        Query<LegoBean> query = session.createQuery("From LegoBean", LegoBean.class);
        List<LegoBean> resultList = query.getResultList();
        resultList.forEach(System.out::println);
        session.close();
        sessionFactory.close();

    }
}
