package info.facilitator.bean.dao;

import info.facilitator.bean.LegoBean;
import org.hibernate.Session;

public class LegoDAO {

    public static void persist(Session session, LegoBean bean){
        session.save(bean);
    }
}
