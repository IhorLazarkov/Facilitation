package org.legoscanner.web.serviceimplementation;

import info.facilitator.bean.LegoBean;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class DefaultFetcher implements Function<Session, List<LegoBean>> {

    @Override
    public List<LegoBean> apply(Session session) {
        Query<LegoBean> query = session.createQuery("From LegoBean order by legoName, date desc", LegoBean.class);
        return query.getResultList();
    }
}
