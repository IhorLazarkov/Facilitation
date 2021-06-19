package info.facilitator.bean.dao;

import info.facilitator.bean.LegoBean;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaUpdate;

public class LegoDAO {

    public static void persist(Session session, LegoBean bean) {
        session.save(bean);
    }

    public static void update(Session session, LegoBean bean) {
        session.update(bean);
    }

    public static void isAvailableToday(Session session, LegoBean bean){
        //TODO
//        session.createQuery("From Book where ")
    }
}
