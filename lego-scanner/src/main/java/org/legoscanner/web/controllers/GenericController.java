package org.legoscanner.web.controllers;

import info.facilitator.bean.LegoBean;
import info.facilitator.persister.SessionProvider;
import io.swagger.annotations.ApiOperation;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class GenericController {

    private final List<LegoBean> legos = new LinkedList<>();

    @Autowired
    private Function<Session, List<LegoBean>> fetcher;

    @ApiOperation(value = "Get all data", response = ModelAndView.class)
    @GetMapping("/")
    public ModelAndView index() {
        SessionProvider.withSession(session -> {
            legos.addAll(fetcher.apply(session));
        });
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("legos", legos);
        legos.forEach(System.out::println);
        return mv;
    }

    @ApiOperation(value = "Get specific item", response = ModelAndView.class)
    @GetMapping(value = "/getItem")
    public ModelAndView getItem(@PathParam("name") String name) {
        List<LegoBean> result = legos.stream().filter(lego -> lego.getLegoName().equalsIgnoreCase(name)).collect(Collectors.toList());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        mv.addObject("legos", result);
        return mv;
    }
}
