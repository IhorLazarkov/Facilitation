package org.legoscanner.web.controllers;

import info.facilitator.bean.LegoBean;
import info.facilitator.persister.SessionProvider;
import io.swagger.annotations.ApiOperation;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.legoscanner.web.serviceimplementation.DefaultFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@RestController
public class GenericController {

    @Autowired
    private Function<Session, List<LegoBean>> fetcher;

    @ApiOperation(value = "Get all data", response = ModelAndView.class)
    @GetMapping("/")
    public ModelAndView index() {
        final List<LegoBean> legos = new LinkedList<>();
        SessionProvider.withSession(session -> {
            legos.addAll(fetcher.apply(session));
        });
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("legos", legos);
        legos.forEach(System.out::println);
        return mv;
    }
}
