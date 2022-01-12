package org.legoscanner.web.controllers;

import info.facilitator.bean.LegoBean;
import info.facilitator.persister.SessionProvider;
import io.swagger.annotations.ApiOperation;
import org.hibernate.Session;
import org.legoscanner.web.serviceimplementation.DateFiltration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class HomeController {

    private final static List<LegoBean> legos = new LinkedList<>();
    private final ModelAndView mv = new ModelAndView("home");

    @Autowired
    private Function<Session, List<LegoBean>> fetcher;

    @ApiOperation(value = "Get all records", response = ModelAndView.class)
    @GetMapping("/")
    public ModelAndView index() {
        if(legos.isEmpty()) {
            SessionProvider.withSession(session -> {
                legos.addAll(fetcher.apply(session));
            });
        }
        mv.addObject("legos", legos);
        legos.forEach(System.out::println);
        return mv;
    }

    @ApiOperation(value = "Get specific item by name", response = ModelAndView.class)
    @GetMapping(value = "/getItemByName")
    public ModelAndView getItem(@RequestParam("name") String name) {
        List<LegoBean> result = legos.stream().filter(lego -> lego.getLegoName().equalsIgnoreCase(name)).collect(Collectors.toList());
        mv.addObject("legos", result);
        return mv;
    }

    @ApiOperation(value = "Get all the records based on date From and To")
    @GetMapping(path = "/filterByDate")
    public ModelAndView filterByDate(@RequestParam(name = "from", defaultValue = "1900-01-01") String dateFrom,
                                     @RequestParam(name = "to", defaultValue = "9999-12-31") String dateTo) {

        Predicate<LegoBean> filterOnwards = new DateFiltration(dateFrom)::fromDateOnward;
        Predicate<LegoBean> filterBackwards = new DateFiltration(dateTo)::fromDateBackward;

        Set<LegoBean> result = legos.stream().filter(filterOnwards).filter(filterBackwards).collect(Collectors.toSet());
        mv.addObject("legos", result);
        return mv;
    }
}
