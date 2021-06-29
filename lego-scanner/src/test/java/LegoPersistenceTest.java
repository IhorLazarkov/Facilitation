import info.facilitator.bean.LegoBean;
import info.facilitator.bean.LegoBeanBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

        Query<LegoBean> query = session.createQuery("From LegoBean Order By id desc", LegoBean.class);
        List<LegoBean> resultList = query.getResultList();
        resultList.forEach(System.out::println);
        session.close();
        sessionFactory.close();

    }

    private Predicate<LegoBean> isToday(){
        return bean -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date currentDate = simpleDateFormat.parse(LocalDateTime.now().toString());
                Date date = simpleDateFormat.parse(bean.getDate());
                int result = date.compareTo(currentDate);
                return result == 0 ;

            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        };
    }
}
