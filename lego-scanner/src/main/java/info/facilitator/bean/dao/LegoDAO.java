package info.facilitator.bean.dao;

import info.facilitator.bean.LegoBean;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaUpdate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

@Component
public class LegoDAO {

    public static BiConsumer<Session, LegoBean> persist() {
        return Session::save;
    }

    public static BiConsumer<Session, LegoBean> update() {
        return Session::update;
    }
    public static BiConsumer<Session, LegoBean> delete() {
        return Session::delete;
    }

    public static Predicate<LegoBean> isToday() {
        return bean -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date currentDate = simpleDateFormat.parse(LocalDateTime.now().toString());
                Date date = simpleDateFormat.parse(bean.getDate());
                int result = date.compareTo(currentDate);
                return result == 0;

            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        };
    }
}
