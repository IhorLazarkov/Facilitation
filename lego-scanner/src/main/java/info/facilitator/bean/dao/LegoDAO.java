package info.facilitator.bean.dao;

import info.facilitator.bean.LegoBean;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaUpdate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Predicate;

public class LegoDAO {

    public static void persist(Session session, LegoBean bean) {
        session.save(bean);
    }

    public static void update(Session session, LegoBean bean) {
        session.update(bean);
    }

    public static Predicate<LegoBean> isToday(){
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
