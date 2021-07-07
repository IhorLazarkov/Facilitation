package org.legoscanner.web.controllers;

import info.facilitator.bean.LegoBean;
import info.facilitator.persister.SessionProvider;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Controller
public class GenericController {

    @GetMapping("/")
    public ModelAndView index() {
        final List<LegoBean> legos = new LinkedList<>();
        SessionProvider.withSession(session -> {

            Query<LegoBean> query = session.createQuery("From LegoBean order by legoName, date desc", LegoBean.class);
            legos.addAll(query.getResultList());

        });
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("legos", legos);
        System.out.println(legos);
        return mv;
    }
}
