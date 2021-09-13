package info.facilitator.persister;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionProvider {

    public static void withSession(DatabaseFunctional<Session> f) {
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        f.apply(session);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @FunctionalInterface
    public interface DatabaseFunctional<A> {
        public void apply(A a);
    }
}

