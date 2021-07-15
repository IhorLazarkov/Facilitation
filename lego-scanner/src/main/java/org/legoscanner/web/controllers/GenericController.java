package org.legoscanner.web.controllers;

import info.facilitator.bean.LegoBean;
import info.facilitator.persister.SessionProvider;
import io.swagger.annotations.ApiOperation;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

@RestController
public class GenericController {

    @ApiOperation(value = "Get all data", response = ModelAndView.class)
    @GetMapping("/")
    public ModelAndView index() {
        final List<LegoBean> legos = new LinkedList<>();
        SessionProvider.withSession(session -> {

            Query<LegoBean> query = session.createQuery("From LegoBean order by legoName, date desc", LegoBean.class);
            legos.addAll(query.getResultList());

        });
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("legos", legos);
        legos.forEach(System.out::println);
        return mv;
    }
}
